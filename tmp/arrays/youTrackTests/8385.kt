// Original bug: KT-21889

fun main(args: Array<String>) {
    BOneObj //make B obj initialize before CThreeObj, so init will be BOneObj -> CThreeObj -> BTwoObj (And BOneObj in list will be null)
    CThreeObj.list.map { it.toString() } // here is null pointer exception, cause one of B objects is null
}

abstract class A {
    abstract val list: List<B>
}
abstract class B(val a: A)

object BOneObj: B(CThreeObj)
object BTwoObj: B(CThreeObj)
object CThreeObj : A() {
    override val list: List<B> = listOf(BOneObj, BTwoObj)
}

