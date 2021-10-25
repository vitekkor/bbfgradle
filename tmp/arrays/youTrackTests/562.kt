// Original bug: KT-42397

package knlibrary

public class Car(val horsepowers: Int) {
    companion object {
        fun makeCar(horsepowers: Int): Car {
            return Car(horsepowers)
        }
    }
}
