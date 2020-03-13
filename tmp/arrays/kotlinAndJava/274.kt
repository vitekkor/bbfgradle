//File Slot.java
import kotlin.Metadata;

public final class Slot {
   private int vitality = 10000;

   public final int getVitality() {
      return this.vitality;
   }

   public final void setVitality(int var1) {
      this.vitality = var1;
   }

   public final void increaseVitality(int delta) {
      this.vitality += delta;
      if (this.vitality > 65535) {
         this.vitality = 65535;
      }

   }
}


//File Main.kt


fun box(): String {
  val s = Slot()
  s.increaseVitality(1000)
  return if (s.vitality == 11000) "OK" else "fail"
}

