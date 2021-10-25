// Original bug: KT-244

fun f(s: String?) {
    if (s != null) {
        s.length  //ok
        val i = s.length //error: Only safe calls are allowed on a nullable receiver
        System.out?.println(s.length) //error
    }
}
