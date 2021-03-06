/*
 * Copyright (c) 2005, 2010 Borland Software Corporation and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.tests.gen;

import junit.framework.TestCase;

import org.eclipse.gmf.gmfgraph.FigureDescriptor;
import org.eclipse.gmf.gmfgraph.RealFigure;
import org.eclipse.gmf.tests.NeedsSetup;
import org.eclipse.gmf.tests.Plugin;
import org.eclipse.gmf.tests.setup.figures.AbstractFigureGeneratorSetup;
import org.eclipse.gmf.tests.setup.figures.FigureCheck;
import org.eclipse.gmf.tests.setup.figures.FigureGeneratorUtil;

/**
 * @author artem
 */
public class FigureCodegenTestBase<S extends AbstractFigureGeneratorSetup> extends TestCase {

	private S mySessionSetup;

	public FigureCodegenTestBase(String name) {
		super(name);
		Plugin.getConfig().prepare(this);
	}

	@NeedsSetup
	public void configure(S sessionSetup) {
		mySessionSetup = sessionSetup;
	}

	protected S getSessionSetup() {
		return mySessionSetup;
	}

	protected void performTests(RealFigure f, FigureCheck check) {
		FigureGeneratorUtil.generateAndParse(f);
		Class<?> figureClass = mySessionSetup.getFigureClass(f);
		check.go(figureClass);
	}

	protected void performTests(FigureDescriptor f, FigureCheck check) {
		FigureGeneratorUtil.generateAndParse(f);
		Class<?> figureClass = mySessionSetup.getFigureClass(f.getActualFigure());
		check.go(figureClass);
	}
}
