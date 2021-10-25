// TARGET_BACKEND: JVM
// MODULE: lib
// FILE: D.java

public class D {
    public String fieldO;

    public static String fieldK;
}

// MODULE: main(lib)
// FILE: 1.kt

// KT-3492

class MyWrongClass : D() {
}

fun box() : String {
    val clazz = MyWrongClass()
    clazz.fieldO = "O"
    D.fieldK = "K"
    return clazz.fieldO!! + D.fieldK!!
}
