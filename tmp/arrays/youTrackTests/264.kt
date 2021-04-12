// Original bug: KT-45647

interface Foo {
  interface Bar
}

sealed class Bar : Foo.Bar {

 class Bar1(val a: Int) : Bar()

 class Bar2(val b: Int) : Bar()

}

fun foo(bar: Bar) = when (bar) {
 is Bar.Bar1 -> 1
 is Bar.Bar2 -> 2
}
