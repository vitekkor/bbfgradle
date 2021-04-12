// Original bug: KT-36809

class A

fun m() {
}

fun getJavaObjectType1():Class<*> {
    // JMV:
    //  LDC Ljava/lang/Integer;.class
    // JVM_IR:
    //  GETSTATIC java/lang/Integer.TYPE : Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return Int::class.javaObjectType
}

fun getJavaObjectType2():Class<*> {
    // JVM:
    //  LDC Ljava/lang/Integer;.class
    // JVM_IR:
    //  LDC Ljava/lang/Integer;.class
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return Integer::class.javaObjectType
}

fun getJavaObjectType3():Class<*> {
    // JVM:
    //  LDC Ljava/lang/Void;.class
    // JVM_IR:
    //  LDC Ljava/lang/Void;.class
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return Void::class.javaObjectType
}

fun getJavaObjectType4():Class<*> {
    // JVM:
    //  LDC Ljava/lang/Boolean;.class
    // JVM_IR:
    //  GETSTATIC java/lang/Boolean.TYPE : Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return Boolean::class.javaObjectType
}

fun getJavaObjectType5():Class<*>? {
    // JVM:
    //  LDC LA;.class
    // JVM_IR:
    //  LDC LA;.class
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return A::class.javaObjectType
}

inline fun <reified T : Any> getJavaObjectType6(): Class<*> {
    // JVM:
    //  INVOKESTATIC kotlin/jvm/internal/Intrinsics.reifiedOperationMarker
    //  LDC Ljava/lang/Object;.class
    // JVM_IR:
    //  INVOKESTATIC kotlin/jvm/internal/Intrinsics.reifiedOperationMarker (ILjava/lang/String;)V
    //  LDC Ljava/lang/Object;.class
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return T::class.javaObjectType
}

fun getJavaObjectType7():Class<*> {
    // JVM:
    //  INVOKEVIRTUAL java/lang/Object.getClass
    // JVM_IR:
    //  INVOKEVIRTUAL java/lang/Object.getClass ()Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return A()::class.javaObjectType
}

fun getJavaObjectType8():Class<*> {
    val i: Int? = 1
    // JVM:
    //  LDC Ljava/lang/Integer;.class
    // JVM_IR:
    //  INVOKEVIRTUAL java/lang/Object.getClass ()Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return i!!::class.javaObjectType
}

fun getJavaObjectType9():Class<*> {
    val i: Int = 1
    // JVM:
    //  LDC Ljava/lang/Integer;.class
    // JVM_IR:
    //  GETSTATIC java/lang/Integer.TYPE : Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return i::class.javaObjectType
}

fun getJavaObjectType10():Class<*> {
    // JVM:
    //  GETSTATIC kotlin/Unit.INSTANCE
    //  INVOKEVIRTUAL java/lang/Object.getClass
    // JVM_IR:
    //  GETSTATIC kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //  INVOKEVIRTUAL java/lang/Object.getClass ()Ljava/lang/Class;
    //  INVOKESTATIC kotlin/jvm/internal/Reflection.getOrCreateKotlinClass (Ljava/lang/Class;)Lkotlin/reflect/KClass;
    //  INVOKESTATIC kotlin/jvm/JvmClassMappingKt.getJavaObjectType (Lkotlin/reflect/KClass;)Ljava/lang/Class;
    return m()::class.javaObjectType
}
