// Original bug: KT-38992

interface AbstractFirTreeBuilder

class FirTreeBuilder : AbstractFirTreeBuilder

abstract class AbstractBuilderConfigurator<T : AbstractFirTreeBuilder> {
    abstract class BuilderConfigurationContext

    inner class LeafBuilderConfigurationContext : BuilderConfigurationContext()
}

class BuilderConfigurator : AbstractBuilderConfigurator<FirTreeBuilder>() {
    fun test(func: (LeafBuilderConfigurationContext) -> Unit) {
        val context = LeafBuilderConfigurationContext()
        func(context)
    }
}
