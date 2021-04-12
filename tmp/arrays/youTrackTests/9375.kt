// Original bug: KT-13698

abstract class A : Collection<String>
abstract class B : A(), List<String> {
//    // access flags 0x1
//    // signature (ILjava/util/Collection<+Ljava/lang/String;>;)Z
//    // declaration: boolean addAll(int, java.util.Collection<? extends java.lang.String>)
//    public addAll(ILjava/util/Collection;)Z
//    NEW java/lang/UnsupportedOperationException
//    DUP
//    LDC "Mutating immutable collection"
//    INVOKESPECIAL java/lang/UnsupportedOperationException.<init> (Ljava/lang/String;)V
//    ATHROW
//    MAXSTACK = 3
//    MAXLOCALS = 3
}
