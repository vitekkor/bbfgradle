//File A.java
import kotlin.Metadata;

public enum A {
   V;
}


//File Main.kt


fun box(): String {
    val a: A = A.V
    when (a) {
        A.V -> return "OK"
    }
}

// 1 TABLESWITCH
// 0 LOOKUPSWITCH
// 1 ATHROW

