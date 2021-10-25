// Original bug: KT-34925

// common

class Some
fun foo() {
    println(Some::class.simpleName) // [NO_REFLECTION_IN_CLASS_PATH] Call uses reflection API which is not found in compilation classpath. Make sure you have kotlin-reflect.jar in the classpath
}
