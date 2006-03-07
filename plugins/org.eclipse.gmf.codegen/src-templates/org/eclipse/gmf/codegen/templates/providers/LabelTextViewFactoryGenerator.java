package org.eclipse.gmf.codegen.templates.providers;

import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.common.codegen.*;

public class LabelTextViewFactoryGenerator
{
  protected static String nl;
  public static synchronized LabelTextViewFactoryGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    LabelTextViewFactoryGenerator result = new LabelTextViewFactoryGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "/*" + NL + " * ";
  protected final String TEXT_3 = NL + " */";
  protected final String TEXT_4 = NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_7 = NL + "\textends ";
  protected final String TEXT_8 = " {" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {" + NL + "\t\tsuper.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = "EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();" + NL + "annotation.setSource(\"ViewIdentifier\"); //$NON-NLS-1$" + NL + "view.getEAnnotations().add(annotation);" + NL + "annotation.getDetails().put(\"modelID\", \"";
  protected final String TEXT_11 = "\"); //$NON-NLS-1$" + NL + "annotation.getDetails().put(\"visualID\", \"";
  protected final String TEXT_12 = "\"); //$NON-NLS-1$" + NL + "\t}" + NL + "}";
  protected final String TEXT_13 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
GenLabel label = (GenLabel) ((Object[]) argument)[0];
GenDiagram genDiagram = label.getDiagram();
ImportUtil importManager = new ImportUtil(genDiagram.getNotationViewFactoriesPackageName());

    stringBuffer.append(TEXT_1);
    
String copyrightText = genDiagram.getEditorGen().getCopyrightText();
if (copyrightText != null && copyrightText.trim().length() > 0) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(copyrightText.replaceAll("\n", "\n * "));
    stringBuffer.append(TEXT_3);
    }
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genDiagram.getNotationViewFactoriesPackageName());
    stringBuffer.append(TEXT_5);
    
importManager.markImportLocation(stringBuffer);
importManager.addImport("org.eclipse.core.runtime.IAdaptable");
importManager.addImport("org.eclipse.emf.ecore.EAnnotation");
importManager.addImport("org.eclipse.emf.ecore.EcoreFactory");
importManager.addImport("org.eclipse.gmf.runtime.notation.View");
boolean isFlowLayout = label instanceof GenNodeLabel && !((GenNodeLabel) label).getNode().isListLayout();

    stringBuffer.append(TEXT_6);
    stringBuffer.append(label instanceof ExternalLabel ? ((ExternalLabel) label).getTextNotationViewFactoryClassName() : label.getNotationViewFactoryClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(isFlowLayout ? importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory") : importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory"));
    stringBuffer.append(TEXT_8);
    GenCommonBase genElement = label;
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genDiagram.getEditorGen().getModelID());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genElement.getVisualID());
    stringBuffer.append(TEXT_12);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
}
