// Original bug: KT-14048

class KotlinMultipleInterfaceExtensionBug {
    fun compiles() {
        regularMethod(X())
        X().extensionMethodA()
        X().extensionMethodB()
    }

    fun doesNotCompile() {
        /*
Error:(13, 13) Kotlin: Unresolved reference. None of the following candidates is applicable because of receiver type mismatch:
public final fun <T : KotlinMultipleInterfaceExtensionBug.A> KotlinMultipleInterfaceExtensionBug.X.combinedExtensionMethod(): Unit where T : KotlinMultipleInterfaceExtensionBug.B defined in KotlinMultipleInterfaceExtensionBug
         */
        X().combinedExtensionMethod()
        /*
Error:(18, 13) Kotlin: Unresolved reference. None of the following candidates is applicable because of receiver type mismatch:
public final fun <T : KotlinMultipleInterfaceExtensionBug.X> KotlinMultipleInterfaceExtensionBug.Y.combinedExtensionMethodWithClassConstraint(): Unit where T : KotlinMultipleInterfaceExtensionBug.C defined in KotlinMultipleInterfaceExtensionBug
                 */
        Y().combinedExtensionMethodWithClassConstraint()
    }

    fun <T> regularMethod(t: T) where T : A, T : B {
    }

    fun <T> T.extensionMethodA() where T : A {
    }

    fun <T> T.extensionMethodB() where T : B {
    }

    fun <T> T.combinedExtensionMethod() where T : A, T : B {
    }

    fun <T> T.combinedExtensionMethodWithClassConstraint() where T : X, T : C {
    }

    interface A

    interface B

    open class X : A, B

    interface C

    open class Y : X(), C
}
