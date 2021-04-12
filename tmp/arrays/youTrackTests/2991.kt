// Original bug: KT-38282

fun thing(vararg args: Int) {

}

fun main() {
    thing(*intArrayOf()) // compiles fine, but warns (only in IDE, not in build log) that: `Remove redundant spread operator`
    // accepting the resolution `Remove redundant spread operator` brings us back to the first attempt:

}
