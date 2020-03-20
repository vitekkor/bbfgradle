//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_REFLECT
// TARGET_BACKEND: JVM

fun box(): String {
    require(C::class.java.getDeclaredField("x0")?.getAnnotation(Ann::class.java) != null)
    return "OK"
}



//File C.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class C {
   @Ann
   @NotNull
   public String x0;

   @NotNull
   public final String getX0() {
      String var10000 = this.x0;
      if (var10000 == null) {
         }

      return var10000;
   }

   public final void setX0(@NotNull String var1) {
      this.x0 = var1;
   }
}


//File Ann.java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Target;

@Target(
   allowedTargets = {AnnotationTarget.FIELD}
)
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.FIELD})
public @interface Ann {
}
