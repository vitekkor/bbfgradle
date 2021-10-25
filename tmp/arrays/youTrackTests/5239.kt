// Original bug: KT-33325

class G<T>

fun <FROM, TO> G<FROM>.ext(f: (FROM) -> TO): G<TO> = TODO()
@JvmName("ext2")
fun <SAME> G<SAME>.ext(f: (SAME) -> Unit): G<SAME> = TODO()

fun intToString(n: Int): String = TODO()

val ext: G<String> = G<Int>().ext(::intToString) // compilation errors
