// Original bug: KT-13322

object Object1 {
    val y: Any = Object2.z // z is not yet initialized (?!)
    object Object2 {
        val z: Any = Object1.y
    }
}
fun use() {
    Object1.Object2.z.hashCode() // NPE in runtime
}
