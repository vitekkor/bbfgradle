// Original bug: KT-6653

interface Trouble {
    val message: String?
}

class NotFoundException(mes: String): RuntimeException(mes), Trouble {
    override val message: String?
        get() {
            return "qwer"
        }
}
