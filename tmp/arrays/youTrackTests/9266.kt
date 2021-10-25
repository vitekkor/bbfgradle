// Original bug: KT-15328

inline fun <reified E : Enum<E>> relaxedStringToEnum(s: String?, default: E): E =
    enumValues<E>().find {it.name.toUpperCase() == s?.toUpperCase()}
        ?: default
