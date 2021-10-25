// Original bug: KT-10688

interface GenericVisitor<T : GenericVisitor<T>> {
    fun visit(function: T.() -> Unit = {})
    fun inner()
}

interface RegularVisitor {
    fun main(function: RegularVisitor.() -> Unit = {})
    fun inner()
}

fun doesNotCompile(visitor: GenericVisitor<*>) {
    visitor.visit() {
        // This line does not compile due to "Unresolved reference: inner"
        inner()
    }
}

fun doesCompile(visitor: RegularVisitor) {
    visitor.main() {
        inner()
    }
}
