// Original bug: KT-26678

    fun idCandidates(baseId: String): Sequence<String> = sequence {
        yield(baseId)
        for (suffix in 'a'..'z') {
            yield(baseId + suffix)
        }
    }
    