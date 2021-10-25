// Original bug: KT-38349

abstract class Meeting {
    abstract val occurrenceRule: Int?
}

suspend fun m(occurrence: Int?, meeting: Meeting?,
              q: suspend (start: Int?) -> Int): Int {
    var occurrenceToModify = occurrence ?: meeting?.occurrenceRule
    if (occurrenceToModify == null) {   // <-- here IDEA suggests to replace with elvis
        return q(1)
    }
    return occurrenceToModify.and(1)
}
