// Original bug: KT-20106

fun isMutedOn2(emails: List<String>) : Map<String, Boolean> {
    val mutedEmails = setOf<String>()
    return emails.associate { it to mutedEmails.contains(it) }
}
