/*
 * Copyright (c) 2006, 2007 Borland Software Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alexander Shatalin (Borland) - initial API and implementation
 */
package org.eclipse.gmf.ecore.edit.policies;

import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.ecore.edit.commands.EEnumLiteralCreateCommand;

import org.eclipse.gmf.ecore.providers.EcoreElementTypes;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class EEnumLiteralsItemSemanticEditPolicy extends EcoreBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (EcoreElementTypes.EEnumLiteral_3009 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(EcorePackage.eINSTANCE.getEEnum_ELiterals());
			}
			return getGEFWrapper(new EEnumLiteralCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
