// Original bug: KT-24215

fun d(
        createDummy: String,
        dummyParam: Int = 0,
        initDummy: String.() -> Unit = {}
) = createDummy.also(initDummy).also { dummyParam }

@Deprecated("Use d instead", ReplaceWith("addA(d(createDummy, initDummy))"))
fun MutableList<String>.addA(
        createDummy: String,
        initDummy: String.() -> Unit = {}
) = createDummy.also(initDummy).also { add(it) }

fun MutableList<String>.addA(a: String): String = a.also { add(a) }

val unDeprecateMe = mutableListOf("Hello").apply {
    addA("Hi ") { 
        println("Yo")
    }
}
