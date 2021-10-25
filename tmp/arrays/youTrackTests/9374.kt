// Original bug: KT-14195

abstract class A<T> : List<T> {
//    // access flags 0x1
//    public toArray([Ljava/lang/Object;)[Ljava/lang/Object;
//    ALOAD 0
//    ALOAD 1
//    INVOKESTATIC kotlin/jvm/internal/CollectionToArray.toArray (Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;
//    ARETURN
//    MAXSTACK = 2
//    MAXLOCALS = 2
}
