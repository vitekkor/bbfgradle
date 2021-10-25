// Original bug: KT-21803

import java.io.ByteArrayInputStream
import java.io.InputStream
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

object DataSourceObject {
    fun dataValue() = ByteArray(1)
}

object DataSourceCache {
    val dataValue by LazyStream()

    private class LazyStream : ReadOnlyProperty<Any, InputStream> {
        private lateinit var value: ByteArray

        override fun getValue(thisRef: Any, property: KProperty<*>): InputStream {
            synchronized(lock) {
                if (!this::value.isInitialized) {
                    val source = DataSourceObject::class.java.getDeclaredMethod(property.name)
                    value = source.invoke(DataSourceObject) as ByteArray
                }
            }

            return ByteArrayInputStream(value)
        }
    }

    private val lock = Any()
}
