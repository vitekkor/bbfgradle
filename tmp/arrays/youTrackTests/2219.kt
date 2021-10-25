// Original bug: KT-14911

infix fun Double?.fuzzyEquals(other: Double?): Boolean {
    // If the doubles are equal or are both null
    if (this == other) {
        return true
    }
    // Since we know that both aren't null, if one of them is null then we should return false
    if (this === null || other === null) {
        return false
    }
    
    // If the value is finite, use ULPs. If the value is infinite
    // then return false, as "this == other" would have already
    // handled it if the infinities were equal.
    if (other.isFinite()) {
        return Math.abs(this - other) <= Math.max(Math.ulp(this), Math.ulp(other))
    }
    
    return false
}
