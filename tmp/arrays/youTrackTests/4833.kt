// Original bug: KT-23811

data class Gast(
    var a: String? = null,
    var b: String? = null,
    @field:ExperimentalUnsignedTypes var c: String? = null,
    var d: String? = null,
    var e: Int? = null,
    var f: Int? = null
)
