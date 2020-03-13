//File D.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class D extends C {
   @NotNull
   public String f() {
      return "D f";
   }
}


//File Main.kt


fun box(): String{
    val d : C = D()
    if(d.f() != "D f") return "fail f"
    return "OK"
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class C {
   @NotNull
   public Object f() {
      return "C f";
   }
}
