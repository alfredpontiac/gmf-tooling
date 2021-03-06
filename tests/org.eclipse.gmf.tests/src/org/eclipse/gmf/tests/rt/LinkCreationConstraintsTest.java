/*
 * Copyright (c) 2005, 2010 Borland Software Corporation and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Radek Dvorak (Borland) - initial API and implementation
 */
package org.eclipse.gmf.tests.rt;

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.codegen.gmfgen.GMFGenFactory;
import org.eclipse.gmf.codegen.gmfgen.GenConstraint;
import org.eclipse.gmf.codegen.gmfgen.GenLink;
import org.eclipse.gmf.codegen.gmfgen.GenLinkConstraints;
import org.eclipse.gmf.codegen.gmfgen.GenNode;
import org.eclipse.gmf.codegen.gmfgen.TypeLinkModelFacet;
import org.eclipse.gmf.internal.bridge.genmodel.GenModelMatcher;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tests.setup.RuntimeBasedGeneratorConfiguration;
import org.eclipse.gmf.tests.setup.ViewerConfiguration;

public class LinkCreationConstraintsTest extends GeneratedCanvasTest {

	public LinkCreationConstraintsTest(String name) {
		this(name, new RuntimeBasedGeneratorConfiguration());
	}
	
	protected LinkCreationConstraintsTest(String name, ViewerConfiguration.Factory viewerConfigFactory) {
		super(name, viewerConfigFactory);
	}
	
	/*
	 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=148818 
	 */
	public void testEndContexts() {
		GenLink genLinkOrigin = getGenModel().getLinkC();
		GenLink genLink = EcoreUtil.copy(genLinkOrigin);
		assertTrue(genLink.getModelFacet() instanceof TypeLinkModelFacet);
		TypeLinkModelFacet tlModelFacet = (TypeLinkModelFacet)genLink.getModelFacet();
		assertTrue(tlModelFacet.getContainmentMetaFeature() != null && tlModelFacet.getTargetMetaFeature() != null);
		
		GenLinkConstraints constraints = GMFGenFactory.eINSTANCE.createGenLinkConstraints();
		constraints.setSourceEnd(createDummyConstraint());
		constraints.setTargetEnd(createDummyConstraint());
		genLink.setCreationConstraints(constraints);
		
		assertSame("Target end context must be the containment feature owner", //$NON-NLS-1$
				tlModelFacet.getContainmentMetaFeature().getGenClass(),
				constraints.getSourceEndContextClass());
		
		assertSame("Source end context must be the target feature type", //$NON-NLS-1$
				tlModelFacet.getTargetMetaFeature().getTypeGenClass(),
				constraints.getTargetEndContextClass());
		
		GenModelMatcher genModelMatcher = new GenModelMatcher(getGenModel().getGenDiagram().getDomainDiagramElement().getGenModel());
		
		GenFeature sourceFeature = genModelMatcher.findGenFeature(tlModelFacet.getMetaClass().getEcoreClass().getEStructuralFeature("source")); //$NON-NLS-1$
		assertNotNull("Link element's source feature not found", sourceFeature); //$NON-NLS-1$
		tlModelFacet.setSourceMetaFeature(sourceFeature);
		
		assertSame("Source end context with source feature must the feature's owner class", //$NON-NLS-1$
				sourceFeature.getTypeGenClass(), constraints.getSourceEndContextClass()); 
	}
	
	private static GenConstraint createDummyConstraint() {
		GenConstraint constraint = GMFGenFactory.eINSTANCE.createGenConstraint();
		constraint.setBody("dummy"); //$NON-NLS-1$
		return constraint;
	}
	
