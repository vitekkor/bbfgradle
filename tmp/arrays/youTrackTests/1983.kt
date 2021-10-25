// Original bug: KT-27390

interface Base {
    fun method1()

    fun method2()

    fun method3()
}

interface Derived: Base {
    override fun method1() {
    	// some implementation
    }

    override fun method2() {
    	// some implementation
    }
}

class Leaf: Derived {
    override fun method3() {
    	// some implementation
    }
}
