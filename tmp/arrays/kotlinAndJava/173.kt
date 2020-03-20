//File Subclass.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Subclass extends BaseClass {
   @NotNull
   private final String kind = "Subclass ";

   @NotNull
   public String getKind() {
      return this.kind;
   }
}


//File BaseClass.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class BaseClass {
   @NotNull
   private final String kind = "BaseClass ";

   @NotNull
   public String getKind() {
      return this.kind;
   }

   @NotNull
   public final String getKindValue() {
      return this.getKind();
   }
}


//File Main.kt


fun box(): String {
    val r = Subclass().getKindValue() + Subclass().kind
    return if(r == "Subclass Subclass ") "OK" else "fail"
}

