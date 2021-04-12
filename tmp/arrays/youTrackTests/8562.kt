// Original bug: KT-20106

fun isMutedOn1(emails: List<String>) : Map<String, Boolean> {
    val mutedEmails = setOf<String>()
    return emails.associate { it to (it in mutedEmails) }
}
