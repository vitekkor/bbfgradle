// Original bug: KT-20433

private fun foo(root: String): String {
    try {
        return root.let { path ->
            try {
                if (!random()) {
                    return root
                }
                return "fail $root"
            } catch (e: Exception) {
                println("Exception $e")
            }
            "fail"
        }
    } finally {
        print("Finally block")
    }
}

fun random() = false

fun main(args: Array<String>) {
    foo("OK")
}
