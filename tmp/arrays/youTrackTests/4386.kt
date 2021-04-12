// Original bug: KT-36710

interface PsiElement
interface SubPsi : PsiElement

interface Foo<T: PsiElement> { // e.g. Tokenizer
    fun foo(element: T)
}

interface FooFactory { // e.g. SpellcheckingStrategy
    fun getFoo(element: PsiElement): Foo<*>
}

object MyFooFactory : FooFactory {
    override fun getFoo(element: PsiElement): Foo<*> = object : Foo<SubPsi> {
        override fun foo(element: SubPsi) {}
    }
}

fun main() {
    (MyFooFactory.getFoo(object : PsiElement {}) as Foo<PsiElement>).foo(object : PsiElement {})
}
