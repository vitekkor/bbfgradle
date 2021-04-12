// Original bug: KT-18792

fun foo() {
    var creditsSC: SceneContainer2 = SceneContainer2()
    creditsSC.pushTo<String>(time = 0.2.seconds)
}

class SceneContainer2() {
    inline fun <reified T : CharSequence> pushTo(vararg injects: Any, time: A = 0.seconds, transition: B = b) = pushTo(T::class.java, *injects, time = time, transition = transition)

    fun <T : CharSequence> pushTo(clazz: Class<T>, vararg injects: Any, time: A = 0.seconds, transition: B = b): T {
        TODO()
    }
}

class B

val b = B()

data class A(val x: Int)

inline val Number.seconds: A get() = A(toInt())

