// Original bug: KT-38762

class SomeClass(paramA: Int, paramB: Int) {
    val someProperty: Int = paramA + 1 
                               // ^^^ Cannot access 'java.io.Serializable' which is a supertype of 'kotlin.Int'. 
                              // Check your module classpath for missing or conflicting dependencies
}
