// Original bug: KT-25485

package contra

fun callContract(p: Any?) {
    require(p is String)
    p.substring(1) // Error highlighting: bug.
}

inline class SampleInline(val p: String) // No error highlighting: correct.
