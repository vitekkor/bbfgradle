// Original bug: KT-8803

interface A
interface B: A

fun fn(value: Any?) {
    if (value is B) {
        if (value != null) {} // Correct due to smartcast
        if (value is A) {}  // Incorrect
    }
}
