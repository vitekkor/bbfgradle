//File ClassData.java
import kotlin.Metadata;

public interface ClassData {
}


//File Main.kt


fun f() = object : ClassData {
    val someInt: Int
        get() {
            return 5
        }
}

fun box(): String{
    f()
    return "OK"
}

