// Original bug: KT-7208

fun scopes() {

    val value = 1

    class C {
         fun f() = value
    }

    fun local() {
        val value = 2

        C().f() == value
    }
}
