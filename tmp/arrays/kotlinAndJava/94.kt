//File AccessMode.java
import kotlin.Metadata;

public enum AccessMode {
   READ,
   WRITE,
   EXECUTE;
}


//File Main.kt


fun whenExpr(access: AccessMode) {
    when (access) {
        AccessMode.READ -> {}
        AccessMode.WRITE -> {}
        AccessMode.EXECUTE -> {}
    }
}

fun box(): String {
    whenExpr(AccessMode.EXECUTE)
    return "OK"
}

// 1 TABLESWITCH
// 0 LOOKUPSWITCH
// 0 ATHROW

