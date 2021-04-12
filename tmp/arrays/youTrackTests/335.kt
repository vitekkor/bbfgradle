// Original bug: KT-45358

import org.intellij.lang.annotations.Language

fun String.onParameter(@Language("java") code: String) = 0

fun @receiver:Language("java") String.onReceiver() = 0

// + receiver().ofFunction(kotlinFunction()
//     .withName("customRule")
//     .withReceiver("kotlin.String")
//     .definedInPackage("example")
// )
fun String.customRule() = 0

fun test() {
    "".onParameter("class X {}")
    "class X {}".onReceiver()
    "class X {}".customRule()
}
