//File LightVariable.java
import kotlin.Metadata;

public final class LightVariable extends Variable {
}


//File Variable.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class Variable {
   @NotNull
   private final LightVariable lightVar = this instanceof LightVariable ? (LightVariable)this : new LightVariable();

   @NotNull
   public final LightVariable getLightVar() {
      return this.lightVar;
   }
}


//File Main.kt


fun box(): String {
    Variable()
    return "OK"
}

