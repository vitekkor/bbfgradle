// Original bug: KT-17722

import java.util.function.Function

val x = Function.identity<String>() // Warning: Call to static methods in Java interfaces are deprecated in JVM target 1.6
