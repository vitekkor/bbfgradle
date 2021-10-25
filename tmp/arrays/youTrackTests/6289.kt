// Original bug: KT-26604

    val a = Double.NaN // Kotlin
    val b = java.lang.Double.NaN // Java - might come from a library
    val c = 0.0 / 0.0
    val d = java.lang.Double.longBitsToDouble(0x7ff8000000000000L)
