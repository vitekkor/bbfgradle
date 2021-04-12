// Original bug: KT-22085

@Deprecated("bla", ReplaceWith("foo(strings)"))
fun test(vararg strings: String){}
fun foo(vararg strings: String){}

fun bar(){
    test()
    test("Hello", "World")
}
