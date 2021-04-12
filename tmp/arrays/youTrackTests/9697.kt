// Original bug: KT-6621

object X {
    operator fun get(key: String) : String = "adf"
    operator fun set(key: String, value: String) {}
}

fun fn() {
    val c = X["sdfsdf"]
    val x = X
    x["sdfsd"] = "sdf" // ok
    X["sdfsd"] = "sdf" // error
}
