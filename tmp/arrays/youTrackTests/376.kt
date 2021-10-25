// Original bug: KT-41063

class DemoApplication {
    var name: String? = null;
}

fun main() {
    val invoice = DemoApplication().also { app ->
        app.name = "Kite"
        val names = listOf("Anne", "Peter", "Jeff")
        names.forEach loop@{ name ->
            if (1 == 1) return@loop
            println(name)
        }
    }
}
