// Original bug: KT-37447

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Range(val min: Long = 0)

fun foo(@Range(min = -90L) x: Int) = Unit // KtPrefixExpression isn't marked as BindingContext.USED_AS_EXPRESSION
