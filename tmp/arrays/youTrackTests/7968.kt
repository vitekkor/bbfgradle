// Original bug: KT-23871

inline fun Boolean?.orTrue() = this ?: true

val boolean:Boolean? = false
val result = boolean?.orTrue()  //MISSING: Unnecessary safe call on nullable receiver of type Boolean
