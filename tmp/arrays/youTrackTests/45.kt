// Original bug: KT-45848

// commonMain 
sealed class SharedSealedClass()

// any platform-specific source set, e.g. jvmMain 
class LocalCaller : SharedSealedClass() // reported with [SEALED_INHERITOR_IN_DIFFERENT_MODULE] Inheritance of sealed classes or interfaces from different module is prohibited
