//File AbstractClass.java
import kotlin.Metadata;

public abstract class AbstractClass {
   public abstract Object getSome();
}


//File Main.kt


fun box(): String = Class().some



//File Class.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Class extends AbstractClass {
   @NotNull
   public String getSome() {
      return "OK";
   }
}
