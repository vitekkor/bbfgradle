// Original bug: KT-15775

@Target(AnnotationTarget.TYPE)
annotation class A

val fn: (@A Int) -> Unit = null!!
val test1 = fn // ': (Int) -> Unit', should be: ': (@A Int) -> Unit'
