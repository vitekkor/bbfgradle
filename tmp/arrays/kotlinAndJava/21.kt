//File Subclass.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Subclass extends BaseClass {
   @NotNull
   private final String kind = "Physical";
   @NotNull
   private final String kind2 = " kind2";

   @NotNull
   protected String getKind() {
      return this.kind;
   }

   @NotNull
   protected String getKind2() {
      return this.kind2;
   }
}


//File BaseClass.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public abstract class BaseClass {
   @NotNull
   private final String kind2 = " kind1";

   @NotNull
   protected abstract String getKind();

   @NotNull
   protected String getKind2() {
      return this.kind2;
   }

   @NotNull
   public final String debug() {
      return this.getKind() + this.getKind2();
   }
}


//File Main.kt


fun box():String = if(Subclass().debug() == "Physical kind2") "OK" else "fail"

