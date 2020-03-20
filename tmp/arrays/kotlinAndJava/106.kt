//File AbstractClass.java
import kotlin.Metadata;

public abstract class AbstractClass {
   public final Object printSome() {
      return this.getSome();
   }

   public abstract Object getSome();
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
fun box() = Class().printSome()



//File Class.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Class extends AbstractClass {
   @NotNull
   public String getSome() {
      return "OK";
   }
}
