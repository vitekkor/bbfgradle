// Original bug: KT-18554

package one;
annotation class CollectionValue2(val value: IntArray)
const val cvc4a3 = 3
@CollectionValue2(intArrayOf(cvc4a3)) class CollectionValueUserV
@CollectionValue2([5]) class CollectionValueUserW
@CollectionValue2([cvc4a3]) class CollectionValueUserX 