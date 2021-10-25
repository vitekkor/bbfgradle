// Original bug: KT-23329

class JsonParser {
    companion object {
        private val jTokenParser = oneOf(
                JsonParsers.jArray
        )
    }
}

class JsonParsers() {
    companion object {

        internal val nonEmptyArray = JsonParser()

        val jArray: JsonParser = nonEmptyArray
    }
}

fun oneOf(vararg x: JsonParser) {
    for (a in x) {
        println(a) // print null
    }
}

fun main(args: Array<String>) {
    JsonParsers.nonEmptyArray
}
