// Original bug: KT-31219

interface Intf
interface GenericIntf<T>

class Foo {
    private val generic1 by lazy {
        abstract class LocalIntf : GenericIntf<CharSequence>
        object : LocalIntf() {}
    }
}
