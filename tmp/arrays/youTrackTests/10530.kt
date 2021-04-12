// Original bug: KT-1798

package java.lang

class Test {
    public class SomeOtherClass() {
    }

//    class object {
//        public class SomeOtherClass {
//        }
//    }
}

fun Test.SomeOtherClass.hello() {
}
