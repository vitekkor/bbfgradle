// Original bug: KT-27577

inline fun <reified T> removeQuestion(arg: T?): T = arg as T

inline fun <reified U> use(arg: U?): U {
    val arg1 = removeQuestion(arg)
    arg1.equals(arg)
    return arg1
}

fun main(args: Array<String>) {
    val a: String? = null
    use<String?>(a)
}
