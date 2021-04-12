// Original bug: KT-33346

package move.cr.p25674.ref
fun refer() {
    println("") // wrong
    println("/Moved.kt") // wrong
    println("move.cr.p25674.to.from.MovedClass") // correct
    println("move.cr.p25674.to.from.MovedClass.movedMember") // correct
}
