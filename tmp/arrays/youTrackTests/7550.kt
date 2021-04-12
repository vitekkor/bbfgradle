// Original bug: KT-27004

    fun <K,V> mapOfNonNullValues(vararg pairs: Pair<K, V?>?): Map<K,V> = pairs.fold(mapOf()) { agg: Map<K,V>, v ->
        return if(v?.second != null){
            agg.plus(v.first to v.second!!) // v.second is not null already
        }else{
            agg
        }
    }
