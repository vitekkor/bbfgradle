// Original bug: KT-35423

object Issue {

    @JvmStatic
    fun main(args: Array<String>) {
        val methods = this::class.java.methods
        val nonNullIntMethod = methods.first { it.name == "nonNullInt" }
        val nullableIntMethod = methods.first { it.name == "nullableInt" }

        println(nonNullIntMethod.parameters.map { it.type.canonicalName }) // int
        println(nullableIntMethod.parameters.map { it.type.canonicalName }) // java.lang.Integer
    }

    fun nonNullInt(x: Int) {
    }

    fun nullableInt(x: Int?) {
    }

}
