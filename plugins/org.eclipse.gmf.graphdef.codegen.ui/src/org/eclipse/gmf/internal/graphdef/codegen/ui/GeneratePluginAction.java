package org.eclipse.gmf.internal.graphdef.codegen.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.gmfgraph.FigureGallery;
import org.eclipse.gmf.gmfgraph.util.RuntimeFQNSwitch;
import org.eclipse.gmf.graphdef.codegen.StandaloneGenerator;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class GeneratePluginAction implements IObjectActionDelegate, IInputValidator {
	private List/*IFile*/ mySelectedFiles = Collections.EMPTY_LIST;
	private IWorkbenchPart myTargetPart;

	public GeneratePluginAction() {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myTargetPart = targetPart;
	}

	public void run(IAction action) {
		List/*FigureGallery*/ galleries = new ArrayList(5);
		final ResourceSet rs = new ResourceSetImpl();
		loadFromSelection(rs, galleries);
		if (galleries.isEmpty()) {
			MessageDialog.openInformation(getShell(), "Nothing to do", "No figure galleries found in the selected files, nothing to do");
			return;
		}
		InputDialog readInputDlg = new InputDialog(getShell(), "Specify plug-in id", "Please enter name of plug-in/main package", null, this);
		if (readInputDlg.open() != InputDialog.OK) {
			return;
		}
		FigureGallery[] input = (FigureGallery[]) galleries.toArray(new FigureGallery[galleries.size()]);
		StandaloneGenerator.Config config = new StandaloneGenerator.ConfigImpl(readInputDlg.getValue(), readInputDlg.getValue(), false);
		final StandaloneGenerator generator = new StandaloneGenerator(input, config, new RuntimeFQNSwitch());
		generator.setSkipPluginStructure(false);

		new Job(action.getText()) {
			{
				// setUser(true); FIXME fixed after M5, uncoment when switching to M6 
			}

			protected IStatus run(IProgressMonitor monitor) {
				try {
					generator.run(monitor);
					if (!generator.getRunStatus().isOK()) {
						return generator.getRunStatus();
					}
					IFile galleryFile = decideOnDestinationFile();
					Resource r = rs.createResource(URI.createPlatformResourceURI(galleryFile.getFullPath().toString()));
					return saveNewFigureGallery(r, generator.getGenerationInfo());
				} catch (InterruptedException e) {
					return Status.CANCEL_STATUS;
				} finally {
					for (Iterator it = rs.getResources().iterator(); it.hasNext();) {
						((Resource) it.next()).unload();
					}
				}
			}

			private IFile decideOnDestinationFile() {
				StandaloneGenerator.Config config = generator.getGenerationInfo().getConfig();
				IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject(config.getPluginID());
				assert p.exists(); // generator.runStatus.ok makes us believe
				IContainer fileContainer = p;
				IFolder modelsFolder = p.getFolder("models");
				if (!modelsFolder.exists()) {
					try {
						modelsFolder.create(true, true, new NullProgressMonitor());
						fileContainer = modelsFolder;
					} catch (CoreException ex) {
						// ignore or log?
					}
				}
				final IPath baseName = new Path("bundled");
				final String ext = "gmfgraph";
				IFile galleryFile = fileContainer.getFile(baseName.addFileExtension(ext));
				for (int i = 1; galleryFile.exists(); i++) {
					galleryFile = fileContainer.getFile(new Path(baseName.lastSegment() + String.valueOf(i)).addFileExtension(ext));
				}
				return galleryFile;
			}

			private IStatus saveNewFigureGallery(Resource galleryResource, StandaloneGenerator.GenerationInfo info) {
				galleryResource.getContents().add(new StandaloneGalleryConverter().convertFigureGallery(info));
				try {
					galleryResource.save(null);
				} catch (IOException e) {
					return new Status(IStatus.ERROR, "org.eclipse.gmf.graphdef.codegen.ui", 0, e.getMessage(), e);
				}
				return Status.OK_STATUS;
			}
		}.schedule();
	}

	private void loadFromSelection(ResourceSet rs, List/*FigureGallery*/ galleries) {
		final FigureFinder extractor = new FigureFinder();
		for (Iterator it = mySelectedFiles.iterator(); it.hasNext();) {
			try {
				IFile next = (IFile) it.next();
				Resource r = rs.getResource(URI.createPlatformResourceURI(next.getFullPath().toString()), true);
				FigureGallery[] fg = extractor.findFigures(r);
				galleries.addAll(Arrays.asList(fg));
			} catch (Exception ex) {
				ex.printStackTrace();
				// FIXME log
			}
		}
	}

	public String isValid(String newText) {
		IStatus s = JavaConventions.validatePackageName(newText);
		if (s.isOK()) {
			return null;
		}
		return s.getMessage();
	}

	private Shell getShell() {
		return myTargetPart.getSite().getShell();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		mySelectedFiles = new ArrayList(5);
		if (selection instanceof IStructuredSelection) {
			mySelectedFiles.addAll(((IStructuredSelection) selection).toList());
		}
	}
}
