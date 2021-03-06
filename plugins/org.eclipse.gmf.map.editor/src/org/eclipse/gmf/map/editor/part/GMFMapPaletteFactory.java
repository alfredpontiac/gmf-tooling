/*
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.map.editor.part;

import java.util.List;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import java.util.ArrayList;

import org.eclipse.gef.palette.PaletteGroup;

import org.eclipse.gmf.map.editor.providers.GMFMapElementTypes;

import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class GMFMapPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createmappings1Group());
	}

	/**
	 * @generated
	 */
	private PaletteContainer createmappings1Group() {
		PaletteContainer paletteContainer = new PaletteGroup("mappings");
		paletteContainer.add(createNode1CreationTool());
		paletteContainer.add(createReference2CreationTool());
		paletteContainer.add(createOwnedNode3CreationTool());
		paletteContainer.add(createReferencedNode4CreationTool());
		paletteContainer.add(createCompartment5CreationTool());
		paletteContainer.add(createCompartmentNode6CreationTool());
		paletteContainer.add(createLink7CreationTool());
		paletteContainer.add(createLabel8CreationTool());
		paletteContainer.add(createFeatureLabel9CreationTool());
		paletteContainer.add(createDesignLabel10CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNode1CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapElementTypes.getImageDescriptor(GMFMapElementTypes.NodeMapping_2003);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFMapElementTypes.NodeMapping_2003);
		ToolEntry result = new NodeToolEntry("Node", "Create new node mapping", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createReference2CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapElementTypes.getImageDescriptor(GMFMapElementTypes.TopNodeReference_3001);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFMapElementTypes.TopNodeReference_3001);
		elementTypes.add(GMFMapElementTypes.ChildReference_3004);
		ToolEntry result = new NodeToolEntry("Reference", "Create new node reference", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOwnedNode3CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapDiagramEditorPlugin.findImageDescriptor("icons/obj16/OwnedNode.gif");

		largeImage = GMFMapDiagramEditorPlugin.findImageDescriptor("icons/obj16/OwnedNode.gif");

		final List relationshipTypes = new ArrayList();
		relationshipTypes.add(GMFMapElementTypes.TopNodeReferenceOwnedChild_4001);
		relationshipTypes.add(GMFMapElementTypes.ChildReferenceOwnedChild_4004);
		ToolEntry result = new LinkToolEntry("Owned Node", "Specify owned node", smallImage, largeImage, relationshipTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createReferencedNode4CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapDiagramEditorPlugin.findImageDescriptor("icons/obj16/ReferencedNode.gif");

		largeImage = GMFMapDiagramEditorPlugin.findImageDescriptor("icons/obj16/ReferencedNode.gif");

		final List relationshipTypes = new ArrayList();
		relationshipTypes.add(GMFMapElementTypes.ChildReferenceReferencedChild_4002);
		ToolEntry result = new LinkToolEntry("Referenced Node", "Specify referenced node", smallImage, largeImage, relationshipTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCompartment5CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapElementTypes.getImageDescriptor(GMFMapElementTypes.CompartmentMapping_3005);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFMapElementTypes.CompartmentMapping_3005);
		ToolEntry result = new NodeToolEntry("Compartment", "Create new compartment mapping", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCompartmentNode6CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapDiagramEditorPlugin.findImageDescriptor("icons/obj16/CompartmentNode.gif");

		largeImage = GMFMapDiagramEditorPlugin.findImageDescriptor("icons/obj16/CompartmentNode.gif");

		final List relationshipTypes = new ArrayList();
		relationshipTypes.add(GMFMapElementTypes.CompartmentMappingChildren_4003);
		ToolEntry result = new LinkToolEntry("Compartment Node", "Specify compartment node", smallImage, largeImage, relationshipTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLink7CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapElementTypes.getImageDescriptor(GMFMapElementTypes.LinkMapping_2002);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFMapElementTypes.LinkMapping_2002);
		ToolEntry result = new NodeToolEntry("Link", "Create new link mapping", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLabel8CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapElementTypes.getImageDescriptor(GMFMapElementTypes.LabelMapping_3002);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFMapElementTypes.LabelMapping_3002);
		elementTypes.add(GMFMapElementTypes.LabelMapping_3003);
		ToolEntry result = new NodeToolEntry("Label", "Create new label mapping", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFeatureLabel9CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapElementTypes.getImageDescriptor(GMFMapElementTypes.FeatureLabelMapping_3006);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFMapElementTypes.FeatureLabelMapping_3006);
		elementTypes.add(GMFMapElementTypes.FeatureLabelMapping_3008);
		ToolEntry result = new NodeToolEntry("Feature Label", "Create new feature label mapping", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDesignLabel10CreationTool() {
		ImageDescriptor smallImage;
		ImageDescriptor largeImage;

		smallImage = GMFMapElementTypes.getImageDescriptor(GMFMapElementTypes.DesignLabelMapping_3007);

		largeImage = smallImage;

		final List elementTypes = new ArrayList();
		elementTypes.add(GMFMapElementTypes.DesignLabelMapping_3007);
		elementTypes.add(GMFMapElementTypes.DesignLabelMapping_3009);
		ToolEntry result = new NodeToolEntry("Design Label", "Create new design label mapping", smallImage, largeImage, elementTypes);

		return result;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description, ImageDescriptor smallIcon, ImageDescriptor largeIcon, List elementTypes) {
			super(title, description, smallIcon, largeIcon);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description, ImageDescriptor smallIcon, ImageDescriptor largeIcon, List relationshipTypes) {
			super(title, description, smallIcon, largeIcon);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
