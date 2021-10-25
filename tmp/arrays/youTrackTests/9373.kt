// Original bug: KT-14188

abstract class A<T> : List<T> {
//    // access flags 0x1
//    // signature (I)TT;
//    // declaration: T removeAt(int)
//    public removeAt(I)Ljava/lang/Object;
//    NEW java/lang/UnsupportedOperationException
//    DUP
//    LDC "Mutating immutable collection"
//    INVOKESPECIAL java/lang/UnsupportedOperationException.<init> (Ljava/lang/String;)V
//    ATHROW
//    MAXSTACK = 3
//    MAXLOCALS = 2
//
//    // access flags 0x1
//    // signature (I)TT;
//    // declaration: T remove(int)
//    public remove(I)Ljava/lang/Object;
//    NEW java/lang/UnsupportedOperationException
//    DUP
//    LDC "Mutating immutable collection"
//    INVOKESPECIAL java/lang/UnsupportedOperationException.<init> (Ljava/lang/String;)V
//    ATHROW
//    MAXSTACK = 3
//    MAXLOCALS = 2
}
