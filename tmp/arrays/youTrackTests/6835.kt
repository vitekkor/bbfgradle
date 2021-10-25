// Original bug: KT-10437

interface Parser<T> {
  fun parse(input: String): T
}

class TestParser : Parser<Int> {
  override fun parse(input: String) = input.toInt()
}

abstract class AbstractParser<T> : Parser<T>

class DelegatedParser : AbstractParser<Int>(), Parser<Int> by TestParser()
