// Original bug: KT-36932

           public fun <T> List<T>.last(): T {
               if (isEmpty())
                    throw NoSuchElementException("List is empty.")
                return this[lastIndex]
            }
            