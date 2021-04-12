// Original bug: KT-32007

import kotlin.properties.Delegates
import kotlin.properties.Delegates.observable

fun showInfo() {
    Delegates.observable("") { _, _, _ -> } // Try Parameter+Info (Ctrl+P) inside parentheses.
    observable("") { _, _, _ -> } // Try the same.
}
