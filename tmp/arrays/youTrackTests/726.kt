// Original bug: KT-42337

interface Kla1 {

    private fun f1(): String {
        return "OK"
    }

    companion object Kla3 {
        fun f2(another: Kla1): String {
            return another.f1()
        }
    }
}

class Kla2 : Kla1


fun main() {
    println(Kla1.f2(Kla2()))
}
