// Original bug: KT-18002

interface Property<T : Comparable<T>> {
    fun parseValue(value: String): T
}

interface PropertyStore {
    fun <T : Comparable<T>> setValue(property: Property<T>, value: T)
}

fun <T : Comparable<T>> parseAndApplyValue(
        propertyStore: PropertyStore,
        property: Property<T>,
        unparsedValue: String) {
    val parsedValue: T = property.parseValue(unparsedValue)
    propertyStore.setValue(property, parsedValue)
}

fun test(
        property: Property<*>,
        unparsedValue: String,
        propertyStore: PropertyStore
) {
    parseAndApplyValue(propertyStore, property, unparsedValue) // does not compile
}
