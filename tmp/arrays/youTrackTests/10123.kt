// Original bug: KT-4015

package foo
object Bar
fun main(args: Array<String>) {
	println(Bar is Bar) // OK, but only inside package :(
	println(Bar is foo.Bar) // OK, but only fully qualified name works :(
}
