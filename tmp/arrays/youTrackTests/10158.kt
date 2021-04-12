// Original bug: KT-9064

class Test(private val _member: String) {

    private val _parameters =  listOf(1..2).map {
        object {
            val annotations = _member
        }
    }
}
