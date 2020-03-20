//File Base.java
import kotlin.Metadata;

public class Base {
   private final Object value;

   public final Object getValue() {
      return this.value;
   }

   public Base(Object value) {
      this.value = value;
   }
}


//File Main.kt


fun box(): String {
    val expected: Long? = -1L
    return if (Box().value == expected) "OK" else "fail"
}



//File Box.java
import kotlin.Metadata;

public final class Box extends Base {
   public Box() {
      super(-1L);
   }
}
