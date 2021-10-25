// Original bug: KT-44530

import kotlin.internal.*

inline fun <T> If(boolean: Boolean, block: () -> T): IfResult<T> {
    return if(boolean) IfResultOf(block()) else IfResultContinue
}

inline infix fun <T> IfResult<T>.Else(block: () -> T): T {
    return getOrElse { block() }
}

// inline so that, with the proposed optimisation, the block would just be directly inlined
// from the result of the boolean invoke extension into the if(wasFalse) branch and its result
// will be passed to IfResultOf, all without actually creatinga a lambda object since it's all inlined!
inline infix fun <T> IfResult<T>.ElseIf(block: ConditionalLambda<T>): IfResult<T> {
    return if(wasFalse) IfResultOf(block()) else this
}

// Ideally this wouldn't need to be crossinlined and instead the returned ConditionalLambda 
// will be directly inlined at the call site
inline operator fun <T> Boolean.invoke(crossinline lambda: () -> T): ConditionalLambda<T> {
    return ConditionalLambda<T>({ if(this) lambda() else CONTINUE })
}

fun main() {
    println(If(false){
        println("this should run")
        5
    } ElseIf (true) {
        println("no this")
        10
    } Else {null})
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class ConditionalLambda<out T> @PublishedApi internal constructor(@PublishedApi internal val lambda: () -> Any?) {
    operator fun invoke() = lambda() as T
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class IfResult<out T> internal constructor(@PublishedApi internal val value: Any?) {
    val wasTrue get() = value != CONTINUE
    val wasFalse get() = value == CONTINUE
    inline fun getOrElse(block: () -> @UnsafeVariance T) = if(wasTrue) value as T else block()
}

@PublishedApi internal val CONTINUE = Any()
@PublishedApi internal val IfResultContinue = IfResult<Nothing>(CONTINUE)

fun <T> IfResultOf(value: T) = IfResult<T>(value)
