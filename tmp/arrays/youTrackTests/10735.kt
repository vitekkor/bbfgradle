// Original bug: KT-2109

import org.xml.sax.Attributes


fun Attributes?.each(f :(String, String) -> Unit) {
    if (this == null) {
        return
    }
    val length = getLength() // should be able to call a method here but I get "only safe call are allowed ... "
}

