// Original bug: KT-3894

fun test1() : String {
    while(true) {
        try {
            println("Try")
            if (true) {
                break
            }

        } finally {
            println("Finally")
            return ""
        }
    }
}