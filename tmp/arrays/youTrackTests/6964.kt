// Original bug: KT-22540

class MyService(val name: String)

class MyServiceProvider() {
    private lateinit var service: MyService
    private val initLock: Any = Object()

    public fun getOrCreateService(): MyService {
        synchronized(initLock) {
            if (!this::service.isInitialized) {
                service = MyService("foo")
            }
            return service
        }
    }
}


fun main(args: Array<String>) {
    println(MyServiceProvider().getOrCreateService().name)
}
