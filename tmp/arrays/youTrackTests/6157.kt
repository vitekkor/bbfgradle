// Original bug: KT-29666

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.SOURCE) // [DEPRECATED_JAVA_ANNOTATION] This annotation is deprecated in Kotlin. Use '@kotlin.annotation.Retention' instead
annotation class Permission
