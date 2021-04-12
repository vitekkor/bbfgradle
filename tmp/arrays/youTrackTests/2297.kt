// Original bug: KT-42376

interface Kla0 {
    private fun fu0() = "OK"

    fun fu1() = fu0()
}

class Kla1 : Kla0 {
    private fun fu0() = "fail"
}


fun main() {
    println(Kla1().fu1())
}
