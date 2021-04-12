// Original bug: KT-712

fun succ(x: Byte): Byte = (x + (1 as Byte)) as Byte;
fun succ(x: Short): Short = (x + (1 as Short)) as Short;

fun main(args : Array<String>) {
  val a = succ(1 as Byte)
  val b = succ(1 as Short)
  System.out?.println(a+b)
}
