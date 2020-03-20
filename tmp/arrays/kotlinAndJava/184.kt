//File Test.java
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Test {
   private final boolean isClosedForRead;

   @Nullable
   public final Object discardSuspend(long discarded0, long max, @NotNull Continuation $completion) {
      Object $continuation;
      label110: {
         if ($completion instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)$completion;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label110;
            }
         }

         $continuation = new ContinuationImpl($completion) {
            // $FF: synthetic field
            Object result;
            int label;
            Object L$0;
            long J$0;
            long J$1;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return Test.this.discardSuspend(0L, 0L, this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         break;
      case 1:
         max = ((<undefinedtype>)$continuation).J$1;
         discarded0 = ((<undefinedtype>)$continuation).J$0;
         this = (Test)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         if (!(Boolean)$result) {
            return Unit.INSTANCE;
         }
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while(this.isClosedForRead) {
         int $i$f$reading = false;
         if (access$setupStateForRead(this) != null) {
            try {
               int var9 = false;
               var9 = Boxing.boxBoolean(true);
            } finally {
               ;
            }
         } else {
            boolean var10000 = false;
         }

         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).J$0 = discarded0;
         ((<undefinedtype>)$continuation).J$1 = max;
         ((<undefinedtype>)$continuation).label = 1;
         Object var15 = this.readSuspend(1, (Continuation)$continuation);
         if (var15 == var12) {
            return var12;
         }

         if (!(Boolean)var15) {
            break;
         }
      }

      return Unit.INSTANCE;
   }

   private final boolean reading(Function0 block) {
      int $i$f$reading = 0;
      if (access$setupStateForRead(this) != null) {
         boolean var3;
         try {
            var3 = (Boolean)block.invoke();
         } finally {
            InlineMarker.finallyStart(1);
            InlineMarker.finallyEnd(1);
         }

         return var3;
      } else {
         return false;
      }
   }

   public final boolean isClosedForRead() {
      return this.isClosedForRead;
   }

   // $FF: synthetic method
   @Nullable
   final Object readSuspend(int size, @NotNull Continuation $completion) {
      return Boxing.boxBoolean(true);
   }

   private final Object setupStateForRead() {
      return null;
   }

   // $FF: synthetic method
   public static final Object access$setupStateForRead(Test $this) {
      return $this.setupStateForRead();
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// WITH_RUNTIME

fun box() = "OK"

