// Original bug: KT-33822

import javax.xml.bind.annotation.XmlAttribute

class Bug {
    @XmlAttribute(tomato = "what is happening")
    val x = 0
}

fun main() {
    println("this compiled successfully yo")
}
