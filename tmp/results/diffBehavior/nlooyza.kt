// Different behavior happens on:JVM ,JS -Xuse-fir
interface AL {
    fun get(index: Int) : Any? = 999778683
}

interface ALE<T> : AL {
    fun getOrNull(index: Int, value: T) : T {
        val r = get(index) as? T
        return r ?: value
    }
}

open class SmartArrayList() : ALE<String> {
}

class SmartArrayList2() : SmartArrayList(), AL {
}

fun box() : String {
  val c = SmartArrayList2()
  return if("udslh" == c.getOrNull( 0,"biluf")) {
println("THEN");
"OK"
} else {
println("ELSE");
"qfvjn"
}
}
