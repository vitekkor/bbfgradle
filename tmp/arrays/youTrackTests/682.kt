// Original bug: KT-43553

@Target(AnnotationTarget.TYPE)
annotation class Qualifier<T>

fun <T> func(param: String): @Qualifier<T> String = param

fun main() {
    func<String>("")
}
