// Original bug: KT-3589

abstract class X(val containingElement: X?) {
    init {
        containingElement?.children?.add(this) // here
    }

    val children: MutableList<X> = ArrayList<X>()
}
