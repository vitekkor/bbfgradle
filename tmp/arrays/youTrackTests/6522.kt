// Original bug: KT-26707

open class Range() {
 companion object {
    @JvmField val EQUAL: Byte = 0
    @JvmField val MODIFIED: Byte = 1
    @JvmField val INSERTED: Byte = 2
    @JvmField val DELETED: Byte = 3
  }
}
