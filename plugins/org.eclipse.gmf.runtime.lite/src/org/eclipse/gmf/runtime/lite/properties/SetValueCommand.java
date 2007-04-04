/**
 * Copyright (c) 2006 Eclipse.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    bblajer - initial API and implementation
 */
package org.eclipse.gmf.runtime.lite.properties;

import java.text.MessageFormat;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;

/**
 * Copied from <code>org.eclipse.gef.ui.properties.SetValueCommand</code> to provide EMF compatibility.
 */
class SetValueCommand extends Command {
	protected Object propertyValue;
	protected Object propertyName;
	protected Object undoValue;
	protected boolean resetOnUndo;
	protected IPropertySource target;

	public SetValueCommand() {
		super(""); //$NON-NLS-1$
	}

	public SetValueCommand(String propLabel) {
		super(MessageFormat.format("Set {0} property", new Object[]{propLabel}).trim());
	}

	public boolean canExecute() {
		return true;
	}

	public void execute() {
		/*
		 * Fix for Bug# 54250
		 * IPropertySource.isPropertySet(String) returns false both when there is no default 
		 * value, and when there is a default value and the property is set to that value.
		 * To correctly determine if a reset should be done during undo, we compare the
		 * return value of isPropertySet(String) before and after setPropertyValue(...) is
		 * invoked.  If they are different (it must have been false before and true after --
		 * it cannot be the other way around), then that means we need to reset.
		 */
		boolean wasPropertySet = getTarget().isPropertySet(propertyName);
		undoValue = getTarget().getPropertyValue(propertyName);
		if (undoValue instanceof IPropertySource) {
			undoValue = ((IPropertySource) undoValue).getEditableValue();
		} else if (undoValue instanceof IItemPropertySource) {
			undoValue = ((IItemPropertySource) undoValue).getEditableValue(propertyName);
			if (undoValue instanceof EList) {
				EList theValue = new BasicEList();
				theValue.addAll((EList) undoValue);
				undoValue = theValue;
			}
		}
		if (propertyValue instanceof IPropertySource) {
			propertyValue = ((IPropertySource) propertyValue).getEditableValue();
		}
		getTarget().setPropertyValue(propertyName, propertyValue);
		if (getTarget() instanceof IPropertySource2) {
			resetOnUndo = !wasPropertySet
					&& ((IPropertySource2) getTarget()).isPropertyResettable(propertyName);
		} else {
			resetOnUndo = !wasPropertySet && getTarget().isPropertySet(propertyName);
		}
		if (resetOnUndo) {
			undoValue = null;
		}
	}

	public IPropertySource getTarget() {
		return target;
	}

	public void setTarget(IPropertySource aTarget) {
		target = aTarget;
	}

	public void redo() {
		execute();
	}

	public void setPropertyId(Object pName) {
		propertyName = pName;
	}

	public void setPropertyValue(Object val) {
		propertyValue = val;
	}

	public void undo() {
		if (resetOnUndo) {
			getTarget().resetPropertyValue(propertyName);
		} else {
			getTarget().setPropertyValue(propertyName, undoValue);
		}
	}
}
