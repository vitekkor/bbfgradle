// Original bug: KT-31325

class MissesConstructor(
    val field1: String,
    val field2: String,
    val field3: String,
    val field4: String,
    val field5: String,
    val field6: String
) {

    fun aFunction(): String {
        return field1 + field2 + field3 + field4 + field5 + field6
    }
}
