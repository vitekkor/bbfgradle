// Original bug: KT-30013

import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE)
annotation class Anno(val k1: KClass<*>, val k2: KClass<*>, val k3: KClass<*>)

fun main() {
    class L {
        fun f(): @Anno(k1 = L::class, k2 = Array<L>::class, k3 = Array<Array<L>>::class) String = "OK"
    }

    println(L::f.returnType.annotations)
}
