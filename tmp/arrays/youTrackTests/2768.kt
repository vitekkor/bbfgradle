// Original bug: KT-33744

class `Undesired forced line break` {

    annotation class A

    val ok1 get() = ""

    val ok2
        get() = "" // Optional line break

    @A
    val bug1
        get() = "" // Forced line break

    @get:A
    val bug2
        get() = "" // Forced line break
}
