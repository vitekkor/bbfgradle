//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class A {
   @NotNull
   private final String field = "F";

   @NotNull
   public String getField$main() {
      return this.field;
   }

   @NotNull
   public String test$main() {
      return "A";
   }
}


//File Z.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Z extends A {
   @NotNull
   private final String field = super.getField$main();

   @NotNull
   public String test() {
      return super.test$main();
   }

   @NotNull
   public String getField() {
      return this.field;
   }
}


//File Main.kt


fun box() : String {
    val z = Z().test()
    if (z != "A") return "fail 1: $z"

    val f = Z().field
    if (f != "F") return "fail 2: $f"

    return "OK"
}

