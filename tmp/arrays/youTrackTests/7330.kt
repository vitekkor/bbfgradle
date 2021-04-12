// Original bug: KT-21214

class Some {
    fun <T> log (string:String, f:() -> T ):T {
        return f()
    }
}

fun main(args: Array<String>) {
    Some().log("mymessage") {
        29
    }
}
