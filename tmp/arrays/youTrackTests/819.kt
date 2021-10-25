// Original bug: KT-44234


fun main(args: Array<String>) {
    val app = App()
    app.onCreate();
    App.context.print()
}

class App {

    val context: Context = Context()

    fun onCreate() {
        instance = this
    }

    companion object {
        private lateinit var instance: App set
        val context: Context get() = instance.context
    }

}

class Context {
    fun print() {
        System.out.println("OK")
    }
}

