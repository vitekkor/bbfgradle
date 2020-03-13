//File A.java
import kotlin.Metadata;

public enum A {
   V;
}


//File Main.kt


fun box(): String {
  val a: A = A.V
  val b: Boolean
  when (a) {
    A.V -> b = true
  }
  return if (b) "OK" else "FAIL"
}

