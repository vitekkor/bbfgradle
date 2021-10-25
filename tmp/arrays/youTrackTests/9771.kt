// Original bug: KT-6908

// Try to build this code
object Some

class Other {
    var _field: Int = 0
    private var Some.Field: Int
        get() = _field
        set(value) {
            _field = value
        }

    fun test() {
        Some.Field++
    }
}
