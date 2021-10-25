// Original bug: KT-34713

fun main() {
    foo(true);
}
fun foo(someBool:Boolean) {
    if (someBool) {
        println("test1");
    } else if (false) {
        println("test2");
    }
    println("test3");
}
