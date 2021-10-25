// Original bug: KT-3344

package test

import java.util.HashMap
import java.util.ArrayList

class Foo(val attributes: Map<String, String>)

class Bar {
    val foos = ArrayList<Foo>()

    fun bar(foo: Foo) {
        foos.add(Foo(HashMap(foo.attributes))) // foo.attributes is unresolved but not marked
    }
}
