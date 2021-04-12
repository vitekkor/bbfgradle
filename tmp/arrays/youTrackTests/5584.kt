// Original bug: KT-33366

fun a(param1: String?) {
    param1?.let {
        if (it.isNotEmpty()) {
            it.toCharArray()[0]--
        } else if (it.length == 2) {
            println('a')
        }
        println("a")
    }
}

