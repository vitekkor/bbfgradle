// Original bug: KT-18937

data class Car(val isCheap: Boolean)

val car = Car(isCheap = false)


fun main(args: Array<String>) {
    if (!car.isCheap)
        println("Car is expensive")
}
