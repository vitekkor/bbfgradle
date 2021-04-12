// Original bug: KT-14651

fun main(args: Array<String>) {
    val z: Double = Double.NaN
    when(z) {
        Double.NaN -> {
            println("NAN")
        }
        else -> { println("Non nan") }
    }

    val z2: Double? = Double.NaN
    when(z2) {
        Double.NaN -> {
            println("NAN")
        }
        else -> { println("Non nan") }
    }

}
//prints now:
//Non nan
//NAN
