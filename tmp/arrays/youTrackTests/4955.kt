// Original bug: KT-35065

class Class {

    constructor(field: String) {
        this.field = field
    }

    private var field: String
        set(value) {
            println("Some checking...")
            field = value
        }
}
