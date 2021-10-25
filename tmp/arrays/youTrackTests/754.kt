// Original bug: KT-44118

external fun <T : Any> require(path: String): T

class Dictionary {
  val words: Array<String> = arrayOf()
}

fun example() {
  val dictionary = require<Dictionary>("./dictionary/it.json")
  dictionary.words.forEach(::println)
}
