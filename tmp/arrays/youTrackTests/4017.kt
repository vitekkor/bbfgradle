// Original bug: KT-8218

import java.util.*

fun <T> foo(f: () -> List<T>): T = throw Exception()
fun test() {
    val b: Int = foo { Collections.emptyList() } // [TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER] Type inference failed: Not enough information to infer parameter T in fun <T : Any!> emptyList(): (Mutable)List<T!>!
}
