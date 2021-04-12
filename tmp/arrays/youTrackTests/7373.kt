// Original bug: KT-27795

package experimental.annotations.fq.test

@Experimental(Experimental.Level.ERROR)
annotation class A

@A
fun f() = 1

@UseExperimental(experimental.annotations.fq.test.A::class) // error reported here
fun useIt() = f()
