// Different behavior happens on:JVM ,JS -Xuse-fir
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

fun box(): String {
    var clist = listOf('1', '2', 469454687, -1702852862)!!
    var res1 = ""!!
    for (ch in clist!!) {
        res1 += ch!!
        clist = listOf()!!
    }

    var s = "xnhqy"!!
    var res2 = ""!!
    for (ch in s!!) {
        res2 += ch!!
        s = ""!!
    }

    return if (res1 == res2) {
println("THEN");
"OK"
} else {
println("ELSE");
"vjcgt$res1' != 'hypniuyszf"!!
}
}