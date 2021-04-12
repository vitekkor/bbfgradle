// Original bug: KT-37479

import java.util.*

class Foo {

    // select from here
    private object MyBundle {

        private fun createUtf8PropertyResourceBundle(
            bundle: ResourceBundle
        ): ResourceBundle {
            return if (bundle !is PropertyResourceBundle) bundle else Utf8PropertyResourceBundle(bundle)
        }

        private class Utf8PropertyResourceBundle(
            private val bundle: PropertyResourceBundle
        ) : ResourceBundle() {

            override fun getKeys(): Enumeration<String> = bundle.keys

            override fun handleGetObject(key: String): Any? {
                val value = bundle.getString(key) ?: return null
                return String(value.toByteArray(charset("ISO-8859-1")), charset("UTF-8"))
            }
        }
    }
// to here
}
