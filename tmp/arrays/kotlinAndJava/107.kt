//File Main.kt


fun box(): String = when(En.A) {
    En.A -> "OK"
    En.B -> "Fail 1"
}



//File En.java
import kotlin.Metadata;

public enum En {
   A,
   B;
}
