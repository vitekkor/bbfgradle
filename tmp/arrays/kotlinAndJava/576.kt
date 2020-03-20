//File A.java
import kotlin.Metadata;

public final class A {
   private byte b;

   public final char c(short d) {
      return (char)(this.b + (byte)d);
   }

   public final byte getB() {
      return this.b;
   }

   public final void setB(byte var1) {
      this.b = var1;
   }

   public A(byte b) {
      this.b = b;
   }
}


//File Main.kt


fun box() : String {
    if(A(10.toByte()).c(20.toShort()) != 30.toByte().toChar()) return "plus failed"

    var x = 20.toByte()
    var y = 20.toByte()
    val foo = {
        x++
        ++x
    }

    if(++x != 21.toByte() || x++ != 21.toByte() || foo() != 24.toByte() || x != 24.toByte()) return "shared byte fail"
    if(++y != 21.toByte() || y++ != 21.toByte() || y != 22.toByte()) return "byte fail"

    var xs = 20.toShort()
    var ys = 20.toShort()
    val foos = {
        xs++
        ++xs
    }

    if(++xs != 21.toShort() || xs++ != 21.toShort() || foos() != 24.toShort() || xs != 24.toShort()) return "shared short fail"
    if(++ys != 21.toShort() || ys++ != 21.toShort() || ys != 22.toShort()) return "short fail"
 
    var xc = 20.toChar()
    var yc = 20.toChar()
    val fooc = {
        xc++
        ++xc
    }

    if(++xc != 21.toChar() || xc++ != 21.toChar() || fooc() != 24.toChar() || xc != 24.toChar()) return "shared char fail"
    if(++yc != 21.toChar() || yc++ != 21.toChar() || yc != 22.toChar()) return "char fail"
 
    return "OK"
}

