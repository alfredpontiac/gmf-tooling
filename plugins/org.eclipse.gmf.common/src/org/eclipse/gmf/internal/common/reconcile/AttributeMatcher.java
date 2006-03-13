/*
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Michael Golubev (Borland) - initial API and implementation
 */

package org.eclipse.gmf.internal.common.reconcile;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class AttributeMatcher implements Matcher {
	private final String myFeatureName;
	private EClass myCachedFeatureOwner;
	private EStructuralFeature myCachedFeature;

	public AttributeMatcher(String featureName){
		myFeatureName = featureName;
	}
	
	public AttributeMatcher(EStructuralFeature feature){
		this(feature.getName());
	}
	
	public boolean match(EObject current, EObject old) {
		assertCompatible(current, old);
		EStructuralFeature feature = findFeature(current.eClass());
		return safeEquals(current.eGet(feature), old.eGet(feature));
	}
	
	private void assertCompatible(EObject current, EObject old){
		if (!current.eClass().equals(old.eClass())){
			throw new IllegalStateException(MessageFormat.format("Objects not compatible: {0}, {1}", new Object[] {current, old}));
		}
	}
	
	private EStructuralFeature findFeature(EClass eClass){
		if (!eClass.equals(myCachedFeatureOwner)){
			EStructuralFeature result = eClass.getEStructuralFeature(myFeatureName);
			if (result == null){
				throw new IllegalStateException(MessageFormat.format("Unknown feature {0} in EClass {1}", new Object[] {myFeatureName, eClass}));
			}
			myCachedFeatureOwner = eClass;
			myCachedFeature = result;
		}
		return myCachedFeature;
	}
	
	private boolean safeEquals(Object first, Object second){
		return first == null ? second == null : first.equals(second);
	}

}
