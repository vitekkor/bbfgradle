// Original bug: KT-4218

object SomeObject {
    val values = create()
    fun create() = Array<Array<String>>(1) { y ->
        Array<String>(1) { x ->
            "(${x}, ${y})"
        }
    }
}
