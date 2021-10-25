// Original bug: KT-45385

fun foo(): Int = 42

object O {
  private val p: String

  init {
    try {
      foo()
    } catch (e: Exception) {
      throw e
    }
    p = "OK"
  }
}
