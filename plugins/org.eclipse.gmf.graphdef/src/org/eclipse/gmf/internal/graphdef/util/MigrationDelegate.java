/*
 * Copyright (c) 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Borland - initial API and implementation
 */
package org.eclipse.gmf.internal.graphdef.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.gmfgraph.ChildAccess;
import org.eclipse.gmf.gmfgraph.Compartment;
import org.eclipse.gmf.gmfgraph.CustomFigure;
import org.eclipse.gmf.gmfgraph.DiagramElement;
import org.eclipse.gmf.gmfgraph.DiagramLabel;
import org.eclipse.gmf.gmfgraph.Figure;
import org.eclipse.gmf.gmfgraph.FigureAccessor;
import org.eclipse.gmf.gmfgraph.FigureDescriptor;
import org.eclipse.gmf.gmfgraph.FigureGallery;
import org.eclipse.gmf.gmfgraph.GMFGraphFactory;
import org.eclipse.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.gmf.gmfgraph.RealFigure;
import org.eclipse.gmf.internal.common.migrate.MigrationDelegateImpl;

class MigrationDelegate extends MigrationDelegateImpl {
	private EReference myFigure_RefElements;
	private EAttribute myDiagramElement_RefFigure;
	
	private Map<String, EObject> myId2EObject;
	private Collection<EObject> myProxiesToResolve;
	private Map<DiagramElement, String> myDiagramElementReferencedFigure;
	private Map<DiagramElement, EObject> myDiagramElementReferencedProxyFigure;
	
	private Collection<EReference> myRemainedFigureReferences;
	private Map<FigureAccessor, ChildAccess> myAccessNeedsToBeSpecifiedLater;
	
	MigrationDelegate() {
	}

	void init() {
		final GMFGraphPackage graphDefPkg = GMFGraphPackage.eINSTANCE;
		// narrowing for reference FigureAccessor.typedFigure: (the only place where concrete CustomFigure was used)
		registerNarrowedAbstractType("RealFigure", graphDefPkg.getCustomFigure()); //$NON-NLS-1$
		
		registerDeletedAttributes(graphDefPkg.getCustomClass(), "bundleName"); //$NON-NLS-1$
		
		myFigure_RefElements = createNewReference("referencingElementsFake", graphDefPkg.getDiagramElement(), false); //$NON-NLS-1$
		registerTracedFeatureForHierarchy(graphDefPkg.getFigure(), "referencingElements", myFigure_RefElements); //$NON-NLS-1$
		registerTracedFeatureForHierarchy(graphDefPkg.getFigureAccessor(), "referencingElements", myFigure_RefElements); //$NON-NLS-1$
		
		// look, we have replaced FigureDescriptor-typed reference with plain EString attribute to take full control on resolving it later in postLoad:
		// but cross-resource references are going to be handled separately
		myDiagramElement_RefFigure = createNewAttribute("figure", EcorePackage.eINSTANCE.getEString(), false); //$NON-NLS-1$
		registerTracedAttributeForHierarchy(graphDefPkg.getDiagramElement(), "figure", myDiagramElement_RefFigure); //$NON-NLS-1$

		myRemainedFigureReferences = Arrays.asList(graphDefPkg.getFigureAccessor_TypedFigure(), graphDefPkg.getPolylineConnection_SourceDecoration(), graphDefPkg.getPolylineConnection_TargetDecoration());
		
		myId2EObject = null;
		myProxiesToResolve = null;
		myDiagramElementReferencedFigure = null;
	}

	private boolean isOneOfRemainedFigureReferences(EStructuralFeature feature) {
		return myRemainedFigureReferences.contains(feature);
	}

