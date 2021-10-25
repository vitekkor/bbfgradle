// Original bug: KT-28785

fun <T: List<*>?> T.foo() {
    if (this != null) {
        this.last() // unsafe call
        this?.last() // Unnecessary safe call on a non-null receiver of type T
    }
}
