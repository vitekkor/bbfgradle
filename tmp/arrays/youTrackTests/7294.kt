// Original bug: KT-18854

inline fun <T> Iterable<T>.forEachSpecial( other:(T) -> Unit,
                                           noinline  first: ((T) -> Unit)? = null, 
                                           noinline  last:((T) -> Unit)? = null,
                                           noinline  onSingle:((T)->Unit)? = null,
                                           noinline  onEmpty:(()->Unit)? = null):Unit{ /** ... **/ }
