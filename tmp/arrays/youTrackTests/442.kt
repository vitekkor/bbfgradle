// Original bug: KT-40749

fun badRefactorProblem(): Collection<Long> {
    return listOf(1L, 2L).map {
        try {
            //Comment
            it
        } catch (ignored: Throwable) {
            null
        }
    }.filterNotNull()
}
