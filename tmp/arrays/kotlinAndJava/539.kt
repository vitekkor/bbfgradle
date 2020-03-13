//File Callback.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public interface Callback {
   @NotNull
   String invoke();
}


//File Base.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class Base implements Callback {
   @NotNull
   private final Callback fn;

   @NotNull
   public String invoke() {
      return this.fn.invoke();
   }

   @NotNull
   public final Callback getFn() {
      return this.fn;
   }

   public Base(@NotNull Callback fn) {
      super();
      this.fn = fn;
   }
}


//File Main.kt


fun box(): String {
    val ok = "OK"

    class Local : Base(
            object : Base(
                    object : Callback {
                        override fun invoke() = ok
                    }
            ) {}
    )

    return Local().fn.invoke()
}

