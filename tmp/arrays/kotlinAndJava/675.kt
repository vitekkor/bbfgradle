//File Test.java
import java.io.Serializable;
import java.util.RandomAccess;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class Test implements RandomAccess, Cloneable, Serializable {
   @NotNull
   public Test clone() {
      return new Test();
   }

   @NotNull
   public String toString() {
      return "OK";
   }
}


//File Main.kt


fun box() = Test().clone().toString()

