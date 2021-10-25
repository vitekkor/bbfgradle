// Original bug: KT-33366

fun a(param1: String?) {
    param1?.also {
        if (it.isNotEmpty()) {
            it.toCharArray()[0]--
        } else if (it.length == 2) { // compiles fine
            println('a')
        }
    }
}
