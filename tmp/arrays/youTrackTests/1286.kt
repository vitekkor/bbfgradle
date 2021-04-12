// Original bug: KT-23881

class LeakedType(val source: String)

class SomeClass(private val fromOuterClass: LeakedType) {
    val classWithCallback = ClassWithCallback()

    fun addCallback() {
        classWithCallback.apply {
            someCallback = { println("In callback :: leakedType=${fromOuterClass.source}") }
        }
    }

    fun addCallback(leaked: LeakedType) {
        classWithCallback.apply {
            someCallback = { println("In callback :: leakedType=${fromOuterClass.source}") }
            println("In apply block using leak arg :: leakedType= ${leaked.source}")
        }
    }
}

class ClassWithCallback {
    var someCallback: (() -> Unit)? = null

    fun dumpCallbackFields() {
        val someCallback = someCallback!!
        someCallback::class.java.declaredFields.forEach {
            println("field=$it")
            if (it.type == LeakedType::class.java) {
                it.isAccessible = true
                val topLevelType = it.get(someCallback) as LeakedType
                println("From callback fields :: leakedType=${topLevelType.source}")
            }
        }
    }
}

fun main(args: Array<String>) {
    val fromCtor = LeakedType("Passed via ctor")
    val someClass = SomeClass(fromCtor)

    someClass.addCallback()
    someClass.classWithCallback.someCallback?.invoke()
    someClass.classWithCallback.dumpCallbackFields()

    println("")

    val passedToFunction = LeakedType("Passed as function arg")
    someClass.addCallback(passedToFunction)
    someClass.classWithCallback.someCallback?.invoke()
    someClass.classWithCallback.dumpCallbackFields()
}
