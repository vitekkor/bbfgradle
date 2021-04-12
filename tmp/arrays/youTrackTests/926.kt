// Original bug: KT-42195

sealed class Tree<TIndex, out TCommon, out TInner, out TLeaf> {
    abstract val value: TCommon
    abstract val children: Map<TIndex, Tree<TIndex, TCommon, TInner, TLeaf>>

    data class Inner<TIndex, TCommon, TInner, TLeaf>(
        override val value: TCommon,
        val innerValue: TInner,
        override val children: Map<TIndex, Tree<TIndex, TCommon, TInner, TLeaf>>
    ) : Tree<TIndex, TCommon, TInner, TLeaf>()

    data class Leaf<TIndex, TCommon, TLeaf>(
        override val value: TCommon,
        val leafValue: TLeaf
    ) : Tree<TIndex, TCommon, Nothing, TLeaf>() {
        override val children: Map<TIndex, Tree<TIndex, TCommon, Nothing, TLeaf>> get() = emptyMap()
    }
}
