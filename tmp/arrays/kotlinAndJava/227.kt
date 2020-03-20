//File ADouble.java
import kotlin.Metadata;

public final class ADouble {
   private double value;

   public final double get(int i) {
      return this.value;
   }

   public final void set(int i, double newValue) {
      this.value = newValue;
   }

   public final double getValue() {
      return this.value;
   }

   public final void setValue(double var1) {
      this.value = var1;
   }

   public ADouble(double value) {
      this.value = value;
   }
}


//File AShort.java
import kotlin.Metadata;

public final class AShort {
   private short value;

   public final short get(int i) {
      return this.value;
   }

   public final void set(int i, short newValue) {
      this.value = newValue;
   }

   public final short getValue() {
      return this.value;
   }

   public final void setValue(short var1) {
      this.value = var1;
   }

   public AShort(short value) {
      this.value = value;
   }
}


//File AByte.java
import kotlin.Metadata;

public final class AByte {
   private byte value;

   public final byte get(int i) {
      return this.value;
   }

   public final void set(int i, byte newValue) {
      this.value = newValue;
   }

   public final byte getValue() {
      return this.value;
   }

   public final void setValue(byte var1) {
      this.value = var1;
   }

   public AByte(byte value) {
      this.value = value;
   }
}


//File AInt.java
import kotlin.Metadata;

public final class AInt {
   private int value;

   public final int get(int i) {
      return this.value;
   }

   public final void set(int i, int newValue) {
      this.value = newValue;
   }

   public final int getValue() {
      return this.value;
   }

   public final void setValue(int var1) {
      this.value = var1;
   }

   public AInt(int value) {
      this.value = value;
   }
}


//File ALong.java
import kotlin.Metadata;

public final class ALong {
   private long value;

   public final long get(int i) {
      return this.value;
   }

   public final void set(int i, long newValue) {
      this.value = newValue;
   }

   public final long getValue() {
      return this.value;
   }

   public final void setValue(long var1) {
      this.value = var1;
   }

   public ALong(long value) {
      this.value = value;
   }
}


//File Main.kt


fun box(): String {
    val aByte = AByte(1)
    var bByte: Byte = 1

    val aShort = AShort(1)
    var bShort: Short = 1

    val aInt = AInt(1)
    var bInt: Int = 1

    val aLong = ALong(1)
    var bLong: Long = 1

    val aFloat = AFloat(1.0f)
    var bFloat: Float = 1.0f

    val aDouble = ADouble(1.0)
    var bDouble: Double = 1.0
    
    aByte[0]++
    bByte++
    if (aByte[0] != bByte) return "Failed post-increment Byte: ${aByte[0]} != $bByte"

    aByte[0]--
    bByte--
    if (aByte[0] != bByte) return "Failed post-decrement Byte: ${aByte[0]} != $bByte"

    aShort[0]++
    bShort++
    if (aShort[0] != bShort) return "Failed post-increment Short: ${aShort[0]} != $bShort"

    aShort[0]--
    bShort--
    if (aShort[0] != bShort) return "Failed post-decrement Short: ${aShort[0]} != $bShort"

    aInt[0]++
    bInt++
    if (aInt[0] != bInt) return "Failed post-increment Int: ${aInt[0]} != $bInt"

    aInt[0]--
    bInt--
    if (aInt[0] != bInt) return "Failed post-decrement Int: ${aInt[0]} != $bInt"

    aLong[0]++
    bLong++
    if (aLong[0] != bLong) return "Failed post-increment Long: ${aLong[0]} != $bLong"

    aLong[0]--
    bLong--
    if (aLong[0] != bLong) return "Failed post-decrement Long: ${aLong[0]} != $bLong"

    aFloat[0]++
    bFloat++
    if (aFloat[0] != bFloat) return "Failed post-increment Float: ${aFloat[0]} != $bFloat"

    aFloat[0]--
    bFloat--
    if (aFloat[0] != bFloat) return "Failed post-decrement Float: ${aFloat[0]} != $bFloat"

    aDouble[0]++
    bDouble++
    if (aDouble[0] != bDouble) return "Failed post-increment Double: ${aDouble[0]} != $bDouble"

    aDouble[0]--
    bDouble--
    if (aDouble[0] != bDouble) return "Failed post-decrement Double: ${aDouble[0]} != $bDouble"
    
    return "OK"
}



//File AFloat.java
import kotlin.Metadata;

public final class AFloat {
   private float value;

   public final float get(int i) {
      return this.value;
   }

   public final void set(int i, float newValue) {
      this.value = newValue;
   }

   public final float getValue() {
      return this.value;
   }

   public final void setValue(float var1) {
      this.value = var1;
   }

   public AFloat(float value) {
      this.value = value;
   }
}
