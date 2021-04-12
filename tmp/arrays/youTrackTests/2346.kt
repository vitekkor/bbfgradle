// Original bug: KT-40152

//   // access flags 0x1
//  public remove(Ltest/SimplePlatform;)Z
//    // annotable parameter count: 1 (visible)
//    // annotable parameter count: 1 (invisible)
//    @Lorg/jetbrains/annotations/NotNull;() // invisible, parameter 0
//   L0
//    ALOAD 1
//    LDC "element"
//    INVOKESTATIC kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
//    NEW java/lang/UnsupportedOperationException
//    DUP
//    LDC "Operation is not supported for read-only collection"
//    INVOKESPECIAL java/lang/UnsupportedOperationException.<init> (Ljava/lang/String;)V
//    ATHROW
//   L1
//    LOCALVARIABLE this Ltest/TargetPlatform; L0 L1 0
//    LOCALVARIABLE element Ltest/SimplePlatform; L0 L1 1
//    MAXSTACK = 3
//    MAXLOCALS = 2

//   // access flags 0x51
//  public final bridge remove(Ljava/lang/Object;)Z
//    // annotable parameter count: 1 (visible)
//    // annotable parameter count: 1 (invisible)
//    @Lorg/jetbrains/annotations/Nullable;() // invisible, parameter 0
//   L0
//    ALOAD 1
//    INSTANCEOF test/SimplePlatform
//    IFNE L1
//    ICONST_0
//    IRETURN
//   L1
//    ALOAD 0
//    ALOAD 1
//    CHECKCAST test/SimplePlatform
//    INVOKEVIRTUAL test/TargetPlatform.remove (Ltest/SimplePlatform;)Z
//    IRETURN
//   L2
//    LOCALVARIABLE this Ltest/TargetPlatform; L0 L2 0
//    LOCALVARIABLE element Ljava/lang/Object; L0 L2 1
//    MAXSTACK = 2
//    MAXLOCALS = 2
