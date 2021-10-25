// Original bug: KT-11253

    /**
     * Returns the sum of all values produced by [selector] function applied 
     * to each element in the collection.
     */
    inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
      var sum: Long = 0
      for (element in this) {
        sum += selector(element)
      }
      return sum
    }

