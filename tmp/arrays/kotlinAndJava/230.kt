//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   public static final class B1 {
   }

   public static final class B2 {
      private final int x;

      public final int getX() {
         return this.x;
      }

      public B2(int x) {
         this.x = x;
      }
   }

   public static final class B3 {
      private final long x;
      private final int y;

      public final long getX() {
         return this.x;
      }

      public final int getY() {
         return this.y;
      }

      public B3(long x, int y) {
         this.x = x;
         this.y = y;
      }
   }

   public static final class B4 {
      @NotNull
      private final String str;

      @NotNull
      public final String getStr() {
         return this.str;
      }

      public B4(@NotNull String str) {
         super();
         this.str = str;
      }
   }
}


//File Main.kt



fun box(): String {
    A.B1()
    val b2 = A.B2(A.B3(42, 42).y)
    return A.B4("OK").str
}

