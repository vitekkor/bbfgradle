// Original bug: KT-43049

val map: Map<String, String> = hashMapOf("a" to "all", "b" to "bar", "c" to "code")

// JVM
//@Lorg/jetbrains/annotations/Nullable;() // invisi
// private final static Ljava/util/Map; d$delegate

// JVM_IR
// @Lorg/jetbrains/annotations/NotNull;() // invisible
//  private final static Ljava/util/Map; d$delegate
val d: String? by map
