// Original bug: KT-29698

data class Account(val id: String, val number: String)

fun main() {
    val accounts = listOf(
        Account("1", "2")
    )

    accounts
        .asSequence()
        .filter { it.id != "1" }
        .any()
}