	public void testCreateConstrainedLinks() throws Exception {
		Diagram diagram = (Diagram)getDiagramEditPart().getModel();		
		Node sourceContainerNode = createNode(getSourceGenNode(), diagram);
		setBusinessElementStructuralFeature(sourceContainerNode, "acceptLinkKind", null); //$NON-NLS-1$		
		
		Node targetNode = createNode(getTargetGenNode(), diagram);		
		assertNotNull(findEditPart(targetNode));
		setBusinessElementStructuralFeature(sourceContainerNode, "acceptLinkKind", null); //$NON-NLS-1$
		// XXX canStartLinkFrom returns false if command == null or not executable
		// hence, assertFalse is slightly incorrect here - command SHOULD NOT be null
		// but only its canExecute() should be false?
		assertFalse("Can start link without acceptedLinkKind", //$NON-NLS-1$
				canStartLinkFrom(getRefGenLink(), sourceContainerNode));
		
		String linkKind = "kind1"; //$NON-NLS-1$
		setBusinessElementStructuralFeature(sourceContainerNode, "acceptLinkKind", linkKind); //$NON-NLS-1$
		assertTrue("Allow start link with acceptedLinkKind", //$NON-NLS-1$ 
				canStartLinkFrom(getRefGenLink(), sourceContainerNode));		
				
		assertNull("Do not create link to node with different linkKind", //$NON-NLS-1$ 
				createLink(getRefGenLink(), sourceContainerNode, targetNode));		
		// set the same link kind to target node
		setBusinessElementStructuralFeature(targetNode, "acceptLinkKind", linkKind); //$NON-NLS-1$
		Edge link = createLink(getRefGenLink(), sourceContainerNode, targetNode);
		assertNotNull("Link start should be allowed", link); //$NON-NLS-1$		
		// once create, refect 2nd link creation due to multiplicity 0..1
		assertNull("Do not create already existing link", //$NON-NLS-1$ 
				createLink(getRefGenLink(), sourceContainerNode, targetNode));
		assertFalse("Do allow start for 2nd link", //$NON-NLS-1$ 
				canStartLinkFrom(getRefGenLink(), sourceContainerNode));

		// test link with Class
		assertNotNull("Should create link for nodes with equal acceptLinkKind", //$NON-NLS-1$ 
				createLink(getClassGenLink(), sourceContainerNode, targetNode));
		// set different acceptLinkKind
		setBusinessElementStructuralFeature(sourceContainerNode, "acceptLinkKind", null); //$NON-NLS-1$		
		assertTrue("Should start link with no restriction", //$NON-NLS-1$ 
				canStartLinkFrom(getClassGenLink(), sourceContainerNode));
		assertNull("Should not create link for nodes with different acceptLinkKind", //$NON-NLS-1$
				createLink(getClassGenLink(), sourceContainerNode, targetNode));
		// set the same acceptLinkKind to target node
		setBusinessElementStructuralFeature(targetNode, "acceptLinkKind", "kind1"); //$NON-NLS-1$ //$NON-NLS-2$
		setBusinessElementStructuralFeature(sourceContainerNode, "acceptLinkKind", "kind1"); //$NON-NLS-1$ //$NON-NLS-2$
		assertTrue(canStartLinkFrom(getClassGenLink(), sourceContainerNode));
		assertNotNull("Should create link for nodes with equal acceptLinkKind", //$NON-NLS-1$ 
				createLink(getClassGenLink(), sourceContainerNode, targetNode));		
	}

	private GenLink getRefGenLink() {
		return getGenModel().getLinkD();
	}

	private GenNode getSourceGenNode() {
		return getGenModel().getNodeA();
	}

	private GenLink getClassGenLink() {
		return getGenModel().getLinkC();
	}

	private GenNode getTargetGenNode() {
		return getGenModel().getNodeB();
	}

	private boolean canStartLinkFrom(GenLink linkType, View source) {
		Command cmd = getViewerConfiguration().getStartLinkCommand(source, linkType);
		return cmd != null && cmd.canExecute();
	}
	/**
	 * Sets structural feature value of the business element associated with the
	 * given notation element.
	 * 
	 * @param view
	 *            the notation element encapsulating the bussiness object
	 *            containing the feature to be modified
	 * @param featureName
	 *            the name of the structural feature to set.
	 * @param value
	 *            the value to set
	 * @throws IllegalArgumentException
	 *             if the given name does not refer existing feature
	 */
	private void setBusinessElementStructuralFeature(View view, String featureName, Object value) {
		Command command = getViewerConfiguration().getSetBusinessElementStructuralFeatureCommand(view, featureName, value);
		assertNotNull("Command is null", command);
		command.execute();
	}
}
