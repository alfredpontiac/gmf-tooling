/*
 * <copyright>
 *
 * Copyright (c) 2005-2006 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.gmf.internal.xpand.build;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.internal.xpand.Activator;
import org.eclipse.gmf.internal.xpand.expression.AnalysationIssue;
import org.eclipse.gmf.internal.xpand.expression.ExecutionContext;
import org.eclipse.gmf.internal.xpand.model.XpandExecutionContext;
import org.eclipse.gmf.internal.xpand.model.XpandResource;
import org.eclipse.gmf.internal.xpand.util.ContextFactory;
import org.eclipse.gmf.internal.xpand.util.OawMarkerManager;
import org.eclipse.gmf.internal.xpand.util.ParserException;
import org.eclipse.gmf.internal.xpand.util.ParserException.ErrorLocationInfo;
import org.eclipse.gmf.internal.xpand.xtend.ast.XtendResource;

public class OawBuilder extends IncrementalProjectBuilder {

	private static boolean firstBuild = true; // XXX odd
	private WorkspaceResourceManager resourceManager;

	// XXX again, using map as mere pairs
	private final Map<XtendResource, IFile> xtendResourcesToAnalyze = new HashMap<XtendResource, IFile>();
	private final Map<XpandResource, IFile> xpandResourcesToAnalyze = new HashMap<XpandResource, IFile>();

	public static final String getBUILDER_ID() {
		return "org.openarchitectureware.base.oawBuilder";
	}

	private static boolean isFileOfInterest(IFile file) {
		return XpandResource.TEMPLATE_EXTENSION.equals(file.getFileExtension()) || XtendResource.FILE_EXTENSION.equals(file.getFileExtension());
	}

	@Override
	protected void startupOnInitialize() {
		// TODO Auto-generated method stub
		super.startupOnInitialize();
		resourceManager = new WorkspaceResourceManager(getProject());
	}

	@Override
	protected IProject[] build(final int kind, final Map args, final IProgressMonitor monitor) throws CoreException {
		try {
			if (firstBuild || (kind == FULL_BUILD)) {
				System.err.println("First build, kind:" + kind + " and is FULLBUILD:" + (kind == FULL_BUILD));
				fullBuild(monitor);
			} else {
				final IResourceDelta delta = getDelta(getProject());
				if (delta == null) {
					fullBuild(monitor);
				} else {
					incrementalBuild(delta, monitor);
				}
			}
		} catch (final Throwable e) {
			e.printStackTrace();
		}
		firstBuild = false;
		// TODO to separate thread
		
		for (XtendResource r : xtendResourcesToAnalyze.keySet()) {
	        final ExecutionContext ctx = ContextFactory.createXtendContext(getResourceManager());
	        final Set<AnalysationIssue> issues = new HashSet<AnalysationIssue>();
	        r.analyze(ctx, issues);
	        updateMarkers(xtendResourcesToAnalyze.get(r), issues);
		}
		for (XpandResource r : xpandResourcesToAnalyze.keySet()) {
	        final XpandExecutionContext ctx = ContextFactory.createXpandContext(getResourceManager());
	        final Set<AnalysationIssue> issues = new HashSet<AnalysationIssue>();
	        r.analyze(ctx, issues);
	        updateMarkers(xpandResourcesToAnalyze.get(r), issues);
		}
		// XXX is builder's instance shared for several projects - if yes, we may get ConcurrentModificationEx here 
		xtendResourcesToAnalyze.clear();
		xpandResourcesToAnalyze.clear();
		return null;
	}

	void reloadResource(final IFile resource) {
		if (!resource.exists()) {
			return;
		}
		getResourceManager().forget(resource);
		try {
			if (XpandResource.TEMPLATE_EXTENSION.equals(resource.getFileExtension())) {
				XpandResource r = getResourceManager().loadXpandResource(resource);
				if (r != null) {
					xpandResourcesToAnalyze.put(r, resource);
				}
			} else if (XtendResource.FILE_EXTENSION.equals(resource.getFileExtension())) {
				XtendResource r = getResourceManager().loadXtendResource(resource);
				if (r != null) {
					xtendResourcesToAnalyze.put(r, resource);
				}
			}
		} catch (ParserException ex) {
			updateMarkers(resource, ex.getParsingErrors());
		} catch (Exception ex) {
			Activator.logError(ex);
			// perhaps, depending on exception type (Core|IO) we can decide to keep old markers? 
			OawMarkerManager.deleteMarkers(resource);
			OawMarkerManager.addErrorMarker(resource, ex.getMessage(), -1, -1);
		}
	}


	public void handleRemovement(final IFile resource) {
		OawMarkerManager.deleteMarkers(resource);
		getResourceManager().forget(resource);
	}

	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		getProject().accept(new XpandResourceVisitor(monitor));
	}

	protected void incrementalBuild(final IResourceDelta delta, final IProgressMonitor monitor) throws CoreException {
		delta.accept(new XpandResourceVisitor(monitor));
	}

	private WorkspaceResourceManager getResourceManager() {
		return resourceManager;
	}

	private static void updateMarkers(IFile resource, Set<AnalysationIssue> issues) {
        OawMarkerManager.deleteMarkers(resource);
        OawMarkerManager.addMarkers(resource, issues.toArray(new AnalysationIssue[issues.size()]));
	}

	private static void updateMarkers(IFile resource, ErrorLocationInfo[] parsingErrors) {
        OawMarkerManager.deleteMarkers(resource);
        OawMarkerManager.addMarkers(resource, parsingErrors);
	}

	private class XpandResourceVisitor implements IResourceVisitor, IResourceDeltaVisitor {
		private IProgressMonitor monitor;

		public XpandResourceVisitor(final IProgressMonitor monitor) {
			this.monitor = monitor;
		}

		public boolean visit(final IResource resource) {
			if (!resource.isDerived() && (resource instanceof IFile) && isFileOfInterest((IFile) resource)) {
				reloadResource((IFile) resource);
			}
			monitor.worked(1);
			return true;
		}

		public boolean visit(final IResourceDelta delta) throws CoreException {
			final IResource resource = delta.getResource();
			if (resource.isDerived()) {
				return false;
			}
			if ((resource instanceof IFile)) {
				IFile file = (IFile) resource;
				if (!isFileOfInterest(file)) {
					return false;
				}
				switch (delta.getKind()) {
				case IResourceDelta.ADDED:
					reloadResource(file);
					break;
				case IResourceDelta.REMOVED:
					handleRemovement(file);
					break;
				case IResourceDelta.CHANGED:
					reloadResource(file);
					break;
				}
			} else if (resource instanceof IProject) {
				// forget about project in resource manager
				if (delta.getKind() == IResourceDelta.REMOVED) {
					System.err.println("Project removed:" + resource.getName());
				}
				if (delta.getKind() == IResourceDelta.OPEN) {
					System.err.println("Project open:" + ((IProject) resource).isOpen());
				}
			}
			monitor.worked(1);
			return true;
		}
	}
}
