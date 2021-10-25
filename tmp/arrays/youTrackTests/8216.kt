// Original bug: KT-20687

private fun getTestString(mightBeNull: String?): String {
        return java.util.function.Function<String, String> {
            mightBeNull?.let {
                return@Function "returnString"
            }
        }.apply("inputString")
    }
