// Original bug: KT-41323

class Sample {

    var data = 10
        @Synchronized get
        @Synchronized set(value) {
            // Random set logic
            field = value + 1
        }

    fun doSomething() {
        println("Value before set $data")
        data = 5
        println("Value after set $data")
    }
}

fun main() {
    Sample().doSomething()
}
