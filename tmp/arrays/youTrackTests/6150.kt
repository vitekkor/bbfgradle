// Original bug: KT-31455

var s: String = "" // String is green, code completion provided, but no go to declaration, no quick doc, no find usages.
var a: Any = "" // Any is green, code completion provided, but no go to declaration, no quick doc, no find usages.
var b: StringBuilder? = null // Everything is provided for StringBuilder.
fun f() {
    println() // Everything is provided for println().
}
