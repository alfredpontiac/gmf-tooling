package org.eclipse.gmf.ecore.edit.parts;

import org.eclipse.gmf.ecore.edit.policies.EAnnotationItemSemanticEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class EAnnotationEditPart extends ListItemEditPart {

	/**
	 * @generated
	 */
	public EAnnotationEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new EAnnotationItemSemanticEditPolicy());
	}

}
