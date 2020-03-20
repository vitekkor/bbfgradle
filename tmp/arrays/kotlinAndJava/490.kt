//File Main.kt


fun box(): String? {
    val c: C? = C()
    val arrayList = arrayOf(c?.calc(), "")
    return arrayList[0]
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class C {
   @NotNull
   public final String calc() {
      return "OK";
   }
}
