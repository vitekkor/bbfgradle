// Original bug: KT-40152

//   // access flags 0x1
//  public remove(Ljava/lang/Object;)Z
//    NEW java/lang/UnsupportedOperationException
//    DUP
//    LDC "Operation is not supported for read-only collection"
//    INVOKESPECIAL java/lang/UnsupportedOperationException.<init> (Ljava/lang/String;)V
//    ATHROW
//    MAXSTACK = 3
//    MAXLOCALS = 2
