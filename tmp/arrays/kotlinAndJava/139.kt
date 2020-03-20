//File Test.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public enum Test {
   OK;

   private final int x;
   @NotNull
   private final String str;

   public final int getX() {
      return this.x;
   }

   @NotNull
   public final String getStr() {
      return this.str;
   }

   private Test(int x, String str) {
      this.x = x;
      this.str = str;
   }

   private Test(int... xs) {
      this(xs.length + 42, "OK");
   }
}


//File Main.kt


fun box(): String =
        if (Test.OK.x == 42)
            Test.OK.str
        else
            "Fail"

