// Original bug: KT-5307

fun main(args: Array<String>) {
   val value = "OK"
    when (value) {
        "" -> {
            print("OK")
        }

        "OK" -> when (value) {
            "entry" -> false
        }
    }
}
