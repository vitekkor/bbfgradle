// Original bug: KT-37431

class Case1() {

     fun foo() {
       //x is Sequence<kotlin.Nothing> (!)
        val x = sequence {

           val  y = this //this@R|/Case1|
            //this is Case1 instead of SequenceScope<String>
            yield("") // UNRESOLVED_REFERENCE

            this.yield("") //UNRESOLVED_REFERENCE

            this as SequenceScope<String>

            yield("") // resolved to SequenceScope.yield

            this.yield("") // resolved to SequenceScope.yield
        }
    }
}

