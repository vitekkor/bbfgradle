// Original bug: KT-8211

class A(b: Boolean) {
    var x: Int
    var y: Int

    init {
        if (b) {
            x = 1
            y = 2
        }
        else {
            y = 2
            x = 1
        }
    }
}
