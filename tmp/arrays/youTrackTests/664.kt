// Original bug: KT-42268

@Target(AnnotationTarget.TYPE)
annotation class Qualifier<T>

fun <T> func(param: String): @Qualifier<T> String {
    return param
}

fun main() {
    func<String>("")
}