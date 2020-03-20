//File Base.java
import kotlin.Metadata;

public interface Base {
   int foo();
}


//File Main.kt


val Int.getter: Int
    get() {
        return object : Base {
            override fun foo(): Int {
                return this@getter
            }
        }.foo()
    }

fun box(): String {
    val i = 1
    if (i.getter != 1) return "getter failed"

    return "OK"
}

