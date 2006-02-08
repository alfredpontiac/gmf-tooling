/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.gmf.codegen.gmfgen.util.GMFGenAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GMFGenItemProviderAdapterFactory extends GMFGenAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection supportedTypes = new ArrayList();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GMFGenItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);		
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenEditorGeneratorItemProvider genEditorGeneratorItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenEditorGeneratorAdapter() {
		if (genEditorGeneratorItemProvider == null) {
			genEditorGeneratorItemProvider = new GenEditorGeneratorItemProvider(this);
		}

		return genEditorGeneratorItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenDiagram} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenDiagramItemProvider genDiagramItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenDiagram}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenDiagramAdapter() {
		if (genDiagramItemProvider == null) {
			genDiagramItemProvider = new GenDiagramItemProvider(this);
		}

		return genDiagramItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenPlugin} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenPluginItemProvider genPluginItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenPlugin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenPluginAdapter() {
		if (genPluginItemProvider == null) {
			genPluginItemProvider = new GenPluginItemProvider(this);
		}

		return genPluginItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenTopLevelNode} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenTopLevelNodeItemProvider genTopLevelNodeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenTopLevelNode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenTopLevelNodeAdapter() {
		if (genTopLevelNodeItemProvider == null) {
			genTopLevelNodeItemProvider = new GenTopLevelNodeItemProvider(this);
		}

		return genTopLevelNodeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenChildNode} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenChildNodeItemProvider genChildNodeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenChildNode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenChildNodeAdapter() {
		if (genChildNodeItemProvider == null) {
			genChildNodeItemProvider = new GenChildNodeItemProvider(this);
		}

		return genChildNodeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenCompartment} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenCompartmentItemProvider genCompartmentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenCompartment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenCompartmentAdapter() {
		if (genCompartmentItemProvider == null) {
			genCompartmentItemProvider = new GenCompartmentItemProvider(this);
		}

		return genCompartmentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenLink} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenLinkItemProvider genLinkItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenLink}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenLinkAdapter() {
		if (genLinkItemProvider == null) {
			genLinkItemProvider = new GenLinkItemProvider(this);
		}

		return genLinkItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenNodeLabel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenNodeLabelItemProvider genNodeLabelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenNodeLabel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenNodeLabelAdapter() {
		if (genNodeLabelItemProvider == null) {
			genNodeLabelItemProvider = new GenNodeLabelItemProvider(this);
		}

		return genNodeLabelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenExternalNodeLabel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenExternalNodeLabelItemProvider genExternalNodeLabelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenExternalNodeLabel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenExternalNodeLabelAdapter() {
		if (genExternalNodeLabelItemProvider == null) {
			genExternalNodeLabelItemProvider = new GenExternalNodeLabelItemProvider(this);
		}

		return genExternalNodeLabelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenLinkLabel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenLinkLabelItemProvider genLinkLabelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenLinkLabel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenLinkLabelAdapter() {
		if (genLinkLabelItemProvider == null) {
			genLinkLabelItemProvider = new GenLinkLabelItemProvider(this);
		}

		return genLinkLabelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.TextLabelModelFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextLabelModelFacetItemProvider textLabelModelFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.TextLabelModelFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createTextLabelModelFacetAdapter() {
		if (textLabelModelFacetItemProvider == null) {
			textLabelModelFacetItemProvider = new TextLabelModelFacetItemProvider(this);
		}

		return textLabelModelFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.TypeModelFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeModelFacetItemProvider typeModelFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.TypeModelFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createTypeModelFacetAdapter() {
		if (typeModelFacetItemProvider == null) {
			typeModelFacetItemProvider = new TypeModelFacetItemProvider(this);
		}

		return typeModelFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.FeatureModelFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureModelFacetItemProvider featureModelFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.FeatureModelFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createFeatureModelFacetAdapter() {
		if (featureModelFacetItemProvider == null) {
			featureModelFacetItemProvider = new FeatureModelFacetItemProvider(this);
		}

		return featureModelFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.CompositeFeatureModelFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeFeatureModelFacetItemProvider compositeFeatureModelFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.CompositeFeatureModelFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCompositeFeatureModelFacetAdapter() {
		if (compositeFeatureModelFacetItemProvider == null) {
			compositeFeatureModelFacetItemProvider = new CompositeFeatureModelFacetItemProvider(this);
		}

		return compositeFeatureModelFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.TypeLinkModelFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeLinkModelFacetItemProvider typeLinkModelFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.TypeLinkModelFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createTypeLinkModelFacetAdapter() {
		if (typeLinkModelFacetItemProvider == null) {
			typeLinkModelFacetItemProvider = new TypeLinkModelFacetItemProvider(this);
		}

		return typeLinkModelFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.FeatureLinkModelFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureLinkModelFacetItemProvider featureLinkModelFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.FeatureLinkModelFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createFeatureLinkModelFacetAdapter() {
		if (featureLinkModelFacetItemProvider == null) {
			featureLinkModelFacetItemProvider = new FeatureLinkModelFacetItemProvider(this);
		}

		return featureLinkModelFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureLabelModelFacetItemProvider featureLabelModelFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createFeatureLabelModelFacetAdapter() {
		if (featureLabelModelFacetItemProvider == null) {
			featureLabelModelFacetItemProvider = new FeatureLabelModelFacetItemProvider(this);
		}

		return featureLabelModelFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.CompositeFeatureLabelModelFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeFeatureLabelModelFacetItemProvider compositeFeatureLabelModelFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.CompositeFeatureLabelModelFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCompositeFeatureLabelModelFacetAdapter() {
		if (compositeFeatureLabelModelFacetItemProvider == null) {
			compositeFeatureLabelModelFacetItemProvider = new CompositeFeatureLabelModelFacetItemProvider(this);
		}

		return compositeFeatureLabelModelFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.ColorAttributes} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColorAttributesItemProvider colorAttributesItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.ColorAttributes}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createColorAttributesAdapter() {
		if (colorAttributesItemProvider == null) {
			colorAttributesItemProvider = new ColorAttributesItemProvider(this);
		}

		return colorAttributesItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.ShapeAttributes} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShapeAttributesItemProvider shapeAttributesItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.ShapeAttributes}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createShapeAttributesAdapter() {
		if (shapeAttributesItemProvider == null) {
			shapeAttributesItemProvider = new ShapeAttributesItemProvider(this);
		}

		return shapeAttributesItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.DefaultSizeAttributes} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultSizeAttributesItemProvider defaultSizeAttributesItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.DefaultSizeAttributes}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createDefaultSizeAttributesAdapter() {
		if (defaultSizeAttributesItemProvider == null) {
			defaultSizeAttributesItemProvider = new DefaultSizeAttributesItemProvider(this);
		}

		return defaultSizeAttributesItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.FigureViewmap} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FigureViewmapItemProvider figureViewmapItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.FigureViewmap}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createFigureViewmapAdapter() {
		if (figureViewmapItemProvider == null) {
			figureViewmapItemProvider = new FigureViewmapItemProvider(this);
		}

		return figureViewmapItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.SnippetViewmap} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SnippetViewmapItemProvider snippetViewmapItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.SnippetViewmap}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createSnippetViewmapAdapter() {
		if (snippetViewmapItemProvider == null) {
			snippetViewmapItemProvider = new SnippetViewmapItemProvider(this);
		}

		return snippetViewmapItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.InnerClassViewmap} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InnerClassViewmapItemProvider innerClassViewmapItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.InnerClassViewmap}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createInnerClassViewmapAdapter() {
		if (innerClassViewmapItemProvider == null) {
			innerClassViewmapItemProvider = new InnerClassViewmapItemProvider(this);
		}

		return innerClassViewmapItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.Palette} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PaletteItemProvider paletteItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.Palette}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createPaletteAdapter() {
		if (paletteItemProvider == null) {
			paletteItemProvider = new PaletteItemProvider(this);
		}

		return paletteItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.NodeEntry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeEntryItemProvider nodeEntryItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.NodeEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createNodeEntryAdapter() {
		if (nodeEntryItemProvider == null) {
			nodeEntryItemProvider = new NodeEntryItemProvider(this);
		}

		return nodeEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.LinkEntry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkEntryItemProvider linkEntryItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.LinkEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createLinkEntryAdapter() {
		if (linkEntryItemProvider == null) {
			linkEntryItemProvider = new LinkEntryItemProvider(this);
		}

		return linkEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.ToolGroup} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ToolGroupItemProvider toolGroupItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.ToolGroup}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createToolGroupAdapter() {
		if (toolGroupItemProvider == null) {
			toolGroupItemProvider = new ToolGroupItemProvider(this);
		}

		return toolGroupItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenFeatureSeqInitializer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenFeatureSeqInitializerItemProvider genFeatureSeqInitializerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenFeatureSeqInitializer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenFeatureSeqInitializerAdapter() {
		if (genFeatureSeqInitializerItemProvider == null) {
			genFeatureSeqInitializerItemProvider = new GenFeatureSeqInitializerItemProvider(this);
		}

		return genFeatureSeqInitializerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenFeatureValueSpec} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenFeatureValueSpecItemProvider genFeatureValueSpecItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenFeatureValueSpec}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenFeatureValueSpecAdapter() {
		if (genFeatureValueSpecItemProvider == null) {
			genFeatureValueSpecItemProvider = new GenFeatureValueSpecItemProvider(this);
		}

		return genFeatureValueSpecItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenLinkConstraints} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenLinkConstraintsItemProvider genLinkConstraintsItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenLinkConstraints}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenLinkConstraintsAdapter() {
		if (genLinkConstraintsItemProvider == null) {
			genLinkConstraintsItemProvider = new GenLinkConstraintsItemProvider(this);
		}

		return genLinkConstraintsItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenAuditContainer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenAuditContainerItemProvider genAuditContainerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenAuditContainer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenAuditContainerAdapter() {
		if (genAuditContainerItemProvider == null) {
			genAuditContainerItemProvider = new GenAuditContainerItemProvider(this);
		}

		return genAuditContainerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.GenAuditRule} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenAuditRuleItemProvider genAuditRuleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenAuditRule}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGenAuditRuleAdapter() {
		if (genAuditRuleItemProvider == null) {
			genAuditRuleItemProvider = new GenAuditRuleItemProvider(this);
		}

		return genAuditRuleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.ValueExpression} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueExpressionItemProvider valueExpressionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.ValueExpression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createValueExpressionAdapter() {
		if (valueExpressionItemProvider == null) {
			valueExpressionItemProvider = new ValueExpressionItemProvider(this);
		}

		return valueExpressionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.codegen.gmfgen.ModelElementSelector} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelElementSelectorItemProvider modelElementSelectorItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.codegen.gmfgen.ModelElementSelector}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createModelElementSelectorAdapter() {
		if (modelElementSelectorItemProvider == null) {
			modelElementSelectorItemProvider = new ModelElementSelectorItemProvider(this);
		}

		return modelElementSelectorItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class) || (((Class)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (genEditorGeneratorItemProvider != null) genEditorGeneratorItemProvider.dispose();
		if (genDiagramItemProvider != null) genDiagramItemProvider.dispose();
		if (genPluginItemProvider != null) genPluginItemProvider.dispose();
		if (genTopLevelNodeItemProvider != null) genTopLevelNodeItemProvider.dispose();
		if (genChildNodeItemProvider != null) genChildNodeItemProvider.dispose();
		if (genCompartmentItemProvider != null) genCompartmentItemProvider.dispose();
		if (genLinkItemProvider != null) genLinkItemProvider.dispose();
		if (genNodeLabelItemProvider != null) genNodeLabelItemProvider.dispose();
		if (genExternalNodeLabelItemProvider != null) genExternalNodeLabelItemProvider.dispose();
		if (genLinkLabelItemProvider != null) genLinkLabelItemProvider.dispose();
		if (textLabelModelFacetItemProvider != null) textLabelModelFacetItemProvider.dispose();
		if (typeModelFacetItemProvider != null) typeModelFacetItemProvider.dispose();
		if (featureModelFacetItemProvider != null) featureModelFacetItemProvider.dispose();
		if (compositeFeatureModelFacetItemProvider != null) compositeFeatureModelFacetItemProvider.dispose();
		if (typeLinkModelFacetItemProvider != null) typeLinkModelFacetItemProvider.dispose();
		if (featureLinkModelFacetItemProvider != null) featureLinkModelFacetItemProvider.dispose();
		if (featureLabelModelFacetItemProvider != null) featureLabelModelFacetItemProvider.dispose();
		if (compositeFeatureLabelModelFacetItemProvider != null) compositeFeatureLabelModelFacetItemProvider.dispose();
		if (colorAttributesItemProvider != null) colorAttributesItemProvider.dispose();
		if (shapeAttributesItemProvider != null) shapeAttributesItemProvider.dispose();
		if (defaultSizeAttributesItemProvider != null) defaultSizeAttributesItemProvider.dispose();
		if (figureViewmapItemProvider != null) figureViewmapItemProvider.dispose();
		if (snippetViewmapItemProvider != null) snippetViewmapItemProvider.dispose();
		if (innerClassViewmapItemProvider != null) innerClassViewmapItemProvider.dispose();
		if (valueExpressionItemProvider != null) valueExpressionItemProvider.dispose();
		if (modelElementSelectorItemProvider != null) modelElementSelectorItemProvider.dispose();
		if (paletteItemProvider != null) paletteItemProvider.dispose();
		if (nodeEntryItemProvider != null) nodeEntryItemProvider.dispose();
		if (linkEntryItemProvider != null) linkEntryItemProvider.dispose();
		if (toolGroupItemProvider != null) toolGroupItemProvider.dispose();
		if (genFeatureSeqInitializerItemProvider != null) genFeatureSeqInitializerItemProvider.dispose();
		if (genFeatureValueSpecItemProvider != null) genFeatureValueSpecItemProvider.dispose();
		if (genLinkConstraintsItemProvider != null) genLinkConstraintsItemProvider.dispose();
		if (genAuditContainerItemProvider != null) genAuditContainerItemProvider.dispose();
		if (genAuditRuleItemProvider != null) genAuditRuleItemProvider.dispose();
	}

}
