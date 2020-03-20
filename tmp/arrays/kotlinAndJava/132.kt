//File A.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class A {
   @NotNull
   private final MyQueue delayedQueue = new MyQueue();

   @NotNull
   public final MyQueue getDelayedQueue() {
      return this.delayedQueue;
   }

   public final void next() {
      while(this.delayedQueue.poll() != null) {
      }

      while(true) {
         String var10001 = this.delayedQueue.poll();
         if (var10001 == null) {
            while(true) {
               var10001 = this.delayedQueue.poll();
               if (var10001 == null) {
                  return;
               }

               this.unblock(var10001);
            }
         }

         this.unblock(var10001);
      }
   }

   public final void unblock(@NotNull String p) {
      }
}


//File Main.kt


fun box() : String {
    A().next()
    return "OK"
}



//File MyQueue.java
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

public final class MyQueue {
   @Nullable
   public final String poll() {
      return null;
   }
}
