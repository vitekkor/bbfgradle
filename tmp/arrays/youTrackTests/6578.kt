// Original bug: KT-17525

class WithInner {
    inner class ItsInner
}
fun referInner(p: WithInner.ItsInner) {
//    val v = p::ItsInner // Incompilable, but I thought it could work as constructor reference.
} 