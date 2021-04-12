// Original bug: KT-21918

@Suppress("UNCHECKED_CAST")
public inline fun <reified T> Collection<T>.toTypedArray(): Array<T> {
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    val thisCollection = this as java.util.Collection<T>
    return thisCollection.toArray(arrayOfNulls<T>(thisCollection.size())) as Array<T>
}
