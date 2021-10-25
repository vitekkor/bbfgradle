// Original bug: KT-28584

private fun <T> myFunction(collection: Collection<T>)
{
    // Smart cast doesn't work
    val list1 = if ((collection is List) && (collection is RandomAccess)) collection else ArrayList(collection)

    // Smart cast OK
    val list2: List<T> = if ((collection is List) && (collection is RandomAccess)) collection else ArrayList(collection)

    // Smart cast OK
    val list3 = if (collection is List) collection else ArrayList(collection)
}
