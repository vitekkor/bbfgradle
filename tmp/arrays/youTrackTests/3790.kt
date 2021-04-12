// Original bug: KT-38065

package com.cedarsoft.i18n

/**
 * A unique key to identify a certain piece of text
 */
class TextKey(
  /**
   * The key
   */
  val key: String,
  /**
   * The default text that may be used if no translated text is available
   */
  val defaultText: String
) {

  override fun toString(): String {
    return "$key [$defaultText]"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is TextKey) return false

    if (key != other.key) return false

    return true
  }

  override fun hashCode(): Int {
    return key.hashCode()
  }

  companion object {
    /**
     * Creates a new text key with the given default text that is also used as key
     */
    fun simple(defaultText: String): TextKey {
      return TextKey(defaultText, defaultText)
    }
  }
}