	@Override
	public boolean setValue(EObject object, EStructuralFeature feature, Object value, int position) {
		// during load
		
		if (object instanceof Figure && "name".equals(feature.getName())) { //$NON-NLS-1$
			// this feature used to be ID in old versions, so need to emulate this during processing to
			// provide manual reference resolving later, in postLoad
			String name = (String) value;
			saveEObjectIdLocally(object, name);
		}
		if (GMFGraphPackage.eINSTANCE.getFigureAccessor_TypedFigure().equals(feature)) {
			if (value instanceof RealFigure) {
				RealFigure figure = (RealFigure) value;
				if (figure.eIsProxy()) {
					// this could happen due to generating resource with references using an older style
					// of hyperlink serialization, where it needs to be in separate element with
					// "href" attribute (controlled by option XMIResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE)
					saveReferenceToGetContainmentLater(figure);
					return false; // default processing is essential - it will set eContainer, which would be == "object",
					// and we'll use this fact later, when deaing with myProxiesToResolve
				} else if (value instanceof CustomFigure && figure.eContainer() != null) {
					// not a proxy and has container - copy right away
					CustomFigure custom = (CustomFigure) value;
					FigureAccessor accessor = (FigureAccessor) object;
					EObject container = custom.eContainer();
					if (!custom.eIsProxy() && container != null) {
						CustomFigure copy = (CustomFigure) EcoreUtil.copy(custom);
						accessor.setTypedFigure(copy);
						fireMigrationApplied(true);
						return true;
					} 
					// opposite case for proxy is going to be processed in preReserve(), here we let the proxy value to be set as always,
					// as well as ordinary containment value (we can recognize the case by null container yet)
				}
				// FALL-THROUGH (for check isOneOfRemainedFigureReferences && value instanceof FigureDescriptor)
			}
		}
		if (myDiagramElement_RefFigure.equals(feature) ) {
			// we are going to resolve figure references in postprocessing, ourselves, see postLoad()
			DiagramElement diagramElement = (DiagramElement) object;
			String figureRef = (String) value;
			saveDiagramElementReferencedFigure(diagramElement, figureRef);
			return true;
		}
		if (GMFGraphPackage.eINSTANCE.getDiagramElement_Figure().equals(feature) ) {
			// we are going to resolve figure references in postprocessing, ourselves, see postLoad()
			DiagramElement diagramElement = (DiagramElement) object;
			EObject figureRef = (EObject) value;
			if (figureRef.eIsProxy()) {
				saveDiagramElementReferencedProxy(diagramElement, figureRef);
				return true;
			}
		}
		
		// after end of document (between preResolve and postLoad calls), handling forward references:
		
		if (isOneOfRemainedFigureReferences(feature) && value instanceof FigureDescriptor) {
			// as in old version a name is used for id of referenced figure, and now we set this name
			// for its figure descriptor, if the figure is directly nested into it, - we could receive 
			// an instanceof descriptor resolved for this id value
			FigureDescriptor descriptor = (FigureDescriptor) value;
			Figure figure = descriptor.getActualFigure();
			fireMigrationApplied(true);
			// we call setValue again only for the case of handling containment reference, where we do not want
			// it to be removed from original descriptor, but just to be copied instead 
			if (!setValue(object, feature, figure, position)) {
				object.eSet(feature, figure);
				return true;
			}
		} 
		if (myFigure_RefElements.equals(feature)) {
			migrateFigureStructureToDescriptor((EObject) value, object);
		} else {
			// other cases are would be processed as defaults
			return super.setValue(object, feature, value, position);
		}
		return true;
	}

	private void migrateFigureStructureToDescriptor(EObject value, EObject object) {
		if (value.eIsProxy()) {
			// this will LOAD all referenced resources, and migrate them if necessary
			value = EcoreUtil.resolve(value, object);
		}
		DiagramElement node = (DiagramElement) value;
		if (object instanceof Figure) {
			Figure figure = (Figure) object; // can be FigureRef as well
			RealFigure topLevel = findTopLevelFigure(figure);
			setFigureToDiagramElement(node, figure, topLevel);
			fireMigrationApplied(true);
		} else if (object instanceof FigureAccessor) {
			FigureAccessor accessor = (FigureAccessor) object;
			Figure figure = accessor.getTypedFigure();
			RealFigure topLevel = findTopLevelFigure((Figure) accessor.eContainer());
			ChildAccess access = setFigureToDiagramElement(node, figure, topLevel);
			if (figure == null) {
				// it could happen that we have not processed our typedFigure reference yet!
				saveAccessNeedsToBeSpecifiedLater(accessor, access);
			}
			fireMigrationApplied(true);
		}
	}

