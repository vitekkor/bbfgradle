fun box(): String {
try {
        emptyArray()
    }
    catch(foo: TypeCastException) {}!! == return ""
}