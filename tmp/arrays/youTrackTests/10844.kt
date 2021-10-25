// Original bug: KT-351

fun foo() {
    var z = 2
    val r = {  // type fun(): Int is inferred
        if (true) {
            2
        }
        else {
            z = 34
        }
    }
}