	private void saveAccessNeedsToBeSpecifiedLater(FigureAccessor accessor, ChildAccess access) {
		if (myAccessNeedsToBeSpecifiedLater == null) {
			myAccessNeedsToBeSpecifiedLater = new HashMap<FigureAccessor, ChildAccess>();
		}
		myAccessNeedsToBeSpecifiedLater.put(accessor, access);
	}

	private ChildAccess setFigureToDiagramElement(DiagramElement node, Figure figure, RealFigure topLevel) {
		// figure can be null, in case of processing reference to FigureAccessor directly
		FigureDescriptor figureDescriptor = topLevel.getDescriptor();
		if (figureDescriptor == null) {
			figureDescriptor = getOrCreateFigureDescriptorFor(topLevel);
		}
		node.setFigure(figureDescriptor);
		if (!topLevel.equals(figure)) {
			ChildAccess access = getOrCreateChildAccessForNested(figure, figureDescriptor);
			setNestedFigureAccessFor(node, access);
			return access;
		}
		return null;
	}

	private void saveDiagramElementReferencedFigure(DiagramElement diagramElement, String figureRef) {
		if (myDiagramElementReferencedFigure == null) {
			myDiagramElementReferencedFigure = new HashMap<DiagramElement, String>();
		}
		if (figureRef != null && figureRef.length() != 0) {
			myDiagramElementReferencedFigure.put(diagramElement, figureRef);
		}
	}

	private void saveDiagramElementReferencedProxy(DiagramElement diagramElement, EObject figureRef) {
		if (myDiagramElementReferencedProxyFigure == null) {
			myDiagramElementReferencedProxyFigure = new HashMap<DiagramElement, EObject>();
		}
		if (figureRef != null && figureRef.eIsProxy()) {
			myDiagramElementReferencedProxyFigure.put(diagramElement, figureRef);
		}
	}

	@Override
	public boolean setManyReference(EObject object, EStructuralFeature feature, Object[] values) {
		return myFigure_RefElements.equals(feature);
	}

	@Override
	public void preResolve() {
		super.preResolve();
		Resource resource = getResource();
		// this is run BEFORE forward references handling 
		if (myId2EObject != null && resource instanceof ResourceImpl) {
			Map<String, EObject> idMappings = ((ResourceImpl)resource).getIntrinsicIDToEObjectMap();
			if (idMappings == null) {
				idMappings = new HashMap<String, EObject>();
				((ResourceImpl)resource).setIntrinsicIDToEObjectMap(idMappings);
			}
			for (Iterator<Map.Entry<String, EObject>> it = myId2EObject.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, EObject> next = it.next();
				String id = next.getKey();
				EObject found = resource.getEObject(id);
				if (found == null) {
					idMappings.put(id, next.getValue());
				} else {
					it.remove();//setValue(found);
				}
			}
			
		}
		if (myProxiesToResolve != null) {
			for (EObject proxy : myProxiesToResolve) {
				// these proxies are expected just to be references, used to be non-containment in the past,
				// and interpreted by loader as proxies because of having "href" attribute 
				String last = EcoreUtil.getURI(proxy).lastSegment();
				EObject saved = resource.getEObject(last);//myId2EObject.get(last);
				if (proxy.eContainer() instanceof FigureAccessor) {
					FigureAccessor accessor = (FigureAccessor) proxy.eContainer();
					// saved could be FigureDescriptor for migrated resources with proxy customFigure references
					if (saved instanceof RealFigure) {
						RealFigure copyOfResolved = (RealFigure) EcoreUtil.copy(saved);
						accessor.setTypedFigure(copyOfResolved);
					} else if (saved instanceof FigureDescriptor) {
						FigureDescriptor descriptor = (FigureDescriptor) saved;
						RealFigure copyOfResolved = (RealFigure) EcoreUtil.copy(descriptor.getActualFigure());
						accessor.setTypedFigure(copyOfResolved);
					}
				}
			}
			myProxiesToResolve.clear();
		}
	}
	
