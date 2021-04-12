// Original bug: KT-7650

fun main(args: Array<String>) {
    fun local() {}
    val local: () -> Unit = { }
    local() //local fun invocation!?
    local.invoke() //local var invocation!?
}
