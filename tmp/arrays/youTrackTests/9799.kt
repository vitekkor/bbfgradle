// Original bug: KT-7893

public fun String.replaceAll(regex: String, replacement: String): String = (this as java.lang.String).replaceAll(regex, replacement)

fun main(args: Array<String>)
{
	"".replaceAll("", "") // Overload resolution ambiguity with deprecated in kotlin stdlib
}
