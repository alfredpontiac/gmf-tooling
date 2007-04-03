package org.eclipse.gmf.codegen.templates.policies;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.common.codegen.*;

public class ChildContainerCanonicalEditPolicyGenerator {
 
  protected static String nl;
  public static synchronized ChildContainerCanonicalEditPolicyGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    ChildContainerCanonicalEditPolicyGenerator result = new ChildContainerCanonicalEditPolicyGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "/*" + NL + " *";
  protected final String TEXT_3 = NL + " */";
  protected final String TEXT_4 = NL + NL + "import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;" + NL + "import org.eclipse.gmf.runtime.notation.View;";
  protected final String TEXT_5 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_6 = " extends CanonicalEditPolicy {" + NL;
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = "/**" + NL + " * @generated" + NL + " */" + NL + "protected ";
  protected final String TEXT_9 = " getSemanticChildrenList() {";
  protected final String TEXT_10 = NL + "\t";
  protected final String TEXT_11 = " result = new ";
  protected final String TEXT_12 = "();" + NL + "\t";
  protected final String TEXT_13 = " modelObject = ((";
  protected final String TEXT_14 = ") getHost().getModel()).getElement();" + NL + "\t";
  protected final String TEXT_15 = " viewObject = (";
  protected final String TEXT_16 = ") getHost().getModel();";
  protected final String TEXT_17 = NL;
  protected final String TEXT_18 = "\t";
  protected final String TEXT_19 = " nextValue;" + NL + "\tint nodeVID;";
  protected final String TEXT_20 = "\tfor (";
  protected final String TEXT_21 = " values = ";
  protected final String TEXT_22 = ".iterator(); values.hasNext();) {" + NL + "\t\tnextValue = (";
  protected final String TEXT_23 = ") values.next();";
  protected final String TEXT_24 = NL + "\tnextValue = ";
  protected final String TEXT_25 = ";";
  protected final String TEXT_26 = NL + "\tnodeVID = ";
  protected final String TEXT_27 = ".getNodeVisualID(viewObject, nextValue);";
  protected final String TEXT_28 = NL + "\tswitch (nodeVID) {";
  protected final String TEXT_29 = NL + "\tcase ";
  protected final String TEXT_30 = ".VISUAL_ID: {";
  protected final String TEXT_31 = NL + "\tif (";
  protected final String TEXT_32 = ".VISUAL_ID == nodeVID) {";
  protected final String TEXT_33 = NL + "\t\tresult.add(nextValue);";
  protected final String TEXT_34 = NL;
  protected final String TEXT_35 = "\t\tbreak;";
  protected final String TEXT_36 = "\t}";
  protected final String TEXT_37 = NL + "\t}";
  protected final String TEXT_38 = NL + "\t}";
  protected final String TEXT_39 = NL + "\treturn result;";
  protected final String TEXT_40 = NL + "\treturn ";
  protected final String TEXT_41 = ".EMPTY_LIST;";
  protected final String TEXT_42 = NL + "}" + NL + "" + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "protected boolean shouldDeleteView(";
  protected final String TEXT_43 = " view) {" + NL + "\tif (view.getEAnnotation(\"Shortcut\") != null) { //$NON-NLS-1$" + NL + "\t\treturn view.isSetElement() && (view.getElement() == null || view.getElement().eIsProxy());" + NL + "\t}";
  protected final String TEXT_44 = NL + "\tint nodeVID = ";
  protected final String TEXT_45 = ".getVisualID(view);" + NL + "\tswitch (nodeVID) {\t";
  protected final String TEXT_46 = NL + "\tcase ";
  protected final String TEXT_47 = ".VISUAL_ID:";
  protected final String TEXT_48 = NL + "\t\treturn true;" + NL + "\t}";
  protected final String TEXT_49 = NL + "\treturn false;" + NL + "}" + NL + "" + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "protected String getDefaultFactoryHint() {" + NL + "\treturn null;" + NL + "}" + NL + "\t";
  protected final String TEXT_50 = NL;
  protected final String TEXT_51 = NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void refreshSemantic() {" + NL + "\t\t";
  protected final String TEXT_52 = " createdViews = new ";
  protected final String TEXT_53 = "();" + NL + "\t\tcreatedViews.addAll(refreshSemanticChildren());" + NL + "\t\tcreatedViews.addAll(refreshPhantoms());" + NL + "\t\tmakeViewsImmutable(createdViews);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_54 = " refreshPhantoms() {" + NL + "\t\t";
  protected final String TEXT_55 = " phantomNodes = new ";
  protected final String TEXT_56 = "();" + NL + "\t\t";
  protected final String TEXT_57 = " container = ((";
  protected final String TEXT_58 = ") getHost().getModel()).getElement();" + NL + "\t\t";
  protected final String TEXT_59 = " containerMetaclass = container.eClass();" + NL + "\t\t";
  protected final String TEXT_60 = " diagram = getDiagram();";
  protected final String TEXT_61 = NL;
  protected final String TEXT_62 = "\tif (";
  protected final String TEXT_63 = ".eINSTANCE.get";
  protected final String TEXT_64 = "().isSuperTypeOf(containerMetaclass)) {";
  protected final String TEXT_65 = NL + "\t\tfor (";
  protected final String TEXT_66 = " destinations = ";
  protected final String TEXT_67 = ".iterator(); destinations.hasNext();) {" + NL + "\t\t\t";
  protected final String TEXT_68 = " nextDestination = (";
  protected final String TEXT_69 = ") destinations.next();";
  protected final String TEXT_70 = NL + "\t\t";
  protected final String TEXT_71 = " nextDestination = (";
  protected final String TEXT_72 = ") ";
  protected final String TEXT_73 = ";";
  protected final String TEXT_74 = NL + "\t\tif (";
  protected final String TEXT_75 = ".VISUAL_ID == ";
  protected final String TEXT_76 = ".getNodeVisualID(diagram, nextDestination)) {";
  protected final String TEXT_77 = NL + "\t\t\tphantomNodes.add(nextDestination);";
  protected final String TEXT_78 = NL;
  protected final String TEXT_79 = NL + "\t\t}";
  protected final String TEXT_80 = NL + "\t\t}";
  protected final String TEXT_81 = NL + "\t}";
  protected final String TEXT_82 = NL;
  protected final String TEXT_83 = NL;
  protected final String TEXT_84 = "for (";
  protected final String TEXT_85 = " diagramNodes = getDiagram().getChildren().iterator(); diagramNodes.hasNext();) {" + NL + "\t";
  protected final String TEXT_86 = " nextView = (";
  protected final String TEXT_87 = ") diagramNodes.next();" + NL + "\t";
  protected final String TEXT_88 = " nextViewElement = nextView.getElement();" + NL + "\tif (phantomNodes.contains(nextViewElement)) {" + NL + "\t\tphantomNodes.remove(nextViewElement);" + NL + "\t}\t" + NL + "}" + NL + "\t\treturn createPhantomNodes(phantomNodes);" + NL + "\t}" + NL + "\t";
  protected final String TEXT_89 = NL;
  protected final String TEXT_90 = "/**" + NL + " * @generated" + NL + " */" + NL + "private ";
  protected final String TEXT_91 = " createPhantomNodes(";
  protected final String TEXT_92 = " nodes) {" + NL + "\tif (nodes.isEmpty()) {" + NL + "\t\treturn ";
  protected final String TEXT_93 = ".EMPTY_LIST;" + NL + "\t}" + NL + "\t";
  protected final String TEXT_94 = " descriptors = new ";
  protected final String TEXT_95 = "();" + NL + "\tfor (";
  protected final String TEXT_96 = " elements = nodes.iterator(); elements.hasNext();) {" + NL + "\t\t";
  protected final String TEXT_97 = " element = (";
  protected final String TEXT_98 = ")elements.next();" + NL + "\t\t";
  protected final String TEXT_99 = ".ViewDescriptor descriptor = getViewDescriptor(element);" + NL + "\t\tdescriptors.add(descriptor);" + NL + "\t}" + NL + "\t";
  protected final String TEXT_100 = " diagram = getDiagram();" + NL + "\t";
  protected final String TEXT_101 = " diagramEditPart = getDiagramEditPart();" + NL + "\t" + NL + "\t";
  protected final String TEXT_102 = " request = getCreateViewRequest(descriptors);" + NL + "\t";
  protected final String TEXT_103 = " cmd = diagramEditPart.getCommand(request);" + NL + "\tif (cmd == null) {" + NL + "\t\t";
  protected final String TEXT_104 = " cc = new ";
  protected final String TEXT_105 = "(";
  protected final String TEXT_106 = ".AddCommand_Label);" + NL + "\t\tfor (";
  protected final String TEXT_107 = " descriptorsIterator = descriptors.iterator(); descriptorsIterator.hasNext();) {" + NL + "\t\t\t";
  protected final String TEXT_108 = ".ViewDescriptor descriptor = (";
  protected final String TEXT_109 = ".ViewDescriptor) descriptorsIterator.next();" + NL + "\t\t\t";
  protected final String TEXT_110 = " createCommand = new ";
  protected final String TEXT_111 = "(((";
  protected final String TEXT_112 = ") getHost()).getEditingDomain(), descriptor, diagram);" + NL + "\t\t\tcc.compose(createCommand);" + NL + "\t\t}" + NL + "\t\tcmd = new ";
  protected final String TEXT_113 = "(cc);" + NL + "\t}\t" + NL + "\t" + NL + "\t";
  protected final String TEXT_114 = " adapters = ";
  protected final String TEXT_115 = ".EMPTY_LIST;" + NL + "\tif ( cmd != null && cmd.canExecute() ) {" + NL + "\t\t";
  protected final String TEXT_116 = ".makeMutable(new ";
  protected final String TEXT_117 = "(((";
  protected final String TEXT_118 = ") diagramEditPart).getNotationView())).execute();" + NL + "\t\texecuteCommand(cmd);" + NL + "\t\tadapters = (";
  protected final String TEXT_119 = ") request.getNewObject();" + NL + "\t}" + NL + "\tdiagramEditPart.refresh();" + NL + "\treturn adapters;" + NL + "}" + NL + "" + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "private ";
  protected final String TEXT_120 = " getDiagramEditPart() {" + NL + "\treturn (";
  protected final String TEXT_121 = ") getHost().getViewer().getEditPartRegistry().get(getDiagram());" + NL + "}" + NL;
  protected final String TEXT_122 = NL;
  protected final String TEXT_123 = "/**" + NL + " * @generated" + NL + " */" + NL + "private ";
  protected final String TEXT_124 = " getDiagram() {" + NL + "\treturn ((";
  protected final String TEXT_125 = ") getHost().getModel()).getDiagram();" + NL + "}";
  protected final String TEXT_126 = NL + NL + "}";
  protected final String TEXT_127 = NL;

	protected final String getFeatureValueGetter(String containerName, GenFeature feature, boolean isContainerEObject, ImportAssistant importManager) {
		StringBuffer result = new StringBuffer();
		if (feature.getGenClass().isExternalInterface()) {
// Using EMF reflective method to access feature value
			result.append("((");
			if (feature.isListType()) {
				result.append(importManager.getImportedName("java.util.Collection"));
			} else {
				if (feature.getTypeGenClass() != null) {
					result.append(importManager.getImportedName(feature.getTypeGenClass().getQualifiedInterfaceName()));
				} else {
					// EDataType
					result.append(importManager.getImportedName(feature.getTypeGenClassifier().getEcoreClassifier().getInstanceClassName()));
				}

			}
			result.append(")");
			if (!isContainerEObject) {
// Casting container to EObject - ExternalIntarfce could be not an instance of EObject
				result.append("((");
				result.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
				result.append(")");
			}
			result.append(containerName);
			if (!isContainerEObject) {
				result.append(")");
			}
			result.append(".eGet(");
			result.append(importManager.getImportedName(feature.getGenPackage().getQualifiedPackageInterfaceName()));
			result.append(".eINSTANCE.get");
			result.append(feature.getFeatureAccessorName());
			result.append("()))");
		} else {
			if (isContainerEObject) {
// Casting container to the typed interface
				result.append("((");
				result.append(importManager.getImportedName(feature.getGenClass().getQualifiedInterfaceName()));
				result.append(")");
			}
			result.append(containerName);
			if (isContainerEObject) {
				result.append(")");
			}
			result.append(".");
			result.append(feature.getGetAccessor());
			result.append("()");
		}
		return result.toString();
	}
	
	protected final String getFeatureValueSetterPrefix(String containerName, GenFeature feature, boolean isContainerEObject, ImportAssistant importManager) {
		StringBuffer result = new StringBuffer();
		if (feature.getGenClass().isExternalInterface()) {
// Using EMF reflective method to access feature value
			if (!isContainerEObject) {
// Casting container to EObject - ExternalIntarfce could be not an instance of EObject
				result.append("((");
				result.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
				result.append(")");
			}
			result.append(containerName);
			if (!isContainerEObject) {
				result.append(")");
			}
			result.append(".eSet(");
			result.append(importManager.getImportedName(feature.getGenPackage().getQualifiedPackageInterfaceName()));
			result.append(".eINSTANCE.get");
			result.append(feature.getFeatureAccessorName());
			result.append("(), ");
		} else {
			if (isContainerEObject) {
// Casting container to the typed interface
				result.append("((");
				result.append(importManager.getImportedName(feature.getGenClass().getQualifiedInterfaceName()));
				result.append(")");
			}
			result.append(containerName);
			if (isContainerEObject) {
				result.append(")");
			}
			if (feature.isListType()) {
				result.append(".");
				result.append(feature.getGetAccessor());
				result.append("().add(");
			} else {
				result.append(".set");
				result.append(feature.getAccessorName());
				result.append("(");
			}
		}
		return result.toString();
	}
 
	protected final String getMetaClassAccessor(GenClass genClass, ImportAssistant importManager) {
		StringBuffer buf = new StringBuffer();
		buf.append(importManager.getImportedName(genClass.getGenPackage().getQualifiedPackageInterfaceName()))
			.append(".eINSTANCE.get") //$NON-NLS-1$
			.append(genClass.getName())
			.append("()"); //$NON-NLS-1$
		return buf.toString();
	} 
 
	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final GenChildContainer genContainer = (GenChildContainer) ((Object[]) argument)[0];
final ImportAssistant importManager = (ImportAssistant) ((Object[]) argument)[1];
GenDiagram genDiagram = genContainer.getDiagram();

    stringBuffer.append(TEXT_1);
    
String copyrightText = genDiagram.getEditorGen().getCopyrightText();
if (copyrightText != null && copyrightText.trim().length() > 0) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(copyrightText.replaceAll("\n", "\n *"));
    stringBuffer.append(TEXT_3);
    }
    importManager.emitPackageStatement(stringBuffer);
    stringBuffer.append(TEXT_4);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genContainer.getCanonicalEditPolicyClassName());
    stringBuffer.append(TEXT_6);
    List genNodes = genContainer.getChildNodes();
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_9);
    if (genNodes.size() > 0) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(importManager.getImportedName("java.util.LinkedList"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    
Map genFeature2genNodeMap = new LinkedHashMap();
for (int nodeIndex = 0; nodeIndex < genNodes.size(); nodeIndex++) {
	GenNode nextNode = (GenNode) genNodes.get(nodeIndex);
	TypeModelFacet typeModelFacet = nextNode.getModelFacet();
	if (typeModelFacet.isPhantomElement()) {
		// Skipping top-level phantoms
		continue;
	}
	GenFeature childMetaFeature = typeModelFacet.getChildMetaFeature();
	if (!genFeature2genNodeMap.containsKey(childMetaFeature)) {
		genFeature2genNodeMap.put(childMetaFeature, new ArrayList());
	}
	((Collection) genFeature2genNodeMap.get(childMetaFeature)).add(nextNode);
}

Set entrySet = genFeature2genNodeMap.entrySet();
if (entrySet.size() > 0) {

    stringBuffer.append(TEXT_18);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_19);
    
}

for (Iterator entries = entrySet.iterator(); entries.hasNext();) {
	Map.Entry nextEntry = (Map.Entry) entries.next();
	GenFeature childMetaFeature = (GenFeature) nextEntry.getKey();
	Collection genNodesCollection = (Collection) nextEntry.getValue();
	if (childMetaFeature.isListType()) {

    stringBuffer.append(TEXT_20);
    stringBuffer.append(importManager.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(getFeatureValueGetter("modelObject", childMetaFeature, true, importManager));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_23);
    	} else {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(getFeatureValueGetter("modelObject", childMetaFeature, true, importManager));
    stringBuffer.append(TEXT_25);
    	}
    stringBuffer.append(TEXT_26);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_27);
    	boolean generateSwitch = genNodesCollection.size() != 1;
	if (generateSwitch) {
    stringBuffer.append(TEXT_28);
    	}
	
	for (Iterator genNodesIterator = genNodesCollection.iterator(); genNodesIterator.hasNext();) {
		GenNode nextNode = (GenNode) genNodesIterator.next();
		if (generateSwitch) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(importManager.getImportedName(nextNode.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_30);
    		} else {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(importManager.getImportedName(nextNode.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_32);
    		}
    stringBuffer.append(TEXT_33);
    stringBuffer.append(TEXT_34);
    		if (generateSwitch) {
    stringBuffer.append(TEXT_35);
    		}
    stringBuffer.append(TEXT_36);
    	}
	if (generateSwitch) {
    stringBuffer.append(TEXT_37);
    	}
	if (childMetaFeature.isListType()) {
    stringBuffer.append(TEXT_38);
    	}
}
    stringBuffer.append(TEXT_39);
    } else {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(importManager.getImportedName("java.util.Collections"));
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_43);
    if (!genNodes.isEmpty()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_45);
    	for (Iterator it = genNodes.iterator(); it.hasNext();) {
	GenNode nextNode = (GenNode) it.next();
    stringBuffer.append(TEXT_46);
    stringBuffer.append(importManager.getImportedName(nextNode.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_47);
    	}
    stringBuffer.append(TEXT_48);
    }
    stringBuffer.append(TEXT_49);
    if (genContainer instanceof GenNode) {
    stringBuffer.append(TEXT_50);
    
final Map<GenClass, GenTopLevelNode> genClass2Phantom = genDiagram.getGenClass2PhantomMap();
final List<GenLink> phantomLinks = genDiagram.getPhantomLinks();
if (genClass2Phantom.size() > 0 && phantomLinks.size() > 0) {

    stringBuffer.append(TEXT_51);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_52);
    stringBuffer.append(importManager.getImportedName("java.util.LinkedList"));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_54);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_55);
    stringBuffer.append(importManager.getImportedName("java.util.LinkedList"));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_58);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_60);
    	
	List genLinks = phantomLinks;
	for (int linkIndex = 0; linkIndex < genLinks.size(); linkIndex++) {
		boolean phantomsOnly = true;

    stringBuffer.append(TEXT_61);
    
GenLink nextLink = (GenLink) genLinks.get(linkIndex);
if (false == nextLink.getModelFacet() instanceof FeatureLinkModelFacet) {
	continue;
}
FeatureLinkModelFacet linkFMF = (FeatureLinkModelFacet) nextLink.getModelFacet();
GenFeature genFeature = linkFMF.getMetaFeature();
GenTopLevelNode phantomNode = (GenTopLevelNode) genClass2Phantom.get(genFeature.getTypeGenClass());
if (phantomsOnly ? (!genFeature.isContains() || phantomNode == null) : (genFeature.isContains())) {
// Processing only features which are isPhantoms == phantomsOnly
	continue;
}

    stringBuffer.append(TEXT_62);
    stringBuffer.append(importManager.getImportedName(genFeature.getGenPackage().getQualifiedPackageInterfaceName()));
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getGenClass().getClassifierAccessorName());
    stringBuffer.append(TEXT_64);
    	if (genFeature.isListType()) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(importManager.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_66);
    stringBuffer.append(getFeatureValueGetter("container", genFeature, true, importManager));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_68);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_69);
    	} else {
    stringBuffer.append(TEXT_70);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_71);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_72);
    stringBuffer.append(getFeatureValueGetter("container", genFeature, true, importManager));
    stringBuffer.append(TEXT_73);
    	}
    	if (phantomsOnly) {
    stringBuffer.append(TEXT_74);
    stringBuffer.append(importManager.getImportedName(phantomNode.getEditPartQualifiedClassName()));
    stringBuffer.append(TEXT_75);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_76);
    	}
    stringBuffer.append(TEXT_77);
    stringBuffer.append(TEXT_78);
    	if (phantomsOnly) {
    stringBuffer.append(TEXT_79);
    	}
	if (genFeature.isListType()) {
    stringBuffer.append(TEXT_80);
    	}
    stringBuffer.append(TEXT_81);
    	}
    stringBuffer.append(TEXT_82);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(importManager.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_85);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_86);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_87);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_88);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(importManager.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_92);
    stringBuffer.append(importManager.getImportedName("java.util.Collections"));
    stringBuffer.append(TEXT_93);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_94);
    stringBuffer.append(importManager.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_95);
    stringBuffer.append(importManager.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_96);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_97);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_98);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest"));
    stringBuffer.append(TEXT_99);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_100);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.EditPart"));
    stringBuffer.append(TEXT_101);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest"));
    stringBuffer.append(TEXT_102);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.commands.Command"));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.common.core.command.CompositeCommand"));
    stringBuffer.append(TEXT_104);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.common.core.command.CompositeCommand"));
    stringBuffer.append(TEXT_105);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages"));
    stringBuffer.append(TEXT_106);
    stringBuffer.append(importManager.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_107);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest"));
    stringBuffer.append(TEXT_108);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest"));
    stringBuffer.append(TEXT_109);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.common.core.command.ICommand"));
    stringBuffer.append(TEXT_110);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand"));
    stringBuffer.append(TEXT_111);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart"));
    stringBuffer.append(TEXT_112);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy"));
    stringBuffer.append(TEXT_113);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(importManager.getImportedName("java.util.Collections"));
    stringBuffer.append(TEXT_115);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.commands.SetViewMutabilityCommand"));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter"));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart"));
    stringBuffer.append(TEXT_118);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_119);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.EditPart"));
    stringBuffer.append(TEXT_120);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.EditPart"));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.Diagram"));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_125);
    
}

    }
    stringBuffer.append(TEXT_126);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_127);
    return stringBuffer.toString();
  }
}