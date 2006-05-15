package org.eclipse.gmf.graphdef.editor.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.gmf.graphdef.editor.edit.parts.Compartment_nameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Connection_nameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureGallery_nameEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Node_nameEditPart;
import org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class GMFGraphParserProvider extends AbstractProvider implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser compartmentCompartmentName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getCompartmentCompartmentName_4001Parser() {
		if (compartmentCompartmentName_4001Parser == null) {
			compartmentCompartmentName_4001Parser = createCompartmentCompartmentName_4001Parser();
		}
		return compartmentCompartmentName_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createCompartmentCompartmentName_4001Parser() {
		GMFGraphStructuralFeatureParser parser = new GMFGraphStructuralFeatureParser(GMFGraphPackage.eINSTANCE.getIdentity().getEStructuralFeature("name"));
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser nodeNodeName_4002Parser;

	/**
	 * @generated
	 */
	private IParser getNodeNodeName_4002Parser() {
		if (nodeNodeName_4002Parser == null) {
			nodeNodeName_4002Parser = createNodeNodeName_4002Parser();
		}
		return nodeNodeName_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createNodeNodeName_4002Parser() {
		GMFGraphStructuralFeatureParser parser = new GMFGraphStructuralFeatureParser(GMFGraphPackage.eINSTANCE.getIdentity().getEStructuralFeature("name"));
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser connectionConnectionName_4003Parser;

	/**
	 * @generated
	 */
	private IParser getConnectionConnectionName_4003Parser() {
		if (connectionConnectionName_4003Parser == null) {
			connectionConnectionName_4003Parser = createConnectionConnectionName_4003Parser();
		}
		return connectionConnectionName_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createConnectionConnectionName_4003Parser() {
		GMFGraphStructuralFeatureParser parser = new GMFGraphStructuralFeatureParser(GMFGraphPackage.eINSTANCE.getIdentity().getEStructuralFeature("name"));
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser figureGalleryFigureGalleryName_4004Parser;

	/**
	 * @generated
	 */
	private IParser getFigureGalleryFigureGalleryName_4004Parser() {
		if (figureGalleryFigureGalleryName_4004Parser == null) {
			figureGalleryFigureGalleryName_4004Parser = createFigureGalleryFigureGalleryName_4004Parser();
		}
		return figureGalleryFigureGalleryName_4004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createFigureGalleryFigureGalleryName_4004Parser() {
		GMFGraphStructuralFeatureParser parser = new GMFGraphStructuralFeatureParser(GMFGraphPackage.eINSTANCE.getIdentity().getEStructuralFeature("name"));
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case Compartment_nameEditPart.VISUAL_ID:
			return getCompartmentCompartmentName_4001Parser();
		case Node_nameEditPart.VISUAL_ID:
			return getNodeNodeName_4002Parser();
		case Connection_nameEditPart.VISUAL_ID:
			return getConnectionConnectionName_4003Parser();
		case FigureGallery_nameEditPart.VISUAL_ID:
			return getFigureGalleryFigureGalleryName_4004Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(GMFGraphVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(GMFGraphVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (GMFGraphElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}
}
