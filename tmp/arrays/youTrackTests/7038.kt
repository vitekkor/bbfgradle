// Original bug: KT-17431


import java.lang.ref.WeakReference
import java.util.*

class LabelHolder {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // just run it, and you'll get VerifyError
        }
    }

    private val labels = IdentityHashMap<String?, WeakReference<String>>()
    private val String?.label: String
        get(): String = labels.getOrPutWeak(this) { "hello" }
}

// removing 'inline' removes this error
inline fun <K, V> MutableMap<K, WeakReference<V>>.getOrPutWeak(key: K, defaultValue: ()->V): V {
    val value = get(key)?.get()
    return if (value == null) {
        val answer = defaultValue()
        put(key, WeakReference(answer))
        answer
    } else {
        value // adding 'as V' removes this error
    }
}
