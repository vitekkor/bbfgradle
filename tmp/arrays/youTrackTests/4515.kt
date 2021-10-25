// Original bug: KT-36121

interface JsonParser
interface JsonCodingParser : JsonParser

fun JsonCodingParser.parseValue(source: String): Any = TODO()
fun JsonParser.parseValue(source: String): Any = TODO()

fun testDecoding(decode: (String) -> Any) {}
