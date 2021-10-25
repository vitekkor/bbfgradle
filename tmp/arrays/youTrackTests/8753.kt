// Original bug: KT-19141

class User(private val field: String) {
    override fun equals(other: Any?): Boolean {
        if (other is User) {
            val o = other as User // USELESS_CAST
            return field == o!!.field
        }
        return false
    }
}
