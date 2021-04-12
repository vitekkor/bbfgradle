// Original bug: KT-37027

open class Base
class Sub : Base()

fun foo(vararg arg: Base) {}

fun bar(base: Array<Base>, sub: Array<Sub>) {
    foo(*base)
    foo(*sub) // INAPPLICABLE: Array<Sub> is not subtype of Array<Base>, while should be Array<out Base>
}
