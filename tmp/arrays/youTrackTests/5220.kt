// Original bug: KT-32761

fun foo(x:String?, y: String?){}

fun test(x: String?, y: String?) {
    foo(x!!, y!!)  // <- two "unnecessary not-null assertions" should be reported here
}
