// Original bug: KT-32207

class Test<X, T> {

    fun hereIdeaFail(values : List<Int>, others : List<String>): List<Test<out Int, out String>> {
        return values.map { left(it) }.plus(others.map { right(it) })
    }

    companion object {

        fun <L> left(left: L): Test<L, Nothing> = TODO()

        fun <R> right(right: R): Test<Nothing, R> = TODO()
    }
}
