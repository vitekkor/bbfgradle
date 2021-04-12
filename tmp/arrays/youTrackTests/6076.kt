// Original bug: KT-17832

class Bound { val boundMaterial = 0 }

class PatientA<T : Bound>(val property: T) {
    fun use() = println(property.boundMaterial)
}

class PatientB<T>(val property: T) where T : Bound {
    fun use() = println(property.boundMaterial)
} 