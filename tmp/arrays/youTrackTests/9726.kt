// Original bug: KT-4441

fun foo(name : String, body : String.(String)->Unit) {}
fun foo(body : String.(String)->Unit) {}

fun test() {
    foo("sdfsd") {}  // ok, with name
    foo {} // ok, without name
    foo { value -> // error, should be without name
    }
}
