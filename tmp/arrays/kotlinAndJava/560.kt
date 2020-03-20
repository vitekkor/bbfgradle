//File Main.kt


fun box(): String =
    if (Cell('a').value in 'a'..'z')
        "OK"
    else
        "fail"



//File Cell.java
import kotlin.Metadata;

public final class Cell {
   private final Object value;

   public final Object getValue() {
      return this.value;
   }

   public Cell(Object value) {
      this.value = value;
   }
}
