// Original bug: KT-45466

abstract class MultiTypeListAdapterWithDiff<T> {
    fun getItemViewType(position: Int): Int = 5
}

open class SimpleListAdapterWithDiff<ItemType>() : MultiTypeListAdapterWithDiff<ItemType>() {
    fun getItemViewType(item: ItemType): Int = 5
}

class LastSeenProductsSkeletonAdapter : SimpleListAdapterWithDiff<Int>()
