// Original bug: KT-8148

fun main(args: Array<String>) {
    try {
        try {
            return
        } finally {
            println("1")
        }
    } finally {
        throw RuntimeException("fail")
    }
}

