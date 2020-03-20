//File A.java
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

public final class A {
   private String r = "fail";

   @NotNull
   public final String getR() {
      return "OK";
   }
}


//File Main.kt
// TARGET_BACKEND: JVM
// WITH_RUNTIME

@file:JvmMultifileClass

fun box() = A().getR()

