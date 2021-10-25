// Original bug: KT-34973

data class Foo(val name: String?) {
  class Builder {
    @set:JvmSynthetic // Hide 'void' setter from Java.
    var name: String? = null

    fun setName(name: String?) = apply {
      this.name = name
    }

    fun build() = Foo(name)
  }
}
