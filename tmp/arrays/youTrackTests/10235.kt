// Original bug: KT-4444

fun test() {
    val map = HashMap<String, String>()
    
    synchronized(map) {
        map["hi"] = "bye" // Unexpected error: "Expected value of type ???"
    }
}
