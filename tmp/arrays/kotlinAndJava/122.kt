//File Main.kt
var holder = ""

var mainShape: Shape? = null

fun getShape(): Shape? {
    holder += "getShape1()"
    mainShape = Shape("fail")
    return mainShape
}

fun getOK(): String {
    holder += "->OK"
    return "OK"
}


fun box(): String {
    getShape()?.getShape2()?.result = getOK();

    if (holder != "getShape1()->getShape2()->OK") return "fail $holder"

    return mainShape!!.innerShape!!.result
}



//File Shape.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Shape {
   @Nullable
   private Shape innerShape;
   @NotNull
   private String result;

   @Nullable
   public final Shape getInnerShape() {
      return this.innerShape;
   }

   public final void setInnerShape(@Nullable Shape var1) {
      this.innerShape = var1;
   }

   @Nullable
   public final Shape getShape2() {
      MainKt.setHolder(MainKt.getHolder() + "->getShape2()");
      this.innerShape = new Shape(this.result);
      return this.innerShape;
   }

   @NotNull
   public final String getResult() {
      return this.result;
   }

   public final void setResult(@NotNull String var1) {
      this.result = var1;
   }

   public Shape(@NotNull String result) {
      super();
      this.result = result;
   }
}
