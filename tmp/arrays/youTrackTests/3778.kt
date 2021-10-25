// Original bug: KT-38043

class JsonMapper(
	val config:Config = Config.Default
) {
	data class Config(
		val lineSeparator:String = "\n"
	) {
		init {
			require(lineSeparator in validLineSeparators) { "Line Separator should be '\\n', '\\r' or '\\r\\n'." }
		}
		companion object {
			@JvmStatic val Default = Config()
			private val validLineSeparators = arrayOf("\n", "\r", "\r\n")
		}
	}
}

fun main(){
	println(JsonMapper())
}
