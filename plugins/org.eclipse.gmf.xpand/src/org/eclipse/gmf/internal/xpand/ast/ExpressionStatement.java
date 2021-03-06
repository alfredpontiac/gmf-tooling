/*
 * Copyright (c) 2005, 2009 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *     Artem Tikhomirov (Borland) - Migration to OCL expressions
 */
package org.eclipse.gmf.internal.xpand.ast;

import java.util.Set;

import org.eclipse.gmf.internal.xpand.model.AnalysationIssue;
import org.eclipse.gmf.internal.xpand.model.ExecutionContext;
import org.eclipse.gmf.internal.xpand.ocl.ExpressionHelper;
import org.eclipse.ocl.cst.OCLExpressionCS;

/**
 * @author Sven Efftinge
 */
public class ExpressionStatement extends org.eclipse.gmf.internal.xpand.ast.Statement {

    private final ExpressionHelper expression;

    public ExpressionStatement(final int start, final int end, final int line, final OCLExpressionCS exprCS) {
        super(start, end, line);
        this.expression = new ExpressionHelper(exprCS, this);
    }

    public void analyze(final ExecutionContext ctx, final Set<AnalysationIssue> issues) {
    	expression.analyze(ctx, issues);
    }

    @Override
    public void evaluateInternal(final ExecutionContext ctx) {
    	Object val = expression.evaluate(ctx);
        if (val != null) {
            ctx.getScope().getOutput().write(val.toString());
        }
    }
    
    ExpressionHelper getExpression() {
    	return expression;
    }
    
}
