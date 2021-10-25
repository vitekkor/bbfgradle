// Original bug: KT-17392

    @Suppress("UNCHECKED_CAST")
    private fun <T> remove(arr: Array<T>, elem: T): Array<T> {
        val n = arr.size
        val i = arr.indexOf(elem)
        check(i >= 0)
        val update = arrayOfNulls<Any>(n - 1)
        System.arraycopy(arr, 0, update, 0, i)
        System.arraycopy(arr, i + 1, update, i, n - i - 1)
        return update as Array<T>
    }
