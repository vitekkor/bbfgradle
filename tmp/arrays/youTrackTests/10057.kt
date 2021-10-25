// Original bug: KT-4053

fun newMultiDimensionalArray(dimensionSizes: List<Int>): Any {
    return java.lang.reflect.Array.newInstance(String::class.java, *dimensionSizes.toIntArray())
}
