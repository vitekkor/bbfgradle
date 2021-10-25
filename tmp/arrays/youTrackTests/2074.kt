// Original bug: KT-40768

private fun fix(label: String): Boolean {
    println(label)
    return true
}

open class BaseObject {
	open val x = 13

    companion object {
        val fixed = fix("BaseObject.Companion")
    }
}

class A: BaseObject()  {
	override val x = 42
    
    companion object {
        val fixed = fix("A.Companion")
    }
}

fun main() {
    val a = A()
}
