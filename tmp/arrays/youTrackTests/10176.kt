// Original bug: KT-8715

data class Rect (
  var left: Int = 0,
  var top: Int = 0,
  var right: Int = 0,
  var bottom: Int = 0)
{
  fun set(rect: Rect) {
    this.left = rect.left
    this.top = rect.top
    this.right = rect.right
    this.bottom = rect.bottom
  }
}
