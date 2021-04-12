// Original bug: KT-6206

package temp

// No hashCode() generated for this class
private data class DataClass(val field: String) : Base()

abstract class Base {
    // Method can be added or removed from library at some point
    override fun hashCode(): Int {
        // some crazy code here, that may be changed in future
        return 1
    }
}
