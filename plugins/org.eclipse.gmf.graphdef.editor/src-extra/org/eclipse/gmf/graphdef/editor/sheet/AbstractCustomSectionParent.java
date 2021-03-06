/*
 *  Copyright (c) 2007 Borland Software Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.sheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public abstract class AbstractCustomSectionParent extends AbstractModelerPropertySection {
	public abstract void doCreateControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage);
	public abstract void doDispose();
	protected abstract ModelUpdater createModelUpdater();

	protected static final String EMPTY_STRING = ""; //$NON-NLS-1$
	
	private TabbedPropertySheetPage myTabbedPropertySheetPage;
	private boolean areControlsCreated;
	private IExpansionListener myExpansionListener;
	private ModelUpdater myListener;
	private Collection mySavedSelection = new ArrayList();

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		myTabbedPropertySheetPage = tabbedPropertySheetPage;
		doCreateControls(parent, tabbedPropertySheetPage);
		areControlsCreated = true;
	}
	
	public void dispose() {
		if (areControlsCreated) {
			doDispose();
			areControlsCreated = false;
		}
		super.dispose();
	}

	public boolean shouldUseExtraSpace() {
		return true;
	}
	
	// XXX [artem] revisit - what's the reason to save copy of selection when it's available from super?
	protected final Collection getSavedSelection() {
		//StructuredSelection structured = (StructuredSelection) super.getSelection();
		//return structured.toList();
		return mySavedSelection;
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (selection.isEmpty() || false == selection instanceof StructuredSelection) {
			super.setInput(part, selection);
			return;
		}
		final StructuredSelection structuredSelection = ((StructuredSelection) selection);
		ArrayList transformedSelection = new ArrayList(structuredSelection.size());
		for (Iterator it = structuredSelection.iterator(); it.hasNext();) {
			Object r = transformSelection(it.next());
			if (r != null) {
				transformedSelection.add(r);
			}
		}
		mySavedSelection = transformedSelection;
		super.setInput(part, new StructuredSelection(transformedSelection));
	}

	protected Object transformSelection(Object selected) {
		if (selected instanceof EditPart) {
			Object model = ((EditPart) selected).getModel();
			return model instanceof View ? ((View) model).getElement() : null;
		}
		if (selected instanceof View) {
			return ((View) selected).getElement();
		}
		if (selected instanceof IAdaptable) {
			View view = (View) ((IAdaptable) selected).getAdapter(View.class);
			if (view != null) {
				return view.getElement();
			}
		}
		return selected;
	}

	protected EObject getEObject() {
		if (getSavedSelection() != null && !getSavedSelection().isEmpty()) {
			return (EObject) getSavedSelection().iterator().next();
		}
		return super.getEObject();
	}
	
	protected ModelUpdater getListener() {
		if (myListener == null) {
			myListener = createModelUpdater();
		}
		return myListener;
	}

	public static abstract class ModelUpdater implements ModifyListener, SelectionListener {
		public abstract void applyChangesFrom(Widget widget);

		private boolean nonUserChange;

		public boolean isNonUserChange() {
			return nonUserChange;
		}

		public void modifyText(ModifyEvent e) {
			if (!isNonUserChange()) {
				applyChangesFrom(e.widget);
			}
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}

		public void widgetSelected(SelectionEvent e) {
			if (!isNonUserChange()) {
				applyChangesFrom(e.widget);
			}
		}

		public void startListeningTo(Spinner spinner) {
			spinner.addModifyListener(this);
			spinner.addSelectionListener(this);
		}

		public void stopListeningTo(Spinner spinner) {
			if (!spinner.isDisposed()) {
				spinner.removeModifyListener(this);
				spinner.removeSelectionListener(this);
			}
		}

		public void startListeningTo(DateTime dateTime) {
			dateTime.addSelectionListener(this);
		}

		public void stopListeningTo(DateTime dateTime) {
			if (!dateTime.isDisposed()) {
				dateTime.removeSelectionListener(this);
			}
		}

		public void startListeningTo(CCombo combo) {
			combo.addSelectionListener(this);
		}

		public void stopListeningTo(CCombo combo) {
			if (!combo.isDisposed()) {
				combo.removeSelectionListener(this);
			}
		}

		public void startListeningTo(Text text) {
			text.addModifyListener(this);
			text.addSelectionListener(this);
		}

		public void stopListeningTo(Text text) {
			if (!text.isDisposed()) {
				text.removeModifyListener(this);
				text.removeSelectionListener(this);
			}
		}

		public void startListeningTo(Button button) {
			button.addSelectionListener(this);
		}

		public void stopListeningTo(Button button) {
			if (!button.isDisposed()) {
				button.removeSelectionListener(this);
			}
		}

		public void startNonUserChange() {
			nonUserChange = true;
		}

		public void finishNonUserChange() {
			nonUserChange = false;
		}
	}

	protected IExpansionListener getExpansionListener(final Composite parent) {
		if (myExpansionListener == null) {
			myExpansionListener = new IExpansionListener() {

				public void expansionStateChanged(ExpansionEvent e) {
					relayoutExpandBar(parent);
				}

				public void expansionStateChanging(ExpansionEvent e) {
				}
			};
		}
		return myExpansionListener;
	}

	protected void relayoutExpandBar(Composite expandBar) {
		Control control = myTabbedPropertySheetPage.getControl();
		Point size = control.getSize();
		control.setSize(size.x - 1, size.y - 1);
		control.setSize(size.x, size.y);
	}

	// helpers used by old handwritten custom sections

	protected Layout createStandardFormLayout() {
		FormLayout layout = new FormLayout();
		layout.marginWidth = ITabbedPropertyConstants.HSPACE + 2;
		layout.marginHeight = ITabbedPropertyConstants.VSPACE;
		layout.spacing = ITabbedPropertyConstants.VMARGIN + 1;
		return layout;
	}

	protected static FormData createFormData(Control leftControl, Control topControl, boolean lowermost) {
		return createFormData(leftControl, topControl, null, lowermost);
	}

	protected static FormData createFormData(Control leftControl, Control topControl, Control rightControl, boolean lowermost) {
		return createFormData(leftControl, topControl, rightControl, null, true, true, lowermost, false);
	}

	protected static FormData createFormData(Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		FormData data = new FormData();
		if (leftControl != null) {
			data.left = new FormAttachment(leftControl);
		} else if (defaultIsLeft) {
			data.left = new FormAttachment(0);
		}
		if (leftControl == null && topControl != null && !defaultIsLeft) {
			Object sd = topControl.getLayoutData();
			if (sd instanceof FormData) {
				FormData topData = (FormData) sd;
				FormAttachment topLeft = topData.left;
				if (topLeft != null && !(topLeft.control instanceof CLabel && (topControl instanceof Spinner || topControl instanceof Text))) {
					data.left = topLeft;
				}
			}
		}
		if (topControl != null) {
			data.top = new FormAttachment(topControl);
			if (topControl instanceof Spinner || topControl instanceof Text) {
				Object sd = topControl.getLayoutData();
				if (sd instanceof FormData) {
					FormData topData = (FormData) sd;
					FormAttachment topLeft = topData.left;
					if (topLeft != null && topLeft.control instanceof CLabel) {
						data.top = topLeft;
					}
				}
			}
		} else if (defaultIsTop) {
			data.top = new FormAttachment(0);
		}
		if (topControl == null && leftControl != null && !defaultIsTop) {
			Object sd = leftControl.getLayoutData();
			if (sd instanceof FormData) {
				FormData topData = (FormData) sd;
				FormAttachment leftTop = topData.top;
				if (leftTop != null) {
					data.top = leftTop;
					if (leftTop.control instanceof Spinner || leftTop.control instanceof Text) {
						Object sd2 = leftTop.control.getLayoutData();
						if (sd2 instanceof FormData) {
							FormData topDataLeftTop = (FormData) sd2;
							FormAttachment topLeft = topDataLeftTop.left;
							if (topLeft != null && topLeft.control instanceof CLabel) {
								data.top = topLeft;
							}
						}
					}
				}
			}
		}
		if (rightControl != null) {
			data.right = new FormAttachment(rightControl);
		} else if (rightmost) {
			data.right = new FormAttachment(100);
		}
		if (bottomControl != null) {
			data.bottom = new FormAttachment(bottomControl);
		} else if (lowermost) {
			data.bottom = new FormAttachment(100);
		}
		return data;
	}

	protected static FormData createFillFormData(Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost, boolean fillHorizonral, boolean fillVertical) {
		FormData data = new FormData();
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.top = new FormAttachment(0);
		data.bottom = new FormAttachment(100);
		return data;
	}
	
	protected Composite createFlatFormComposite(Composite parent, int leftPosition) {
		return createFlatFormComposite(parent, leftPosition, false);
	}

	protected Composite createFlatFormComposite(Composite parent, int leftPosition, boolean noData) {
		return createFlatFormComposite(parent, null, null, null, null, false, true, false, true, leftPosition, noData);
	}

	protected Composite createFlatFormComposite(Composite parent, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost, int leftPosition, boolean noData) {
		Composite pane = getWidgetFactory().createFlatFormComposite(parent);
		if (!noData) {
			FormData data = createFormData(leftControl, topControl, rightControl, bottomControl, defaultIsLeft, defaultIsTop, lowermost, rightmost);
			if (leftPosition != SWT.DEFAULT) {
				data.left = new FormAttachment(leftPosition);
			}
			pane.setLayoutData(data);
		}
		return pane;
	}
	
	protected ExpandableComposite createLayoutParamsExpandableComposite(Composite parent, String label, Control topControl, boolean rightmost) {
		return createExpandableComposite(parent, label, null, topControl, false, rightmost);
	}
	
	protected ExpandableComposite createExpandableComposite(Composite parent, String label, Control leftControl, Control topControl, boolean lowermost, boolean rightmost) {
		ExpandableComposite expandable = getWidgetFactory().createExpandableComposite(parent, ExpandableComposite.TWISTIE | ExpandableComposite.CLIENT_INDENT | ExpandableComposite.TITLE_BAR);
		expandable.setText(label);
		expandable.setBackground(getWidgetFactory().getHyperlinkGroup().getBackground());
		expandable.setActiveToggleColor(getWidgetFactory().getHyperlinkGroup().getActiveForeground());
		expandable.setTitleBarForeground(getWidgetFactory().getHyperlinkGroup().getForeground());
		FormData data = createFormData(leftControl, topControl, null, null, true, true, lowermost, rightmost);
		expandable.setLayoutData(data);
		return expandable;
	}

	protected Group createGroup(Composite parent, String label) {
		return createGroup(parent, label, false);
	}

	protected Group createGroup(Composite parent, String label, boolean noData) {
		return createGroup(parent, label, null, null, noData);
	}

	protected Group createGroup(Composite parent, String label, Control leftComposite, Control topComposite) {
		return createGroup(parent, label, leftComposite, topComposite, false);
	}
	
	protected Group createGroup(Composite parent, String label, Control leftComposite, Control topComposite, boolean noData) {
		return createGroup(parent, label, leftComposite, topComposite, true, true, false, false, noData);
	}

	protected Group createGroup(Composite parent, String label, Control leftComposite, Control topComposite, boolean defaultIsTop, boolean defaultIsLeft, boolean lowermost, boolean rightmost, boolean noData) {
		Group group = getWidgetFactory().createGroup(parent, label);
		if (!noData) {
			FormData data = createFormData(leftComposite, topComposite, null, null, defaultIsLeft, defaultIsTop, lowermost, rightmost);
			group.setLayoutData(data);
		}
		group.setLayout(createStandardFormLayout());
		return group;
	}

	protected Button createRadio(Composite parent, Control topControl, boolean lowermost, Image image) {
		return createRadio(parent, image, EMPTY_STRING, 0, SWT.DEFAULT, topControl, 0, lowermost);
	}

	protected Button createRadio(Composite parent, String label, Control topControl, int rightAlignment) {
		return createRadio(parent, null, label, 0, rightAlignment, topControl, SWT.DEFAULT, false);
	}

	protected Button createRadio(Composite parent, String label, Control topControl, boolean lowermost) {
		return createRadio(parent, null, label, 0, SWT.DEFAULT, topControl, SWT.DEFAULT, lowermost);
	}

	protected Button createRadio(Composite parent, Image image, String label, int leftAlignment, int rightAlignment, int topAlignment, boolean lowermost) {
		return createRadio(parent, image, label, leftAlignment, rightAlignment, null, topAlignment, lowermost);
	}
	
	protected Button createRadio(Composite parent, Image image, String label, int leftAlignment, int rightAlignment, Control topControl, int topAlignment, boolean lowermost) {
		return createRadio(parent, image, label, null, leftAlignment, topControl, topAlignment, null, rightAlignment, true, true, lowermost, false);
	}

	protected Button createRadio(Composite parent, Image image, String label, Control leftControl, int leftAlignment, Control topControl, int topAlignment, Control rightControl, int rightAlignment, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		Button radio = getWidgetFactory().createButton(parent, label == null ? EMPTY_STRING : label, SWT.RADIO);
		if (image != null) {
			radio.setImage(image);
		}
		FormData data = createFormData(leftControl, topControl, rightControl, null, defaultIsLeft, defaultIsTop, lowermost, rightmost);
		if (leftControl == null && SWT.DEFAULT != leftAlignment) {
			data.left = new FormAttachment(leftAlignment);
		}
		if (rightControl == null && SWT.DEFAULT != rightAlignment) {
			data.right = new FormAttachment(rightAlignment);
		}
		if (topControl == null && SWT.DEFAULT != topAlignment) {
			data.top = new FormAttachment(topAlignment);
		}
		radio.setLayoutData(data);
		return radio;
	}
	
	protected Button createCheckbox(Composite parent, String label, Control topControl, Control leftControl) {
		return createCheckbox(parent, label, topControl, leftControl, false);
	}

	protected Button createCheckbox(Composite parent, String label, Control topControl, Control leftControl, boolean lowermost) {
		return createCheckbox(parent, label, topControl, leftControl, true, true, lowermost, SWT.DEFAULT);
	}

	protected Button createCheckbox(Composite parent, String label, Control topControl, Control leftControl, boolean defaultIsTop, boolean defaultIsLeft, boolean lowermost, int leftAlignment) {
		Button checkbox = getWidgetFactory().createButton(parent, label, SWT.CHECK);
		FormData data = createFormData(leftControl, topControl, null, null, defaultIsLeft, defaultIsTop, lowermost, false);
		if (leftAlignment != SWT.DEFAULT) {
			data.left = new FormAttachment(leftAlignment);
		}
		checkbox.setLayoutData(data);
		return checkbox;
	}

	protected Spinner createSpinnerWidget(Composite parent, String label, Control leftWidget) {
		return createSpinnerWidget(parent, label, leftWidget, null, false, false);
	}

	protected Spinner createSpinnerWidget(Composite parent, String label, Control leftWidget, Control topWidget, boolean rightlast, boolean horizontalNotVertical) {
		return createSpinnerWidget(parent, label, leftWidget, topWidget, false, rightlast, horizontalNotVertical, 1, 999, 1, 100);
	}
	
	protected Spinner createSpinnerWidget(Composite parent, String label, Control leftControl, Control topControl, boolean lowermost, boolean rightmost, boolean horizontalNotVertical, int min, int max, int inc, int pageInc) {
		Spinner spinner = new Spinner(parent, SWT.BORDER | (horizontalNotVertical ? SWT.H_SCROLL : SWT.V_SCROLL));
		spinner.setMinimum(min);
		spinner.setMaximum(max);
		spinner.setIncrement(inc);
		spinner.setPageIncrement(pageInc);
		spinner.setBackground(parent.getBackground());
		spinner.setForeground(parent.getForeground());
		Control leftWidget = leftControl;
		if (label != null && label.length() != 0) {
			leftWidget = createLabelWidget(parent, label, leftControl, topControl);
		}
		FormData data = createFormData(leftWidget, topControl, null, null, true, true, lowermost, rightmost);
		spinner.setLayoutData(data);
		return spinner;
	}

	protected Text createText(Composite parent, String label, Control leftWidget, Control topWidget, String initialText) {
		return createText(parent, label, leftWidget, topWidget, initialText, true, true, false, true);
	}
	
	protected Text createText(Composite parent, String label, Control leftControl, Control topControl, String initialText, boolean defaultIsTop, boolean defaultIsLeft, boolean lowermost, boolean rightmost) {
		Text text = getWidgetFactory().createText(parent, initialText, SWT.BORDER);
		Control leftWidget = leftControl;
		if (label != null && label.length() != 0) {
			leftWidget = createLabelWidget(parent, label, leftControl, topControl);
		}
		FormData data = createFormData(leftWidget, topControl, null, null, defaultIsLeft, defaultIsTop, lowermost, rightmost);
		text.setLayoutData(data);
		return text;
	}

	protected Text createExtendedText(Composite parent, String label, Control leftControl, Control topControl, String initialText, boolean defaultIsTop, boolean defaultIsLeft, boolean lowermost, boolean rightmost) {
		Control leftWidget = leftControl;
		if (label != null && label.length() != 0) {
			leftWidget = createLabelWidget(parent, label, leftControl, topControl);
		}
		Text text = getWidgetFactory().createText(parent, initialText, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP);
		FormData data = createFormData(leftWidget, topControl, null, null, true, true, true, true);
		text.setLayoutData(data);
		return text;
	}

	protected CLabel createLabelWidget(Composite parent, String labelText, Control leftWidget) {
		return createLabelWidget(parent, labelText, leftWidget, null);
	}

	protected CLabel createLabelWidget(Composite parent, String labelText, Control leftWidget, Control topWidget) {
		CLabel label = getWidgetFactory().createCLabel(parent, labelText);
		FormData data = createFormData(leftWidget, topWidget, null, false);
		label.setLayoutData(data);
		return label;
	}
	
	private DateTime createDate(Composite parent, String label, Control leftControl, Control topControl, boolean defaultIsTop, boolean defaultIsLeft, boolean lowermost, boolean rightmost) {
		DateTime date = new DateTime(parent, SWT.DATE);
		date.setBackground(parent.getBackground());
		date.setForeground(parent.getForeground());
		getWidgetFactory().adapt(date);
		Control leftWidget = leftControl;
		if (label != null && label.length() != 0) {
			leftWidget = createLabelWidget(parent, label, leftControl, topControl);
		}
		FormData data = createFormData(leftWidget, topControl, null, null, defaultIsLeft, defaultIsTop, lowermost, rightmost);
		date.setLayoutData(data);
		return date;
	}
	
	private DateTime createTime(Composite parent, String label, Control leftControl, Control topControl, boolean defaultIsTop, boolean defaultIsLeft, boolean lowermost, boolean rightmost) {
		DateTime time = new DateTime(parent, SWT.TIME);
		time.setBackground(parent.getBackground());
		time.setForeground(parent.getForeground());
		getWidgetFactory().adapt(time);
		Control leftWidget = leftControl;
		if (label != null && label.length() != 0) {
			leftWidget = createLabelWidget(parent, label, leftControl, topControl);
		}
		FormData data = createFormData(leftWidget, topControl, null, null, defaultIsLeft, defaultIsTop, lowermost, rightmost);
		time.setLayoutData(data);
		return time;
	}
	
	private CCombo createCombo(Composite parent, String label, Control leftControl, Control topControl, boolean defaultIsTop, boolean defaultIsLeft, boolean lowermost, boolean rightmost) {
		CCombo combo = getWidgetFactory().createCCombo(parent, SWT.NONE);
		Control leftWidget = leftControl;
		if (label != null && label.length() != 0) {
			leftWidget = createLabelWidget(parent, label, leftControl, topControl);
		}
		FormData data = createFormData(leftWidget, topControl, null, null, defaultIsLeft, defaultIsTop, lowermost, rightmost);
		combo.setLayoutData(data);
		return combo;
	}
	
	// these ones are used in model customization example templates!

	public Composite createMainWidget(Composite parent) {
		return createFlatFormComposite(parent, 0, true);
	}

	public Composite createCompositeWidget(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createFlatFormComposite(parent, leftControl, topControl, rightControl, bottomControl, defaultIsLeft, defaultIsTop, lowermost, rightmost, SWT.DEFAULT, false);
	}

	public ExpandableComposite createExpandableComposite(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createExpandableComposite(parent, label, leftControl, topControl, lowermost, rightmost);
	}

	public Group createGroupWidget(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		Group group = createGroup(parent, label, leftControl, topControl, defaultIsTop, defaultIsLeft, lowermost, rightmost, false);
		return group;
	}

	public Button createRadioButton(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createRadio(parent, null, label, leftControl, SWT.DEFAULT, topControl, SWT.DEFAULT, rightControl, SWT.DEFAULT, defaultIsLeft, defaultIsTop, lowermost, rightmost);
	}

	public Button createCheckboxButton(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createCheckbox(parent, label, topControl, leftControl, defaultIsTop, defaultIsLeft, lowermost, SWT.DEFAULT);
	}
	
	public Spinner createSpinnerWidget(Composite parent, String label, int min, int max, int inc, int pageInc, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createSpinnerWidget(parent, label, leftControl, topControl, lowermost, rightmost, false, min, max, inc, pageInc);
	}
	
	public Text createTextWidget(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createText(parent, label, leftControl, topControl, EMPTY_STRING, defaultIsTop, defaultIsLeft, lowermost, rightmost);
	}

	public Text createExtendedTextWidget(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		Text extendedText = createExtendedText(parent, label, leftControl, topControl, EMPTY_STRING, defaultIsTop, defaultIsLeft, lowermost, rightmost);
		return extendedText;
	}

	public CLabel createLabelWidget(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createLabelWidget(parent, label, leftControl, topControl);
	}

	public DateTime createDateWidget(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createDate(parent, label, leftControl, topControl, defaultIsTop, defaultIsLeft, lowermost, rightmost);
	}

	public DateTime createTimeWidget(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createTime(parent, label, leftControl, topControl, defaultIsTop, defaultIsLeft, lowermost, rightmost);
	}

	public CCombo createComboBox(Composite parent, String label, Control leftControl, Control topControl, Control rightControl, Control bottomControl, boolean defaultIsLeft, boolean defaultIsTop, boolean lowermost, boolean rightmost) {
		return createCombo(parent, label, leftControl, topControl, defaultIsTop, defaultIsLeft, lowermost, rightmost);
	}

}
