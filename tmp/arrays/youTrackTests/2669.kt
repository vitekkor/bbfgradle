// Original bug: KT-37585

inline fun <T> Collection<T>.indexOfOrNull(element: T): Int? {
    //will use the list indexOf if this is a list, thus this should be as optimal as the other collection extensions
    return when (val index = indexOf(element)) { 
        -1 -> null
        else -> index
    }
}
