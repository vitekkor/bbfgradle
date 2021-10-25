// Original bug: KT-11499

object CrashMe {
  fun <T> crash(value: T): T? = null
}

internal inline fun <reified T> crashMe(value: T?): T? {
  return CrashMe.crash(value ?: return null)
}

fun main(args: Array<String>) {
  crashMe<String>(null)
}
