// Original bug: KT-23708

class POS {
    val display = Display()

    fun displayPrice(price: String) {
        showPrice(price)
    }

    fun showPrice(price: String) {
        display.message = price
    }
}

class Display {
    var message: String = "Hello"
}
