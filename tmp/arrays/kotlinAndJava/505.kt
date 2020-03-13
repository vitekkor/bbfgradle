//File Obj.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class Obj {
   public static final Obj INSTANCE;

   private Obj() {
   }

   static {
      Obj var0 = new Obj();
      INSTANCE = var0;
   }

   public static final class Inner {
      @NotNull
      public final String ok() {
         return "OK";
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// IGNORE_BACKEND: JS_IR
// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS, NATIVE
// WITH_RUNTIME

fun box() : String {
    val klass = Obj.Inner::class.java
    val cons = klass.getConstructors()!![0]
    val inner = cons.newInstance(*(arrayOfNulls<String>(0) as Array<String>))
    return "OK"
}

