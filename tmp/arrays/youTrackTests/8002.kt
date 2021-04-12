// Original bug: KT-23888

//For Simple
inline fun <T> inlineFunction(crossinline function: () -> T): T = function()

//For Simple
fun <T> notInlineFunction(function: () -> T): T = function()

fun testFunction(value: Boolean) {
    inlineFunction {
        notInlineFunction {
            if (value) {
                notInlineFunction {
                    // this Lambda unresolved references
                }
            }
        }
    }
}
