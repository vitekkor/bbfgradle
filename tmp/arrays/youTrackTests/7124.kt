// Original bug: KT-28070

data class EnumMeta(
    val test1: String,
    val test2: String
)

enum class EnumTest(
    val value: Long,
    val meta: EnumMeta? = null
) {
    VALUE_1(
               value = 0,
               meta = EnumMeta(
                   test1 = "test1",
                   test2 = "test2"
               )
           ),

    VALUE_2(
               value = 1
           )
}
