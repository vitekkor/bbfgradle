// Original bug: KT-15618

fun box(): String {    
val x = arrayOf<Int>(42)  
if (x::size.get() != 1) return "Fail"    //java.lang.NoSuchMethodError: [Ljava.lang.Object;.getSize()I
return "OK" 
}
