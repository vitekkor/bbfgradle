// Original bug: KT-25745

annotation class Anno

class Statics {
    companion object {
        @JvmStatic
        @get:Anno
        @set:Anno
        var x2 = "" // currently there is a warning
    }
}
