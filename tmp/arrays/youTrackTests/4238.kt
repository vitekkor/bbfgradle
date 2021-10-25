// Original bug: KT-37447

@Target(AnnotationTarget.FUNCTION)
annotation class Range(val min: Long = 0)

@Range(min = -90L) // KtPrefixExpression is marked as BindingContext.USED_AS_EXPRESSION
fun foo(x: Int) = Unit
