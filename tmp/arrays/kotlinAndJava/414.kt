//File A.java
import kotlin.Metadata;

public interface A {
   Object getProperty();

   Object a();

   public static final class DefaultImpls {
      public static Object a(A $this) {
         return $this.getProperty();
      }
   }
}


//File B.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class B implements A {
   @NotNull
   private final Object property = "fail";

   @NotNull
   public Object getProperty() {
      return this.property;
   }

   @NotNull
   public Object a() {
      return A.DefaultImpls.a(this);
   }
}


//File Main.kt


fun box() : String {
    return C().a() as String
}



//File C.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public class C extends B implements A {
   @NotNull
   private final Object property = "OK";

   @NotNull
   public Object getProperty() {
      return this.property;
   }
}
