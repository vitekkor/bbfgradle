// Original bug: KT-22822

interface NavigationFace { 
    fun navigate() // Check action name here.
}
class NavigationImpl : NavigationFace {
    override fun navigate() {}
}
open class NavigationParent {
    open fun navigate() {} // Check action name here.
}
class NavigationChild : NavigationParent() {
    override fun navigate() {
        super.navigate()
    }
}
