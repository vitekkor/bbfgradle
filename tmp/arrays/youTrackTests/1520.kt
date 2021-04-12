// Original bug: KT-16282

package test

operator fun Int.get(s: Int): Int = this + s

operator fun Int.set(s: Int, x: Int = 42, z: Int) {
    print("s=$s x=$x z=$z")
}

fun main(args: Array<String>) {
    1[2] = 1 // prints: s=2 x=0 z=1; expected: s=2 x=42 z=1
}
