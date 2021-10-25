// Original bug: KT-29994

interface Inf

class KotlinBugCase0 {
    private val outVar = "ok"

    val foo = "anything".let {
        object : Inf {
            init {
                println(outVar)
            }
        }
    }
}

class KotlinBugCase1 {
    val foo = "anything"?.let {
        object : Inf {
            init {
                println("ok")
            }
        }
    }
}

class KotlinBugCase2 {
    private val outVar = "wrong"

    val foo = "anything"?.let {
        object : Inf {
            init {
                println(outVar)
            }
        }
    }
}

fun main(args: Array<String>) {
    KotlinBugCase0()
    KotlinBugCase1()
    KotlinBugCase2()
}
