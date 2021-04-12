// Original bug: KT-10729

package com.vladsch.kotlin

import org.jetbrains.annotations.PropertyKey
import java.lang.ref.SoftReference
import java.util.*

class IntentionsBundle {
    companion object {
        @JvmStatic fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any): String {
            return ResourceBundle.getBundle(BUNDLE).getString(key)
        }

        private var ourBundle: SoftReference<ResourceBundle>? = null

        private const val BUNDLE = "com.vladsch.kotlin.IntentionsBundle"

        private val bundle: ResourceBundle
            get() {
                var bundle = ourBundle?.get()

                if (bundle == null) {
                    bundle = ResourceBundle.getBundle(BUNDLE)
                    ourBundle = SoftReference<ResourceBundle>(bundle)
                }
                return bundle as ResourceBundle
            }
    }
}

