// Original bug: KT-7477

// A.kt
package foo

public class A {
    private val value = "A.bar"

    companion object {

        public fun bar(): String {
            return A().value
        }
    }
}
