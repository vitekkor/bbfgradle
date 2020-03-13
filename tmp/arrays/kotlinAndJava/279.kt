//File Tr.java
import kotlin.Metadata;

public interface Tr {
   Object getV();
}


//File Main.kt


fun box() = C().v



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C implements Tr {
   @NotNull
   private final String v = "OK";

   @NotNull
   public String getV() {
      return this.v;
   }
}
