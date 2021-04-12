// Original bug: KT-10110

class MyList<T> : ArrayList<T>() {
    val subList = arrayListOf<T>()

    inline fun removeHeader(predicate: (T) -> Boolean) {
        val run = subList.firstOrNull { predicate(it) }?.run {
            subList.remove(this)
            super.remove(this) // INVOKESTATIC test2/MyList.access$remove$s-1976407926 (Ltest2/MyList;Ljava/lang/Object;)Z
        }
    }
}
