// Original bug: KT-41361

class Container<out T> {}
fun <T> good(): Container<T> = Container<Nothing>()
fun <T> evil(): Container<T> = Container<T>() as Container<Nothing>
//                                            ^^^^^^^^^^^^^^^^^^^^^
//                                            Unchecked cast
