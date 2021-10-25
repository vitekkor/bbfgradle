// Original bug: KT-9443

abstract class BaseClass {
    protected open val menuId: Int = 0

    public fun greet() {
        println("hello $menuId")
        println(menuId == 0)
    }
}

class ImplClass: BaseClass() {
    override val menuId: Int = 3
}

public fun main(args: Array<String>) {
    ImplClass().greet()
}
