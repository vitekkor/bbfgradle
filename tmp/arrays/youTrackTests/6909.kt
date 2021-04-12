// Original bug: KT-17945

fun main(args: Array<String>) {
    class Address(val line1: String, val line2: String, val zip: String, val country: String) {
        operator fun component1() = line1
        operator fun component2() = line2
        operator fun component3() = zip
        operator fun component4() = country
    }


    val address: Address = Address("Address line 1", "Address line 2", "12345", "Dallas")

    val (line1, line2, zip, country) = address
    println(line1)
}
