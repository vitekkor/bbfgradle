// Original bug: KT-21617

class AWithSecondaryInit {

    lateinit var a: String

    init {
        println()
    }

    constructor(i: Int) {
        a = i.toString()
    }

    constructor(s: String) {
        a = s
    }

}
