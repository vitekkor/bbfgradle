// Original bug: KT-40277

interface MS<T> : MutableSet<T>

abstract class AbstractStringStringMap : MutableMap<String, MS<String>> by HashMap<String, MS<String>>() {
  abstract operator override fun get(key: String): MS<String>
}
