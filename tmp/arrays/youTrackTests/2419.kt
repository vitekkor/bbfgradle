// Original bug: KT-19554

class TestContinueInSwitchInFor {
    fun foo(cc: CharArray) {
        var a = 0
        var b = 0
        var c = 0
        var i = 0
        while (i < cc.size && cc[i] != ';') {
            when (cc[i]) {
                'a' -> a++
                'b' -> b++
                'c' -> c++
            }
            ++i
        }
        println("a = $a; b = $b; c = $c")
    }
}
