// Original bug: KT-37545

class IndentationIssue {

    fun method(): String {
        return X.combine(
                1,
                1
            ) { x, y -> "xxx" }
            .let { "zzz" }
    }
}

object X {
    fun <L, R, T> combine(x: L, y: R, combine: (L, R) -> T): T = combine(x, y)
}