	@Override
	public void postLoad() {
		super.postLoad();
		Resource resource = getResource();
		if (myAccessNeedsToBeSpecifiedLater != null) {
			for (FigureAccessor accessor : myAccessNeedsToBeSpecifiedLater.keySet()) {
				ChildAccess access = myAccessNeedsToBeSpecifiedLater.get(accessor);
				if (access != null && access.getFigure() == null) {
					RealFigure figure = getOrCreateTypedFigure(accessor);
					access.setFigure(figure);
				}
			}
			myAccessNeedsToBeSpecifiedLater.clear();
		}
		if (myDiagramElementReferencedFigure != null) {
			for (DiagramElement diagramElement : myDiagramElementReferencedFigure.keySet()) {
				if (diagramElement.getFigure() != null) {
					// this is the case of resolving this reference while processing forwardReference,
					// that existed in the same file
					continue;
				}
				// in the other case this reference either do not have a forward one (i.e. it
				// is a new file, and the reference points to an existing descriptor),
				// either we have a reference to a figure that had no forward reference
				// to ourselves, that was possible prior to GMF 1.0 RC2_10.
				String figureRef = myDiagramElementReferencedFigure.get(diagramElement);
				EObject referencedEObject = resource.getEObject(figureRef);
				setFigureDescriptorForDiagramElement(referencedEObject, diagramElement);
			}
			myDiagramElementReferencedFigure.clear();
		}
		if (myDiagramElementReferencedProxyFigure != null) {
			for (DiagramElement diagramElement : myDiagramElementReferencedProxyFigure.keySet()) {
				if (diagramElement.getFigure() != null && !diagramElement.getFigure().eIsProxy()) {
					// this is the case of resolving this reference while processing forwardReference,
					// that existed in the same file
					continue;
				}
				// It is the case either of cross-resource reference (and that other resource
				// can be either old or new one), either it is a kind of reference, produced
				// using an older style of hyperlink serialization, where it needs to be in separate element with
				// "href" attribute (controlled by option XMIResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE)
				EObject proxyFigure = myDiagramElementReferencedProxyFigure.get(diagramElement);
				URI proxyURI = EcoreUtil.getURI(proxyFigure);
				// this should LOAD all referenced resources, and migrate them if necessary
				Resource referencedResource = resource.getResourceSet().getResource(proxyURI.trimFragment(), false);
				if (referencedResource == null) {
					referencedResource = resource.getResourceSet().createResource(proxyURI.trimFragment());
				}
				// do not load the referenced resource, cause it may happen that it is loading us now
				if (!((ResourceImpl)referencedResource).isLoading()) {
					EObject referencedEObject = resource.getResourceSet().getEObject(proxyURI, true);
					if (diagramElement.getFigure() != null && !diagramElement.getFigure().eIsProxy()) {
						// referenced file is migrated, we got value during 'referencedElements' processing
						fireMigrationApplied(true);
						continue;
					}
					setFigureDescriptorForDiagramElement(referencedEObject, diagramElement);
				}
			}
			myDiagramElementReferencedProxyFigure.clear();
		}
		if (myId2EObject != null) {
			myId2EObject.clear();
		}
	}

	private void setFigureDescriptorForDiagramElement(EObject referencedEObject, DiagramElement diagramElement) {
		if (referencedEObject instanceof FigureDescriptor) {
			// this is newest properly structured metamodel reference
			diagramElement.setFigure((FigureDescriptor) referencedEObject);
		} else if (referencedEObject instanceof Figure) {
			// this could be the case of nested figure, that came for its name
			Figure figure = (Figure) referencedEObject;
			if (figure.getDescriptor() == null) {
				// Otherwise we have a reference to a figure that had no forward reference
				// to ourselves!! That was possible prior to GMF 1.0 RC2_10.
				// Should we initialize wrapping it with descriptor in such case?
				// Fortunately, FigureAccessor (with no ID attribute!) is introduced afterwards,
				// since GMF 1.0 I20060526_1200 build (prior to RC1_0, though).
				setValue(figure, myFigure_RefElements, diagramElement, 0);
				fireMigrationApplied(true);
			}
			FigureDescriptor descriptor = figure.getDescriptor();
			if (descriptor != null) {
				diagramElement.setFigure(descriptor);
			} else {
				// this message is going to be shown to the user, so there should be i18n
				throw new IllegalArgumentException(MessageFormat.format("Reference to the figure {0} could not be resolved to its descriptor for diagram element {1}", figure, diagramElement));
			}
		} else {
			// this message is going to be shown to the user, so there should be i18n
			throw new IllegalArgumentException(MessageFormat.format("Figure reference to {0} could not be resolved for {1}", referencedEObject, diagramElement));
		}
	}

