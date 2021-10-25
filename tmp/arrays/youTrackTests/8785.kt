// Original bug: KT-18937

data class Car(val isExpensive: Boolean)

val car = Car(isExpensive = true)


fun main(args: Array<String>) {
    if (car.isExpensive)
        println("Car is expensive")
}
