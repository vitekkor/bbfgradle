// Original bug: KT-22476

fun convert(c: String): String? = c

fun foo(superTypes: List<String>) {
  val result = mutableListOf<String>()
  for (superType in superTypes) {
    val superDescriptor = convert(superType)
    if (superDescriptor != null) {
      result.add(superDescriptor)
    }
  }
}
