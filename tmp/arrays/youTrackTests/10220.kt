// Original bug: KT-8148

fun main(args: Array<String>) {
    test {
        "xxxx"        
    }
}

fun test(p: () -> String) : String {
    try {
        try {
            return p()
        } finally {
            println("1")
        }
    } finally {
        throw RuntimeException("fail")
    }
}
