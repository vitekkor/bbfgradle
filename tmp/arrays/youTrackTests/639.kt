// Original bug: KT-43374

fun npcTypeFromQuestNpc(): Int {
    val episode = true

    return when (6) {

        5 -> when (7) {
            0 -> if (episode) 5 else 4
            1 -> if (episode) 5 else 4
            else -> if (episode) 5 else 4
        }

        else -> 5
    }
}

