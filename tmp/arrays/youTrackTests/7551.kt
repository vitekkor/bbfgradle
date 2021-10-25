// Original bug: KT-27004

fun <K,V> mapOfNonNullValues(vararg pairs: Pair<K, V?>?): Map<K,V> = pairs.fold(mapOf()) { agg: Map<K,V>, v ->
    val x = v?.second
    
    return if(x != null){
        agg.plus(v.first to x)
    }else{
        agg
    }
}
