// Original bug: KT-36932

   /**
    * A generic ordered collection of elements. Methods in this interface support only read-only access to the list;
    * read/write access is supported through the [MutableList] interface.
    * @param E the type of elements contained in the list. The list is covariant in its element type.
    */
   public interface List<out E> : Collection<E> {
       // Query Operations
   
       override val size: Int
       override fun isEmpty(): Boolean
       override fun contains(element: @UnsafeVariance E): Boolean
       override fun iterator(): Iterator<E>
   
       // Bulk Operations
       override fun containsAll(elements: Collection<@UnsafeVariance E>): Boolean
   
       // Positional Access Operations
       /**
        * Returns the element at the specified index in the list.
        */
       public operator fun get(index: Int): E
   
       // Search Operations
       /**
        * Returns the index of the first occurrence of the specified element in the list, or -1 if the specified
        * element is not contained in the list.
        */
       public fun indexOf(element: @UnsafeVariance E): Int
   
       /**
        * Returns the index of the last occurrence of the specified element in the list, or -1 if the specified
        * element is not contained in the list.
        */
       public fun lastIndexOf(element: @UnsafeVariance E): Int
   
       // List Iterators
       /**
        * Returns a list iterator over the elements in this list (in proper sequence).
        */
       public fun listIterator(): ListIterator<E>
   
       /**
        * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified [index].
        */
       public fun listIterator(index: Int): ListIterator<E>
   
       // View
       /**
        * Returns a view of the portion of this list between the specified [fromIndex] (inclusive) and [toIndex] (exclusive).
        * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa.
        *
        * Structural changes in the base list make the behavior of the view undefined.
        */
       public fun subList(fromIndex: Int, toIndex: Int): List<E>
   }
   