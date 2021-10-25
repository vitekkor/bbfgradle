// Original bug: KT-41900

data class Pin(val value: String) {
    init {
        check(pinRegex.matches(value)) {
            "Incorrect pin value. Expected 3 digits"
        }
    }

    private companion object {
        val pinRegex = "^[0-9]{3}$".toRegex()
    }
}
