// Original bug: KT-5861

fun <K,T,V> List<K>.partition(filter:(K)->Boolean, transformA:(K)->T, transformB:(K)-> V) :Pair<List<T>, List<V>> {
            val listA = ArrayList<T>()
            val listB = ArrayList<V>()
            for (element in this) if (filter(element)) listA.add(transformA(element)) else listB.add(transformB(element))
            return Pair(listA, listB)
        }
 