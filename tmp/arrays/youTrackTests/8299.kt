// Original bug: KT-19514

class My {
    val x = 1
        get // Redundant getter
    private var y = 2
        private set // Redundant setter
    // Or even (optional)
    var z = 3
        // Both accessors are redundant
        get() = field
        set(value) {
            field = value
        }
}
