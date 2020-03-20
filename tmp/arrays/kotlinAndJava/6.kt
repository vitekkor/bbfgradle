//File ZImpl.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class ZImpl implements Z {
   @NotNull
   public String getValue() {
      return "OK";
   }

   @NotNull
   public String getZ() {
      return (String)Z.DefaultImpls.getZ(this);
   }
}


//File Z.java
import kotlin.Metadata;

public interface Z {
   Object getValue();

   Object getZ();

   public static final class DefaultImpls {
      public static Object getZ(Z $this) {
         return $this.getValue();
      }
   }
}


//File ZImpl2.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class ZImpl2 extends ZImpl {
   @NotNull
   public String getZ() {
      return super.getZ();
   }
}


//File Main.kt



fun box(): String {
    return ZImpl2().value
}

