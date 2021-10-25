// Original bug: KT-38942

fun main(args: Array<String>) {
    println(Outer().outersGetProperty)
    println(Outer.outersCompanion)
    println(Outer.Inner.innersCompanion)
}

class Outer {

    val outersGetProperty: List<Inner>
        get() = Inner.innersCompanion

    companion object {
        val outersCompanion: List<Inner> = listOf(Inner.First, Inner.Second)
    }

    open class Inner {
        object First : Inner()
        object Second : Inner()

        companion object {
            val innersCompanion: List<Inner> = listOf(First, Second)

            //used now version, it works fine
            //val innersCompanion: List<Inner> by lazy { listOf(First, Second) } 
        }
    }
}
