package org.eclipse.gmf.graphdef.editor.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry;

/**
 * @generated
 */
public class GMFGraphEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public static final String EXTERNAL_NODE_LABELS_LAYER = "External Node Labels";

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			int viewVisualID = GMFGraphVisualIDRegistry.getVisualID(view);
			switch (viewVisualID) {
			case CompartmentEditPart.VISUAL_ID:
				return new CompartmentEditPart(view);
			case Compartment_nameEditPart.VISUAL_ID:
				return new Compartment_nameEditPart(view);
			case NodeEditPart.VISUAL_ID:
				return new NodeEditPart(view);
			case Node_nameEditPart.VISUAL_ID:
				return new Node_nameEditPart(view);
			case ConnectionEditPart.VISUAL_ID:
				return new ConnectionEditPart(view);
			case Connection_nameEditPart.VISUAL_ID:
				return new Connection_nameEditPart(view);
			case FigureGalleryEditPart.VISUAL_ID:
				return new FigureGalleryEditPart(view);
			case FigureGallery_nameEditPart.VISUAL_ID:
				return new FigureGallery_nameEditPart(view);
			case RectangleEditPart.VISUAL_ID:
				return new RectangleEditPart(view);
			case Rectangle2EditPart.VISUAL_ID:
				return new Rectangle2EditPart(view);
			case EllipseEditPart.VISUAL_ID:
				return new EllipseEditPart(view);
			case RoundedRectangleEditPart.VISUAL_ID:
				return new RoundedRectangleEditPart(view);
			case PolylineEditPart.VISUAL_ID:
				return new PolylineEditPart(view);
			case Ellipse2EditPart.VISUAL_ID:
				return new Ellipse2EditPart(view);
			case RoundedRectangle2EditPart.VISUAL_ID:
				return new RoundedRectangle2EditPart(view);
			case Polyline2EditPart.VISUAL_ID:
				return new Polyline2EditPart(view);
			case Compartment_VisualFacetsEditPart.VISUAL_ID:
				return new Compartment_VisualFacetsEditPart(view);
			case Node_VisualFacetsEditPart.VISUAL_ID:
				return new Node_VisualFacetsEditPart(view);
			case Connection_VisualFacetsEditPart.VISUAL_ID:
				return new Connection_VisualFacetsEditPart(view);
			case FigureGallery_FiguresEditPart.VISUAL_ID:
				return new FigureGallery_FiguresEditPart(view);
			case CanvasEditPart.VISUAL_ID:
				return new CanvasEditPart(view);
			case FigureEditPart.VISUAL_ID:
				return new FigureEditPart(view);
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrapLabel)
			return new TextCellEditorLocator((WrapLabel) source.getFigure());
		else {
			IFigure figure = source.getFigure();
			return new LabelCellEditorLocator((Label) figure);
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrapLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrapLabel wrapLabel) {
			super();
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrapLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);

			if (getWrapLabel().isTextWrapped() && getWrapLabel().getText().length() > 0)
				rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
			else {
				int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
			}

			if (!rect.equals(new Rectangle(text.getBounds())))
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
		}

	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);

			int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));

			if (!rect.equals(new Rectangle(text.getBounds())))
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
		}
	}
}
