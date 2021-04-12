// Original bug: KT-45632

interface TType
interface Operand<T: TType>

fun <T : TType> placeholderWithDefault(input: Operand<T>): Operand<T> = input
inline fun <reified T : TType> ones(vararg dims: Int): Operand<T> = error("") // uses T::class

val a = placeholderWithDefault(ones(2, 2))
