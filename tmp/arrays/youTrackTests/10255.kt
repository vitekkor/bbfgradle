// Original bug: KT-7769

fun main(args: Array<String>) {
    print(S("fail").foo().bar)
}

interface B<T> {
    val bar: T
}

class S(val value: String) {

    fun bar() = value

    fun foo(): B<String> {
        val p  = S("OK");
        return object : B<String> {
            override val bar: String = " " + p.bar() //captures p and this@S
        }
    }
}
