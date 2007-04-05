/*
 *  Copyright (c) 2006, 2007 Borland Software Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alexander Shatalin (Borland) - initial API and implementation
 */
package org.eclipse.gmf.ecore.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.ecore.edit.policies.EcoreBaseItemSemanticEditPolicy;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class EReferenceReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public EReferenceReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (!(getElementToEdit() instanceof EReference)) {
			return false;
		}
		EReference link = (EReference) getElementToEdit();
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			if (!(oldEnd instanceof EClass && newEnd instanceof EClass)) {
				return false;
			}
			EClass source = (EClass) newEnd;
			EClassifier target = link.getEType();
			return EcoreBaseItemSemanticEditPolicy.LinkConstraints.canExistEReference_4002(source, target);
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			if (!(oldEnd instanceof EClassifier && newEnd instanceof EClassifier)) {
				return false;
			}
			if (!(link.eContainer() instanceof EClass)) {
				return false;
			}
			EClass source = (EClass) link.eContainer();
			EClassifier target = (EClassifier) newEnd;
			return EcoreBaseItemSemanticEditPolicy.LinkConstraints.canExistEReference_4002(source, target);
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	private CommandResult reorientSource() throws ExecutionException {
		EReference link = (EReference) getElementToEdit();
		EClass oldSource = (EClass) oldEnd;
		EClass newSource = (EClass) newEnd;

		oldSource.getEStructuralFeatures().remove(link);
		newSource.getEStructuralFeatures().add(link);
		return CommandResult.newOKCommandResult(link);
	}

	/**
	 * @generated
	 */
	private CommandResult reorientTarget() throws ExecutionException {
		EReference link = (EReference) getElementToEdit();
		EClassifier oldTarget = (EClassifier) oldEnd;
		EClassifier newTarget = (EClassifier) newEnd;

		link.setEType(newTarget);
		return CommandResult.newOKCommandResult(link);
	}
}
