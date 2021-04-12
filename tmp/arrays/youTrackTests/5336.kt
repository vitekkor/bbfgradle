// Original bug: KT-19803

val property: String get() {
    // Long block body likely present here. 
    // Declaring the get() on the same line results in code 
    // symmetrical with function declarations.
    return "Foobar"
}
