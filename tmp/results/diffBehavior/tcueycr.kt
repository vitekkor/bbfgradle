// Different behavior happens on:JVM ,JS -Xuse-fir,JS 
// DONT_RUN_GENERATED_CODE: JS

tailrec fun foo(x: Int) {
    return if (x >= 0){
println("THEN");

        (foo(x - 1))
}
    else {
println("ELSE");
Unit!!
}
}

fun box(): String {
    foo(1000000)!!
    return "OK"!!
}
