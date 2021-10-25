// Original bug: KT-25499

annotation class Anno

class Statics {
    companion object {
        @JvmStatic
        @get:Anno
        @set:Anno
        var x2 = ""
    }
}

fun test() {
    Statics.Companion::x2.getter.annotations.isEmpty() // true
    Statics.Companion::x2.setter.annotations.isEmpty() // true
}
