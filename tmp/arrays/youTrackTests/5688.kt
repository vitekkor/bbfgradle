// Original bug: KT-32808

abstract class StoresThings<T>(protected val storedThings: Array<T>)

class StoresAsList<T>(things: Array<T>): StoresThings<T>(things) {
    val things: List<T> = this.storedThings.asList()
}
