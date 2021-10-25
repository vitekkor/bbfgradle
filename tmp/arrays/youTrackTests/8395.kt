// Original bug: KT-19316

fun <T> cast(from: Any): T? = from as? T // Unchecked warning
val x = cast<String>(42) // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
