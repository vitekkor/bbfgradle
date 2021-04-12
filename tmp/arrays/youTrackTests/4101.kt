// Original bug: KT-32165

inline fun <reified T : Enum<T>> enumFromOrNull(ifNotFound: T? = null,
                                                findAction: Function1<T, Boolean>): T? {
    return enumValues<T>().find(findAction) ?: ifNotFound
}

inline fun <reified T : Enum<T>> enumFromOr(ifNotFound: T,
                                            findAction: Function1<T, Boolean>): T {
    return enumFromOrNull(null, findAction) ?: ifNotFound
}
