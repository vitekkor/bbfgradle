// Original bug: KT-21532

enum class E(val a: String? = null) { // removing "a" argument fixes the problem
    A { // changing to A() fixes the problem
        override fun f() = "" // removing overridden method fixes the problem
    };

    open fun f() = ""
}

fun main(args: Array<String>) {
    E.A // generates java.lang.NoSuchMethodError: integration.E: method <init>(Ljava/lang/String;I)V not found
}
