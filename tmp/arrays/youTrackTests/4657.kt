// Original bug: KT-32646

// Kotlin
package pack

class MockDevicepackageManager {
    fun fooBar() {}
}

class DebugLog {
    companion object {
        @JvmStatic
        fun d(s: String) {
        }
    }
}


var s = "MDM"
fun main() {
    DebugLog.d("MDM")
    Class.forName("MDM")
}

