// Original bug: KT-37165

enum class MyEnum(
    val function: (Int) -> String
) {
    THIS_WORKS({ n -> "INLINE: Number is $n" }),
    SO_DOES_THIS(::functionOutsideClass),
    THIS_DOESNT(::functionInCompanion);

    companion object {
        private fun functionInCompanion(n: Int): String = "IN COMPANION... $n"
    }
}

fun functionOutsideClass(n: Int): String = "OUTSIDE: Number is $n"

fun main() {
    MyEnum.values().forEachIndexed { num, symbol ->
        println("$symbol = ${symbol.function(num)}")
    }
}
