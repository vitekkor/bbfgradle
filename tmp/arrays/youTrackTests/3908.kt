// Original bug: KT-24882

// API in standard library
interface StringTemplate {
    val elements: List<Element>
    
    sealed class Element {
        data class Literal(val text: String) : Element()
        data class Placeholder(val code: String, val value: Any?) : Element()
    }
}
