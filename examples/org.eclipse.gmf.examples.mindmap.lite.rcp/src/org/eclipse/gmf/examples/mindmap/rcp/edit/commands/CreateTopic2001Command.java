/*
 * Copyright (c) 2006, 2007 Borland Software Corporation.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Richard Gronback (Borland) - initial API and implementation
 */

package org.eclipse.gmf.examples.mindmap.rcp.edit.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.examples.mindmap.MindmapFactory;
import org.eclipse.gmf.examples.mindmap.MindmapPackage;
import org.eclipse.gmf.examples.mindmap.rcp.view.factories.DomainElementInitializer;
import org.eclipse.gmf.examples.mindmap.rcp.view.factories.TopicViewFactory;
import org.eclipse.gmf.runtime.lite.commands.CreateNotationalNodeCommand;
import org.eclipse.gmf.runtime.lite.requests.CreateRequestEx;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class CreateTopic2001Command extends AbstractCommand {
	/**
	 * @generated
	 */
	private final CreateNotationalNodeCommand notationAddCommand;

	/**
	 * @generated
	 */
	private final Command domainModelAddCommand;

	/**
	 * @generated
	 */
	public CreateTopic2001Command(View parent, CreateRequestEx request) {
		this(parent, request, null);
	}

	/**
	 * @generated
	 */
	public CreateTopic2001Command(View parent, CreateRequestEx request,
			Rectangle constraint) {
		EObject createdDomainElement = MindmapFactory.eINSTANCE.createTopic();
		DomainElementInitializer.Topic_2001
				.initializeElement(createdDomainElement);
		TransactionalEditingDomain domainModelEditDomain = TransactionUtil
				.getEditingDomain(parent.getDiagram().getElement());
		domainModelAddCommand = AddCommand.create(domainModelEditDomain, parent
				.getElement(), MindmapPackage.eINSTANCE.getMap_RootTopics(),
				createdDomainElement);
		if (constraint != null) {
			constraint.union(new Dimension(40, 40));
		}
		this.notationAddCommand = new CreateNotationalNodeCommand(parent,
				createdDomainElement, constraint, TopicViewFactory.INSTANCE);
		request.setCreatedObject(notationAddCommand.getCreatedView());
	}

	/**
	 * @generated
	 */
	protected boolean prepare() {
		return domainModelAddCommand != null
				&& domainModelAddCommand.canExecute()
				&& notationAddCommand != null
				&& notationAddCommand.canExecute();
	}

	/**
	 * @generated
	 */
	public boolean canUndo() {
		return domainModelAddCommand != null && domainModelAddCommand.canUndo()
				&& notationAddCommand != null && notationAddCommand.canUndo();
	}

	/**
	 * @generated
	 */
	public void execute() {
		domainModelAddCommand.execute();
		notationAddCommand.execute();
	}

	/**
	 * @generated
	 */
	public void undo() {
		notationAddCommand.undo();
		domainModelAddCommand.undo();
	}

	/**
	 * @generated
	 */
	public void redo() {
		execute();
	}
}
