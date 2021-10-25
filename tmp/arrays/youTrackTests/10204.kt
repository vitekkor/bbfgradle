// Original bug: KT-3299

fun main(args: Array<String>) {

//    val a = try{throw Exception("error")} catch(e: Exception) {null}
//    val b = try{throw Exception("error")} catch(e: Exception) {null}
    test(
            try{throw Exception("error")} catch(e: Exception) {null},//a,
            try{throw Exception("error")} catch(e: Exception) {null} //b
    )

}

fun test(a: Any?, b: Any?) = println("$a, $b")
