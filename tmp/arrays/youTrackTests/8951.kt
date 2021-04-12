// Original bug: KT-13592

internal class A {

    var value: String
        set(value) {
            //useful code about to run
            val f: String = field
            f.toString()
        }

    constructor() {
        value = ""
    }
}
