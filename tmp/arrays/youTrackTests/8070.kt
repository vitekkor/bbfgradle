// Original bug: KT-16570

private interface UriFilter

open class Filter<V>(val name: String, val state: V) {
    open class CheckBox(name: String, defValue: Boolean) : Filter<Boolean>(name, defValue)
    open class Select<V>(name: String, state: Array<V>) : Filter<Array<V>>(name, state)
    open class Group<V>(name: String, state: List<V>) : Filter<List<V>>(name, state)
}

open class UriGroup<V>(name: String, state: List<V>) : Filter.Group<V>(name, state), UriFilter

class AdvancedOption(name: String, val param: String, defValue: Boolean = false) : Filter.CheckBox(name, defValue), UriFilter
class RatingOption : Filter.Select<String>("", arrayOf("")), UriFilter
class AdvancedGroup : UriGroup<Filter<*>>("", listOf(
        AdvancedOption("", "", true),
        RatingOption()
))

fun main(args: Array<String>) {
    val test = AdvancedGroup()
    println(test.name)
    println(test.state)
}
