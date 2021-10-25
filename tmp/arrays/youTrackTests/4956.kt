// Original bug: KT-35065

class Class(field: String) {

    private var field: String = field
        set(value) {
            println("Some checking...")
            field = value
        }
}
