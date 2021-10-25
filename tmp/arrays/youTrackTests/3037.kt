// Original bug: KT-37431


fun case2() {
    //x is Sequence<kotlin.Nothing> (!)
    val x = sequence {

        val  y = this //Unresolved this@null
        yield("") // UNRESOLVED_REFERENCE

        this.yield("") //UNRESOLVED_REFERENCE

        this as SequenceScope<String>

        yield("") // UNRESOLVED_REFERENCE

        this.yield("") // UNRESOLVED_REFERENCE
    }
}
