// Original bug: KT-37165

enum class MyEnum(val function: () -> String) {
    A(::foo);

    companion object {
        fun foo(): String = ""
    }
}

fun main() {
    MyEnum.A.function()
}
