// Original bug: KT-43050

inline class X(val x: Any?)

interface IFoo<out T : X?> {
    fun foo(): T
}

//JVM
  // access flags 0x401
  // signature ()TT;
  // declaration: T foo()
//  public abstract foo()Lsss/X;

//JVM-IR
 // access flags 0x401
  // signature ()TT;
  // declaration: T foo-aUZL1e0()
///  public abstract foo-aUZL1e0()Lsss/X;


