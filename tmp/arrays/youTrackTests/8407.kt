// Original bug: KT-14145

abstract class Base {
    private var i = 1

    class Sub : Base() {
        private var i = 4  // error, hides member from supertype
    }
}

class Sub : Base() { 
    private var i = 4 // ok
}
