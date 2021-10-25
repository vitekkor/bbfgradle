// Original bug: KT-34813

class T(val p:Int?)

fun <T>T.capture(): List<T> = listOf(this)

// infered type: List<Int?>
fun r1(t :T?): List<Int?> = if (t == null) 10.capture() else t.p.capture()

//after convertion infered type: List<Int>
fun r2(t :T?): List<Int> = t?.p?.capture() ?: 10.capture()

fun main(){
    println(r2(T(null)))
    println(r1(T(null)))
}
