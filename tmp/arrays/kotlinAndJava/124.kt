//File Main.kt


fun box(): String {
    val fake: Any = FakeInt(42)

    val int1 = 1
    val int42 = 42

    if (fake == int1) return "FakeInt(42) == 1"
    if (fake != int42) return "FakeInt(42) != 42"
    if (int1 == fake) return "1 == FakeInt(42)"
    if (int42 == fake) return "42 == FakeInt(42)"

    return "OK"
}



//File FakeInt.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class FakeInt {
   private final int value;

   public boolean equals(@Nullable Object other) {
      return other instanceof Integer && Intrinsics.areEqual(other, this.value);
   }

   public final int getValue() {
      return this.value;
   }

   public FakeInt(int value) {
      this.value = value;
   }
}
