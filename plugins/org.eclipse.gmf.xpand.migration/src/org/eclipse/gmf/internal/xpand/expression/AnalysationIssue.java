/*
 * <copyright>
 *
 * Copyright (c) 2005-2006 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.gmf.internal.xpand.expression;

import org.eclipse.gmf.internal.xpand.expression.ast.SyntaxElement;

/**
 * @author Sven Efftinge
 * @author Arno Haase
 */
public class AnalysationIssue {

    public enum Type {
        INCOMPATIBLE_TYPES("Incompatible types"),
        UNNECESSARY_CAST("Unnecessary cast"),
        FEATURE_NOT_FOUND("Callable not found"),
        TYPE_NOT_FOUND ("Type not found"),
        INTERNAL_ERROR("Internal error"),
        JAVA_TYPE_NOT_FOUND("Java Type not found"),
        SYNTAX_ERROR("Syntax error"),
        DEFINITION_NOT_FOUND("Definition not found");

        private String name;

        private Type(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private final Type type;

    private final String message;

    private final SyntaxElement element;

    private final boolean isWarningNotError;

    public AnalysationIssue(final Type type, final String message, final SyntaxElement element) {
    	this(type, message, element, false);
    }

    public AnalysationIssue(final Type type, final String message, final SyntaxElement element, boolean isWarningNotError) {
        this.type = type;
        this.message = message;
        this.element = element;
        this.isWarningNotError = isWarningNotError;
    }

    public boolean isWarningNotError() {
    	return isWarningNotError;
    }

    public SyntaxElement getElement() {
        return element;
    }

    public String getMessage() {
        return message;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + type.name + "] - " + message + " : " + element;
    }
}
