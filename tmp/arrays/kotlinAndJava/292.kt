//File Foo.java
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

public final class Foo {
   private final String fld = "O";
   @NotNull
   private final Function0 indirectFldGetter = (Function0)(new Function0() {
      @NotNull
      public final String invoke() {
         return Foo.this.getFld();
      }
   });

   private final String getFld() {
      return (String)((Function0)(new Function0() {
         @NotNull
         public final String invoke() {
            return Foo.this.fld;
         }
      })).invoke() + "K";
   }

   @NotNull
   public final Function0 getIndirectFldGetter() {
      return this.indirectFldGetter;
   }

   @NotNull
   public final String simpleFldGetter() {
      return this.getFld();
   }
}


//File Main.kt


fun box() = Foo().simpleFldGetter()

