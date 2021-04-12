// Original bug: KT-26187

fun F(x: Int)
{
    val f: Function<Int> = if (x == 0) {
//                         ^^ Cascade if should be replaced with when
        { 42 }
    } else if (x == 1) {
        { 43 }
    } else {
        { 44 }
    }
}
