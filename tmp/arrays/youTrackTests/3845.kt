// Original bug: KT-36069

fun <E, F : E> bar(arg: E?, fn: F): E = TODO()

fun test(s: String?) {
    val v =
        if (s != null) ( {s} )
        else bar(null) { "" }   //  required Nothing, found () -> String
}
