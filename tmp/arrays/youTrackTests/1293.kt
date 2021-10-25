// Original bug: KT-23619

inline fun <T> mrun(block: () -> T): T {
    return block()
}

var result = "fail"
class Foo {
    fun bar(obj: String) =
       mrun { //inline lambda 
            { //this lambda would be transformed with new class name, old class would be deleted
                result = obj;
                { "K" } //stateless singleton lambda with incorrect class info
            } ()
        }
    }

fun box(): String {
    val bar = Foo().bar("OK")
    val clazz = bar::class.java

    clazz.enclosingClass //fails at runtime

    return "smth"
}
