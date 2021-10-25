// Original bug: KT-38267

import java.io.InputStreamReader
import java.io.Reader

interface I {
    fun foo(reader: Reader): String?
}

class C {
    fun bar(resourceName: String, list: List<I>) {
        val results = list.mapNotNull {
            javaClass.getResourceAsStream(resourceName).use { stream ->
                it.foo(InputStreamReader(stream))
            }
        }
    }
}
