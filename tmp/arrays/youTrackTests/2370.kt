// Original bug: KT-36724

package test

abstract class AbstractStringIterable : Iterable<String> {
    // throwing stub: public iterator()Ljava/util/Iterator;
}

abstract class AbstractStringCollection : Collection<String> {
    // throwing stub: public iterator()Ljava/util/Iterator;
}

abstract class AbstractStringSet : Set<String> {
    // throwing stub: public iterator()Ljava/util/Iterator;
}

abstract class AbstractStringList : List<String> {
    // throwing stub: public listIterator()Ljava/util/ListIterator;
    // throwing stub: public listIterator(I)Ljava/util/ListIterator;
    // throwing stub: public subList(II)Ljava/util/List;
    // NO iterator()Ljava/util/Iterator;
}
