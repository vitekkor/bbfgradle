// Original bug: KT-33621

import java.lang.annotation.Documented

@Documented //annotation is marked as depricated and it's recommended to use kotlin's *@MustBeDocumented*
annotation class MyAnnotation
