// Original bug: KT-9154

fun foo() {
    var a: Int    
    @Suppress("")  a = 1 // Error: this annotation is not applicable to target 'expression'
    @Suppress("") a += 1 // Surprisingly Ok
}
