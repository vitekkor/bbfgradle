// Original bug: KT-42883

@Deprecated("")
class A

// While compiler shows warning on deprecated elements
// Highlighting ignores them 
fun main() {
    val answer: HashMap<Int, Int> = hashMapOf()
    answer.maxBy { it.value } // maxBy should be marked as Deprecated 

    A() // highlighted as deprecated
}
