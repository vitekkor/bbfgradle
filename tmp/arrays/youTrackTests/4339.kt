// Original bug: KT-34640

fun test(foo: String) {

    if (foo == "1") {
        println("1");
    } else if (foo == "2") {
        println("2");
    } else if (foo == "3") {
        println("3");
    }

    if (foo == "a") {
        println("a");
    } else if (foo == "b") {
        //  do b
        println("b");
    }
}
