// Original bug: KT-24790

annotation class Anno

@Anno
data class DataCls(val a: Anno)

fun box(): String {
    val anno = DataCls::class.annotations.first() as Anno
    val d1 = DataCls(anno)
    val d2 = DataCls(anno)
    val c1 = d1.equals(d2)
    val c2 = d1.hashCode() 
    return "OK"
}
