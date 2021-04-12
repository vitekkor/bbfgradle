// Original bug: KT-36810

class A {
}

fun m() {
}


fun getJavaPrimitiveType1():Class<*>? {
    // JVM:
    //  GETSTATIC java/lang/Integer.TYPE
    // JVM_IR:
    //  GETSTATIC java/lang/Integer.TYPE : Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return Int::class.javaPrimitiveType
}

fun getJavaPrimitiveType2():Class<*>? {
    // JVM:
    //  GETSTATIC java/lang/Integer.TYPE
    // JVM_IR:
    //  LDC Ljava/lang/Integer;.class
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return Integer::class.javaPrimitiveType
}

fun getJavaPrimitiveType3():Class<*>? {
    // JVM:
    //  GETSTATIC java/lang/Void.TYPE
    // JVM_IR:
    //  LDC Ljava/lang/Void;.class
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return Void::class.javaPrimitiveType
}

fun getJavaPrimitiveType4():Class<*>? {
    // JVM:
    //  GETSTATIC java/lang/Boolean.TYPE
    // JVM_IR:
    //  GETSTATIC java/lang/Boolean.TYPE : Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return Boolean::class.javaPrimitiveType
}

fun getJavaPrimitiveType5():Class<*>? {
    // JVM:
    //  ACONST_NULL
    // JVM_IR:
    //  LDC LA;.class
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return A::class.javaPrimitiveType
}

inline fun <reified T : Any> getJavaPrimitiveType6():Class<*>? {
    // Both JVM and JVM_IR:
    //  INVOKESTATIC kotlin/jvm/internal/Intrinsics.reifiedOperationMarker
    //  LDC Ljava/lang/Object;.class
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType
    return T::class.javaPrimitiveType
}

fun getJavaPrimitiveType7():Class<*>? {
    // Both JVM and JVM_IR:
    //  INVOKEVIRTUAL java/lang/Object.getClass
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType
    return A()::class.javaPrimitiveType
}

fun getJavaPrimitiveType8():Class<*>? {
    val i:Int? = 1
    // JVM:
    //  GETSTATIC java/lang/Integer.TYPE
    // JVM_IR:
    //  ALOAD 0
    //  INVOKEVIRTUAL java/lang/Object.getClass ()Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return i!!::class.javaPrimitiveType
}

fun getJavaPrimitiveType9():Class<*>? {
    val i:Int = 1
    // JVM:
    //  GETSTATIC java/lang/Integer.TYPE
    // JVM_IR:
    //  GETSTATIC java/lang/Integer.TYPE : Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return i::class.javaPrimitiveType
}

fun getJavaPrimitiveType10():Class<*>? {
    // Both JVM and JVM_IR:
    //  INVOKEVIRTUAL java/lang/Object.getClass
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaPrimitiveType
    return m()::class.javaPrimitiveType
}

