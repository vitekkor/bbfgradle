//File Hierarchy.java
import kotlin.Metadata;

public interface Hierarchy {
}


//File Main.kt


fun box(): String {
    return Location().p
}



//File Location.java
import kotlin.Metadata;

public final class Location extends Persistent implements Hierarchy {
   public Location() {
      super("OK");
   }
}


//File Persistent.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Persistent {
   @NotNull
   private final String p;

   @NotNull
   public final String getP() {
      return this.p;
   }

   public Persistent(@NotNull String p) {
      super();
      this.p = p;
   }
}
