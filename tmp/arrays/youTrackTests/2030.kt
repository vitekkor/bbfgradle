// Original bug: KT-10777

class Test<T>(val t: Collection<T>) {
    val something : String
    init {
        @Suppress("CAST_NEVER_SUCCEEDS", "UNUSED_VARIABLE")
        something = t as String
    }
}
