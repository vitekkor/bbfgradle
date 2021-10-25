// Original bug: KT-8803

interface A
interface B : A

fun fn(value: Any) {
    if (value is B) {
        if (value is A) { // [USELESS_IS_CHECK] Check for instance is always 'true'

        }
    }
}
