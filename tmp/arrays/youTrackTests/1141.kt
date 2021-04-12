// Original bug: KT-43270

@Retention(AnnotationRetention.RUNTIME)
annotation class Anno(val x: Array<in String>)
@Anno(["", arrayOf(1), 'a']) // in fact `Array<Serializable>` is passed
fun foo() {}
fun main() {
    println(::foo.annotations) // AnnotationTypeMismatchException
}