	private void saveEObjectIdLocally(EObject object, String id) {
		if (myId2EObject == null) {
			myId2EObject = new HashMap<String, EObject>();
		}
		if (myId2EObject.get(id) == null) {
			myId2EObject.put(id, object);
		} else {
			// collision with figure name expected only in new versions, as old ones used this as id 
		}
	}

	private void saveReferenceToGetContainmentLater(EObject proxy) {
		if (myProxiesToResolve == null) {
			myProxiesToResolve = new ArrayList<EObject>();
		}
		myProxiesToResolve.add(proxy);
	}

	private RealFigure getOrCreateTypedFigure(FigureAccessor accessor) {
		RealFigure result = accessor.getTypedFigure();
		if (result == null) {
			CustomFigure custom = GMFGraphFactory.eINSTANCE.createCustomFigure();
			// @see org.eclipse.gmf.codegen/templates/xpt/diagram/editparts/TextAware.xpt::labelSetterFigureClassName
			custom.setQualifiedClassName("org.eclipse.draw2d.IFigure"); //$NON-NLS-1$
			accessor.setTypedFigure(custom);
			result = custom;
		}
		return result;
	}

	private CustomFigure findParentCustomFigure(FigureAccessor accessor) {
		CustomFigure result = null;
		if (accessor.eContainer() instanceof CustomFigure) {
			result = (CustomFigure) accessor.eContainer();
		}
		return result;
	}

	private void setNestedFigureAccessFor(DiagramElement dElem, ChildAccess access) {
		if (dElem instanceof DiagramLabel) {
			DiagramLabel label = (DiagramLabel) dElem;
			label.setAccessor(access);
		} else if (dElem instanceof Compartment) {
			Compartment bag = (Compartment) dElem;
			bag.setAccessor(access);
		}
	}

	private RealFigure findTopLevelFigure(Figure figure) {
		RealFigure result =  figure instanceof RealFigure ? (RealFigure) figure : null;
		Object container = result.eContainer();
		while (container instanceof RealFigure || container instanceof FigureAccessor) {
			if (container instanceof FigureAccessor) {
				result = findParentCustomFigure((FigureAccessor) container);
			} else {
				result = (RealFigure) result.eContainer();
			}
			container = result.eContainer();
		} // now it should be the one contained within FigureGallery or FigureDescriptor
		return result;
	}

	private ChildAccess getOrCreateChildAccessForNested(Figure nestedFigure, FigureDescriptor toplevelDescriptor) {
		for (ChildAccess haveAccess : toplevelDescriptor.getAccessors()) {
			if (nestedFigure != null && nestedFigure.equals(haveAccess.getFigure())) {
				return haveAccess;
			}
		}
		ChildAccess result = GMFGraphFactory.eINSTANCE.createChildAccess();
		result.setFigure(nestedFigure);
		toplevelDescriptor.getAccessors().add(result);
		return result;
	}
	
	private FigureDescriptor getOrCreateFigureDescriptorFor(RealFigure toplevelFigure) {
		FigureDescriptor descriptor = toplevelFigure.getDescriptor();
		if (descriptor == null) {
			descriptor = GMFGraphFactory.eINSTANCE.createFigureDescriptor();
			if (toplevelFigure.getName() != null) {
				descriptor.setName(toplevelFigure.getName());
			}
			EObject container = toplevelFigure.eContainer();
			if (container instanceof FigureGallery) {
				FigureGallery gallery = (FigureGallery) container;
				gallery.getDescriptors().add(descriptor);
			}
			descriptor.setActualFigure(toplevelFigure);
		}
		return descriptor;
	}
}