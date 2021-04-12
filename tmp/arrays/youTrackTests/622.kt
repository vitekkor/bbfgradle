// Original bug: KT-41995

inline operator fun <reified T> Array<T>.minus(element: T) = this.excluding(element)
inline operator fun <reified T> Array<T>.minus(elements: Array<out T>) = this.excluding(elements)
inline operator fun <reified T> Array<Array<T>>.minus(element: Array<T>) = this.excludingArray(element)

inline fun <reified T> Array<T>.excluding(element: T) =
    this.filter { it != element }.toTypedArray()

inline fun <reified T> Array<T>.excluding(elements: Array<out T>) =
    this.filter { !elements.contains(it) }.toTypedArray()

inline fun <reified T> Array<Array<T>>.excludingArray(element: Array<T>) =
    this.filter { !it.contentEquals(element) }.toTypedArray()
