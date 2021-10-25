// Original bug: KT-25540

package foo

inline fun fooTopLevelFun(): String = "foo#fooTopLevelFun"

class FooClass {
    inline fun fooClassMethod(): String = "foo.FooClass#fooClassMethod"
}
