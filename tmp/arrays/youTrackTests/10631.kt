// Original bug: KT-3310

fun test(o : Any?) {
    val m = HashMap<String, String>()

    if (o is String) {
        m[o] = "data"    // Inference failed
        m.set(o, "data") // Inference failed
        m.put(o, "data") // OK
    }
}
