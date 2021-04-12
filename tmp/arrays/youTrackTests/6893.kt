// Original bug: KT-15453

class Foo {
    @get:JvmName("bar") // An accessor will not be generated for 'x', so the annotation will not be written to the class file
    private val x: String = ""
}
