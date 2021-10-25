// Original bug: KT-37848

object NothingWrapper {
  private val nothing: Nothing get() = TODO("Nothing can't be ever assigned or returned")
  val typeOfNothing = this::nothing.returnType
}
