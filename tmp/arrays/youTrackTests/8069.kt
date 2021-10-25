// Original bug: KT-16570

package xyz.nulldev
class Huh {
    private interface UriFilter

    open class Filter<V>(val name: String, val state: V) {
        open class CheckBox(name: String, defValue: Boolean): Filter<Boolean>(name, defValue)
        open class Select<V>(name: String, state: Array<V>): Filter<Array<V>>(name, state)
        open class Group<V>(name: String, state: List<V>): Filter<List<V>>(name, state)
    }

    class AdvancedOption(name: String, defValue: Boolean = false): Filter.CheckBox(name, defValue), UriFilter
    class RatingOption : Filter.Select<String>("", arrayOf("")), UriFilter
    class AdvancedGroup : Filter.Group<Filter<*>>("", listOf(
            AdvancedOption("", true),
            AdvancedOption("", true),
            AdvancedOption(""),
            AdvancedOption(""),
            AdvancedOption(""),
            AdvancedOption(""),
            AdvancedOption(""),
            AdvancedOption(""),
            RatingOption()
    ))

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val test = Huh.AdvancedGroup()
            println(test.name)
            println(test.state)
        }
    }
}
