// Original bug: KT-13073

package debugger.testwhile

fun main(args: Array<String>) {
    while (true) {
        if (testSome()) {
            break
        }
    }
}

fun testSome() = false

/*
public final static main([Ljava/lang/String;)V
    @Lorg/jetbrains/annotations/NotNull;() // invisible, parameter 0
   L0
    ALOAD 0
    LDC "args"
    INVOKESTATIC kotlin/jvm/internal/Intrinsics.checkParameterIsNotNull (Ljava/lang/Object;Ljava/lang/String;)V
   L1
    LINENUMBER 4 L1
   L2
   L3
    LINENUMBER 5 L3
    INVOKESTATIC debugger/testwhile/Kt13059Kt.testSome ()Z
    IFEQ L4                               <------------------------- Could be L2 instead
   L5
    LINENUMBER 6 L5
    GOTO L6
   L4
    LINENUMBER 4 L4
    GOTO L2
   L6
    LINENUMBER 9 L6
    RETURN
   L7
    LOCALVARIABLE args [Ljava/lang/String; L0 L7 0
    MAXSTACK = 2
    MAXLOCALS = 1
*/
