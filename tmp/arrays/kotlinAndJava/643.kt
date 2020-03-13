//File Main.kt


fun box(): String {

  val u: Unit = when(En.A) {
    En.A -> {}
    En.B -> {}
  }

  return "OK"
}



//File En.java
import kotlin.Metadata;

public enum En {
   A,
   B;
}
