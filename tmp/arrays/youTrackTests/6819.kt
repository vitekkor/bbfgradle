// Original bug: KT-10846

class CrashMe {
  companion object {
    private const val CRASH_ME = "CRASH_ME"

    fun crash(): String {
      return CRASH_ME
    }
  }
}

fun main(args: Array<String>) {
  println(CrashMe.crash())
}
