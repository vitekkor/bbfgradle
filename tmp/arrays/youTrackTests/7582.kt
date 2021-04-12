// Original bug: KT-26599

class Test {
    fun doAThing(param1: String) {

    }

    fun doAThingIfPresent(param1: String?) {
        if (param1 != null) {
            doAThing(param1)
        }
    }
}
