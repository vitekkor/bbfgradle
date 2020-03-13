//File Father.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class Father {
   public abstract class InClass {
      @NotNull
      public abstract String work();
   }
}


//File Main.kt


fun box(): String {
    return Child().test().work()
}



//File Child.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Child extends Father {
   @NotNull
   public final Father.InClass test() {
      return (Father.InClass)(new Father.InClass() {
         @NotNull
         public String work() {
            return "OK";
         }
      });
   }
}
