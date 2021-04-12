// Original bug: KT-45215

fun wrapper(): Wrapper {
    return Wrapper(
        MyInlineClass(1.23)
    )
}

data class Wrapper(
    val myInlineClass : MyInlineClass?
)
inline class MyInlineClass(val value: Double){

    override fun toString(): String {
        return "I am no double"
    }
}
