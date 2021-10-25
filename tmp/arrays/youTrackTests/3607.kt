// Original bug: KT-28846

package old

@Deprecated(message = "Deprecated", replaceWith = ReplaceWith("foo()", imports = ["newpackage.foo"]))
fun foo(): Int {
    return 2
}

fun test() {
    foo() // apply quick fix here
}
