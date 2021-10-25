// Original bug: KT-30414

fun main() {
    run label@{
        return@label if (true) { // Replace return with 'if' expression
            42
        } else {
            42
        }
    }
}
