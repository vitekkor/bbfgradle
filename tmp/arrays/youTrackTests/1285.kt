// Original bug: KT-23881

fun main(args: Array<String>) {
    class LeakedType(val source: String)
    class ClassWithCallback {
        var someCallback: (() -> Unit)? = null

        fun dumpCallbackFields() =
            someCallback!!.javaClass.declaredFields
                .map { it.get(someCallback!!) as LeakedType }
                .forEach { println("From callback fields :: leakedType=${it.source}") }
    }

    val classWithCallback = ClassWithCallback()

    val outer = LeakedType("Used in callback")
    val leaked = LeakedType("Used in apply block but not callback")

    classWithCallback.apply {
        someCallback = { println("In callback :: leakedType=${outer.source}") }
        println("In apply block using leak arg :: leakedType=${leaked.source}")
    }
    classWithCallback.dumpCallbackFields()
}
