// Original bug: KT-8882

    open class Test(open val id: String) {
        init {
            println("Id is: ${id}")
        }
    }

    class Test2(override val id: String) : Test(id)
