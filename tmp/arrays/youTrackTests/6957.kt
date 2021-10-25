// Original bug: KT-22108

fun main(vararg args: String) {

    data class Step(val name: String, val completed: Boolean)

    val steps = listOf(
            Step("one", true),
            Step("two", true),
            Step("three", true),
            Step("four", false),
            Step("five", false))

    for (step in steps) {

        // Original, use Alt+Enter on the 'for' keyword
        var isNavigable = true
        for (s in steps) {
            if (s == step)
                break
            if (!s.completed) {
                isNavigable = false
                break
            }
        }

        // Incorrect suggested replacement
        val incorrectSuggestedReplacement = steps
                .takeWhile { it != step }
                .any { it.completed }

        // Correct replacement
        val correctReplacement = steps
                .takeWhile { it != step }
                .all { it.completed }
    }
}
