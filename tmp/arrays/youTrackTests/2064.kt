// Original bug: KT-43044

inline class Result<T>(val a: Any?)

// JVM-IR
// access flags 0x9
// signature <T:Ljava/lang/Object;>(Ljava/lang/Object;)Ljava/lang/Object;
// declaration:  constructor-impl<T>(java.lang.Object)
// public static constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

// OLD backend
//public static constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
