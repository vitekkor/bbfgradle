// Original bug: KT-19575

class Relevant {
    companion object {
        val value = ""
    }
}

@Deprecated("Use Relevant")
typealias Obsolete = Relevant

val x = Obsolete.value // not marked
val y = Obsolete::class //ok 
fun f(o: Obsolete) {} //ok
