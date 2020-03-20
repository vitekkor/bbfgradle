//File LongR.java
import kotlin.Metadata;

public final class LongR {
   public final boolean contains(long l) {
      return l == (long)5;
   }
}


//File Main.kt


fun box(): String {
  if (5 !in LongR()) return "fail 1"
  if (6 in LongR()) return "fail 2"
  return "OK"
}

