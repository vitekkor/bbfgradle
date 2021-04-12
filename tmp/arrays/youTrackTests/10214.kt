// Original bug: KT-5890

import java.util.ArrayList

fun foo(l: List<String>) {
    for (i in l) {
        val list = ArrayList<Any>()
        while(true) {
            list.add(bar() ?: break)
        }
    }
}

fun bar(): Any? = null
