// Original bug: KT-41577

open class LE
class FE: LE()

interface P{
    fun open(): String
}
class FSS{
    fun opener(le: LE): P? {
        return (le as? FE)?.let { fe ->
            if(true) {
                object: P{
                    override fun open(): String {
                        println("open")
                        return doOpen(fe)
                    }
                }
            }else null
        }
    }
    fun doOpen(le: LE) = "ok"
}

fun main(){
    val fs = FSS()
    val le = FE()
    val p = fs.opener(le)
    println("done")
}
