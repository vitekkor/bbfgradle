// Original bug: KT-842

fun foo(s: String) {
    s.trim()  //not marked unresolved, works correctly, but doesn't refer to implementation, because it is declared in library.jet
    s.toUpperCase() //it isn't declared in library.jet, so it has a reference to String.kt
}
