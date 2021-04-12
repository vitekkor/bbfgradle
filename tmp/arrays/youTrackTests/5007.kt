// Original bug: KT-33597

class A {
    companion object {
        val INST = A()
    }
}

class B {
    companion object {
        val INST = B()
    }

    fun foo() {
        A.INST.toString()
    }
}

