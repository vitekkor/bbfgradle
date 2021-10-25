// Original bug: KT-34293

@RequiresOptIn(level = RequiresOptIn.Level.WARNING)
@Target(AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ExperimentalAPI

var foo: Int
    @ExperimentalAPI
    get() {
        return 1
    }
    @ExperimentalAPI
    set(value) {
        return
    }

fun main() {
    val x = foo // no warning about experimental usage
    foo = 10 // for setters, experimental usage warnings are present
}
