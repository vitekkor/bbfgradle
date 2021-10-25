// Original bug: KT-21725

class View {
    var elevation: Int = 0
}

object ViewCompat {
    @Deprecated(
        message = "Please don't use this anymore",
        replaceWith = ReplaceWith("view.elevation = elevationValue")
    )
    fun setElevation(view: View, elevationValue: Int) {
        view.elevation = elevationValue
    }
}

class Main {
    fun main() {
        ViewCompat.setElevation(View(), 10)
    }
}
