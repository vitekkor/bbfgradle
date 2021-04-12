// Original bug: KT-28056

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class EmailAddress @PublishedApi internal constructor(private val value: String) {

    override fun toString(): String = value

    companion object {

        fun parse(string: String): EmailAddress {
            check(string.contains('@')) { "Invalid email address." }

            return EmailAddress(string)
        }
    }
}
