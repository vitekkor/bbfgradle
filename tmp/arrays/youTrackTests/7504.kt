// Original bug: KT-18608

fun getInt(v: String): Int = TODO("some error prone calculation")
fun getLong(v: Int): Long = TODO("some error prone calculation")

val res1: Long =  try { getInt("oops").let { getLong(it) } }
  catch (e: Throwable) { getLong(0) }
