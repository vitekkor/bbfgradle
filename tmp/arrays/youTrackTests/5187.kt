// Original bug: KT-34445

open class Parent {
    open fun String.iWantToBeOverridden() {} // Refactor > Convert receiver to parameter
}

class Child : Parent() {
    override fun String.iWantToBeOverridden() { // Refactor > Convert receiver to parameter
    }
}
