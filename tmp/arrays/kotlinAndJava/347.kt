//File Parser.java
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class Parser {
   @NotNull
   private final Function1 f;

   @NotNull
   public final Result invoke(Object input) {
      return (Result)this.f.invoke(input);
   }

   @NotNull
   public final Parser mapJoin(@NotNull final Function1 selector, @NotNull final Function2 projector) {
      return new Parser((Function1)(new Function1() {
         @NotNull
         public final Result invoke(Object input) {
            Result res = Parser.this.invoke(input);
            Result var10000;
            if (res instanceof Result.ParseError) {
               var10000 = (Result)(new Result.ParseError(((Result.ParseError)res).getProductionLabel(), ((Result.ParseError)res).getChild(), ((Result.ParseError)res).getRest()));
            } else {
               if (!(res instanceof Result.Value)) {
                  throw new NoWhenBranchMatchedException();
               }

               Object v = ((Result.Value)res).getValue();
               Result res2 = ((Parser)selector.invoke(v)).invoke(((Result.Value)res).getRest());
               if (res2 instanceof Result.ParseError) {
                  var10000 = (Result)(new Result.ParseError(((Result.ParseError)res2).getProductionLabel(), ((Result.ParseError)res2).getChild(), ((Result.ParseError)res2).getRest()));
               } else {
                  if (!(res2 instanceof Result.Value)) {
                     throw new NoWhenBranchMatchedException();
                  }

                  var10000 = (Result)(new Result.Value(projector.invoke(v, ((Result.Value)res2).getValue()), ((Result.Value)res2).getRest()));
               }
            }

            return var10000;
         }
      }));
   }

   @NotNull
   public final Function1 getF() {
      return this.f;
   }

   public Parser(@NotNull Function1 f) {
      super();
      this.f = f;
   }
}


//File Result.java
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Result {
   private Result() {
   }

   // $FF: synthetic method
   public Result(DefaultConstructorMarker $constructor_marker) {
      this();
   }

   public static final class Value extends Result {
      private final Object value;
      private final Object rest;

      public final Object getValue() {
         return this.value;
      }

      public final Object getRest() {
         return this.rest;
      }

      public Value(Object value, Object rest) {
         super((DefaultConstructorMarker)null);
         this.value = value;
         this.rest = rest;
      }
   }

   public static final class ParseError extends Result {
      @NotNull
      private final String productionLabel;
      @Nullable
      private final Result.ParseError child;
      private final Object rest;

      @NotNull
      public final String getProductionLabel() {
         return this.productionLabel;
      }

      @Nullable
      public final Result.ParseError getChild() {
         return this.child;
      }

      public final Object getRest() {
         return this.rest;
      }

      public ParseError(@NotNull String productionLabel, @Nullable Result.ParseError child, Object rest) {
         super((DefaultConstructorMarker)null);
         this.productionLabel = productionLabel;
         this.child = child;
         this.rest = rest;
      }
   }
}


//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
//KT-10934 compiler throws UninferredParameterTypeConstructor in when block that covers all types

fun box() = "OK"

