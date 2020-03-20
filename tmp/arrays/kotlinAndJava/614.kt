//File Template.java
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Template {
   @NotNull
   private final ArrayList collected = new ArrayList();

   @NotNull
   public final ArrayList getCollected() {
      return this.collected;
   }

   public final void unaryPlus(@NotNull String $this$unaryPlus) {
      this.collected.add($this$unaryPlus);
   }

   public final void test() {
      this.unaryPlus("239");
   }
}


//File Main.kt


fun box() : String {
    val u = Template()
    u.test()
    return if(u.collected.size == 1 && u.collected.get(0) == "239") "OK" else "fail"
}

