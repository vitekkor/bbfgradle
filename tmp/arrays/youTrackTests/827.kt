// Original bug: KT-43831

class Change : IsChange {
    override fun filterWithSelect(select: RootPropRefGraph<out PropertyDefinitions>): Change? {
        return null
    }

}

interface IsChange {
    fun filterWithSelect(select: RootPropRefGraph<out PropertyDefinitions>): IsChange?
}

abstract class PropertyDefinitions : AbstractPropertyDefinitions<Any>()

interface IsPropertyDefinitions {
}


abstract class AbstractPropertyDefinitions<DO : Any> :
    IsPropertyDefinitions {}

class RootPropRefGraph<P : IsPropertyDefinitions>
