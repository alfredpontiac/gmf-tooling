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
package org.eclipse.gmf.tests.setup.annotated;

import java.util.Iterator;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.codegen.gmfgen.GenDiagram;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.gmfgen.GenLink;
import org.eclipse.gmf.codegen.gmfgen.GenNode;
import org.eclipse.gmf.internal.bridge.NaiveIdentifierDispenser;
import org.eclipse.gmf.internal.bridge.genmodel.BasicDiagramRunTimeModelHelper;
import org.eclipse.gmf.internal.bridge.genmodel.DiagramGenModelTransformer;
import org.eclipse.gmf.internal.bridge.genmodel.InnerClassViewmapProducer;
import org.eclipse.gmf.internal.bridge.naming.gen.GenModelNamingMediatorImpl;
import org.eclipse.gmf.mappings.Mapping;
import org.eclipse.gmf.tests.Utils;
import org.eclipse.gmf.tests.setup.DiaGenSource;

/**
 * @author dstadnik
 */
public class GenASetup extends AbstractASetup implements DiaGenSource {

	protected Mapping mapping;

	protected boolean rcp;

	protected GenEditorGenerator gen;

	public GenASetup(Mapping mapping, boolean rcp) {
		this.mapping = mapping;
		this.rcp = rcp;
	}

	protected Iterator getAllDomainModelContents() {
		return null;
	}

	// source

	public GenDiagram getGenDiagram() {
		if (gen == null) {
			DiagramGenModelTransformer t = new DiagramGenModelTransformer(new BasicDiagramRunTimeModelHelper(), new GenModelNamingMediatorImpl(), new InnerClassViewmapProducer(), new NaiveIdentifierDispenser(), rcp);
			EPackage ePackage = mapping.getDiagram().getDomainModel();
			if (ePackage != null) {
				t.setEMFGenModel(Utils.createGenModel(ePackage));
			}
			t.transform(mapping);
			gen = t.getResult();
			saveModel(gen, "gmfgen"); //$NON-NLS-1$
			validate(gen);
		}
		return gen.getDiagram();
	}

	public GenNode getNodeA() {
		return null;
	}

	public GenNode getNodeB() {
		return null;
	}

	public GenLink getLinkC() {
		return null;
	}

	public GenLink getLinkD() {
		return null;
	}
}
