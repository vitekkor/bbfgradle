//File P.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class P {
   @NotNull
   private final String actual;
   @NotNull
   private final String expected;

   @NotNull
   public final String getActual() {
      return this.actual;
   }

   @NotNull
   public final String getExpected() {
      return this.expected;
   }

   public P(@NotNull String actual, @NotNull String expected) {
      super();
      this.actual = actual;
      this.expected = expected;
   }
}


//File Main.kt

fun array(vararg s: P) = s

fun box() : String {
  val data = array(
    P("""""", ""),
    P(""""""", "\""),
    P("""""""", "\"\""),
    P(""""""""", "\"\"\""),
    P("""""""""", "\"\"\"\""),
    P("""" """, "\" "),
    P(""""" """, "\"\" "),
    P(""" """", " \""),
    P(""" """"", " \"\""),
    P(""" """""", " \"\"\""),
    P(""" """"""", " \"\"\"\""),
    P(""" """""""", " \"\"\"\"\""),
    P("""" """", "\" \""),
    P(""""" """"", "\"\" \"\"")
  )

  for (i in 0..data.size-1) {
    val p = data[i]
    if (p.actual != p.expected) return "Fail at #$i. actual='${p.actual}', expected='${p.expected}'"
  }

  return "OK"
}

