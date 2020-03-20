//File Test.java
import kotlin.Metadata;

public enum Test {
   OK;
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
//WITH_RUNTIME

import kotlin.test.assertEquals

fun box(): String {
    assertEquals(Test.OK.ordinal, 0)
    assertEquals(Test.OK.name, "OK")
    
    return "OK"
}

