// Original bug: KT-33583

class Some 
fun main() {
    println(Some::class.qualifiedName) // `qualifiedName` is reproted with `[NO_REFLECTION_IN_CLASS_PATH] Call uses reflection API which is not found in compilation classpath. Make sure you have kotlin-reflect.jar in the classpath`
}
