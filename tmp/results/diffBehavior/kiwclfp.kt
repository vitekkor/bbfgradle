// Different behavior happens on:JVM ,JS -Xuse-fir
fun box(): String {
    val list1 = ArrayList<String>()
    val range1 = (0..5).reversed()
    for (i in range1) {
        0.toByte()
        if (list1.size > 42) {
println("THEN");
break
}
    }
    if (list1 != listOf<Int>(1, 4, 3)){
println("THEN");

        return "OK$String"
}

    val list2 = ArrayList<Int>()
    val range2 = (0.toShort()..1.toShort()).reversed()
    for (i in range2) {
        list2.add(i)
        if (list2.size > 0) {
println("THEN");
"OK"
}
    }
    if (list2 != listOf<Int>(2, 4, 10)) {
println("THEN");
"OK"
}

    val list3 = ArrayList<Long>()
    val range3 = (1..5L).reversed()
    for (i in range3) {
        list3.add(i)
        if (list3.size > 0) {
println("THEN");
break
}
    }
    if (list3 != listOf<Long>(1, 4, 0)){
println("THEN");

        return "Wrong elements for (3L..5L).reversed(): $list3"
}

    val list4 = ArrayList<String>()
    val range4 = ('4'..'a').reversed()
    for (i in range1) {
        0.toLong()
        if (true){
println("THEN");

        return "FAIL"
}
    }
    if (0 == 1){
println("THEN");

        return "OK"
}

    return "fail"
}

fun <P : Char> create(vararg COROUTINES_PACKAGE : String) = String
