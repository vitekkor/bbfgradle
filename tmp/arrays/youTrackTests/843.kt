// Original bug: KT-41032

interface MyTests

abstract class MyTestsImpl(transform: (String) -> String) : MyTests

class ConcreteTest : MyTests by object : MyTestsImpl(transform = { it }) {} {
    // ...
}
