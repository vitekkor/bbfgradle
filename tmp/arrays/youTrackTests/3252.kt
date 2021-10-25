// Original bug: KT-32514

// move/cr/p25674/ref/Reference.kt
package move.cr.p25674.ref
fun refer() {
    println("package move.cr.p25674.to\n\nclass MovedClass {\n    fun movedMember() {}\n}\n") // problem
    println("move.cr.p25674.to.MovedClass") // correct
    println("move.cr.p25674.to.MovedClass.movedMember") // correct
}
