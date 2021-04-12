// Original bug: KT-22950

class Config(var id: Int)

val map = hashMapOf<String, Config>()

fun configurationFor(str: String) = map[str]?.run {
    ->
    apply {
        id = 42
    }
}
