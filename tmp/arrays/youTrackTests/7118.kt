// Original bug: KT-5092

fun main(args: Array<String>) {
    val objectInLambda = {
        object : Any () {}
    }()

    val declaringClass = objectInLambda.javaClass.getDeclaringClass()
}
