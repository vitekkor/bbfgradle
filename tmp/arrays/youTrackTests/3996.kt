// Original bug: KT-26157

import kotlin.properties.Delegates

fun main(args: Array<String>) {
    var foo: Int? by Delegates.observable(1) {
            _, _, new -> // new: Int?, no error
    }

    var bar: Int? by Delegates.observable(1) {
            _, _, new -> // new: Int, error
        new?.let {}
    }
}
