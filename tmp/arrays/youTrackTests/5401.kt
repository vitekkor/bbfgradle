// Original bug: KT-33381

open class ClassName() {
  protected val _viewState1 = "state" // warning
  private val _viewState2 = "state" // no warning

  init {
    _viewState1
    _viewState2
  }
}
