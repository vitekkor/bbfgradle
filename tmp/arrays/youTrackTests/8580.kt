// Original bug: KT-18631

annotation class CollectionValue1(val value: Array<String>)
@CollectionValue1(emptyArray()) class CollectionValueUserAB
@CollectionValue1(value = emptyArray()) class CollectionValueUserAC 
