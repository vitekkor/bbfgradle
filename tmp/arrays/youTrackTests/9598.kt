// Original bug: KT-10864

fun test(l: List<String>, jl: java.util.List<String>, a: java.util.ArrayList<String>) {
    l.stream() // unresolved
    (l as java.util.List<String>).stream() // unresolved
    (l as List<String>).stream() // unresolved
    (l as java.util.Collection<String>).stream()

    jl.stream() // unresolved
    (jl as java.util.List<String>).stream() // unresolved
    (jl as java.util.Collection<String>).stream()

    a.stream() // unresolved
    (a as java.util.List<String>).stream() // unresolved
    (a as java.util.ArrayList<String>).stream() // unresolved
    (a as java.util.Collection<String>).stream()
}
