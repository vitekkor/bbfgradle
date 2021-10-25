// Original bug: KT-26499

// Inspection "Array property in data class: it's recommended to override equals() / hashCode()"
data class Test(val str: String, val strArray: Array<String>? = null)
