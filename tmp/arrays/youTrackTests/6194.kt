// Original bug: KT-31112

package inspector.p30879
import inspector.p30879.B.G

val <T> T.letVar: Int; get() = 0

fun test() {
    C.G.let { it } // No warning, fixed.
    C.G.letVar // Still a warning.
}

class B { object G }
class C { object G }
