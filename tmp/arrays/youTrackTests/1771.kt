// Original bug: KT-30053

import java.util.*
import org.jetbrains.annotations.PropertyKey

fun ResourceBundle.string(@PropertyKey(resourceBundle = "Message") key: String) = getString(key) ?: key
val foo = ResourceBundle.getBundle("Message").string("foo")
