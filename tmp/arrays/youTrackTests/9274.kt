// Original bug: KT-15073

class Logger(val name: String) {
    operator fun String.invoke(s: String) = this@Logger.name + this  + s
}


fun main(arg: Array<String>) {
    println(with(Logger("AAA")) { "bbb"("OK") })
    println(with(Logger("AAA")) { "bbb".invoke("OK") })
}
