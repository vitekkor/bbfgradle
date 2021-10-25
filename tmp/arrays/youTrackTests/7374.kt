// Original bug: KT-22350

package com.ealva

/**
 * Created by Eric A. Snell on 1/17/18.
 */
enum class EnumError {
  One {
    override fun doIt(): String {
      return ""
    }
  },
  Two {
    /**
     * Snafu
     */
    override fun doIt(): String {
      return ""
    }
  };

  protected abstract fun doIt(): String

  fun it(): String {
    return doIt()
  }

}
