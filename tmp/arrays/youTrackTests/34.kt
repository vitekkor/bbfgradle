// Original bug: KT-45920

package pack

abstract class MindMap : MutableMap<Int, String> {
    operator fun get(key: Any?): String? = null
    operator fun set(key: Int, value: String) {}
}
