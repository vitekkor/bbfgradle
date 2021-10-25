// Original bug: KT-44633

enum class SimpleStatus {
    ERROR (*values());

    var validPreviousStates: Set<SimpleStatus>

    constructor (vararg validPreviousStates: SimpleStatus) {
        this.validPreviousStates = validPreviousStates.toSet()
    }
}

fun main() {
    SimpleStatus.ERROR
}
