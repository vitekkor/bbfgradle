// Original bug: KT-10729

/*
 * Copyright 2000-2016 JetBrains s.r.o.
 * Copyright (c) 2015-2016 Vladimir Schneider <vladimir.schneider@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * based on the IntelliJ Community Edition Sources
 *
 */

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
