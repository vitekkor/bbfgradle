// Original bug: KT-665

fun f(x: Long, zzz: Long = 1): Long
{
  return if (x <= 1) zzz 
         else f(x-1, x*zzz)
}

fun main(args: Array<String>)
{
  val six: Long = 6;
  System.out?.println(f(six))
}
