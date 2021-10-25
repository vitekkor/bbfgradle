// Original bug: KT-33346

// move/cr/p25674/ref/Reference.kt
package move.cr.p25674.ref
fun refer() {
    println("move/cr/p25674/from")
    println("move/cr/p25674/from/Moved.kt")
    println("move.cr.p25674.from.MovedClass")
    println("move.cr.p25674.from.MovedClass.movedMember")
}
