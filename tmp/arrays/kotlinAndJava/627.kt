//File Main.kt


fun box(): String {
    when(En.A) {
        En.A -> "s1"
        En.B -> "s2"
    }
    return "OK"
}



//File En.java
import kotlin.Metadata;

public enum En {
   A,
   B;
}
