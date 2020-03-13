//File SomeClass.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class SomeClass {
   private final double some;
   private final int other;
   @NotNull
   private final String[] args;

   @NotNull
   public final String result() {
      return this.args[1];
   }

   public final double getSome() {
      return this.some;
   }

   public final int getOther() {
      return this.other;
   }

   @NotNull
   public final String[] getArgs() {
      return this.args;
   }

   public SomeClass(double some, int other, @NotNull String... args) {
      super();
      this.some = some;
      this.other = other;
      this.args = args;
   }
}


//File Main.kt


fun box(): String {
    return object : SomeClass(3.14, 42, "No", "OK", "Yes") {
    }.result()
}

