// Original bug: KT-19723

inline fun String.fire(message: String? = null) {
        val res = this + message!!
}

fun main(args: Array<String>) {
    val receiver = "receiver"
    "".let {
        {
            receiver.fire()
        }
    }
}