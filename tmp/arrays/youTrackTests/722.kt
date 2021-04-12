// Original bug: KT-42694

class App {
    private lateinit var deviceId: String

    fun onCreate() {
        buggy = this
        okay = this
        deviceId = "123"
    }

    companion object {
        @get:Synchronized
        private lateinit var buggy: App

        private lateinit var okay: App

        val buggyDeviceId: String
            get() = buggy.deviceId

        val okayDeviceId: String
            get() = okay.deviceId
    }
}

fun main() {
    App().onCreate()
    println(App.okayDeviceId)
    println(App.buggyDeviceId)
}
