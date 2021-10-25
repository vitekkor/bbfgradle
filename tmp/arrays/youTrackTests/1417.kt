// Original bug: KT-25085

object A {
    private var _a: String = "default"  //complains: Object or top-level property name '_a' should not start with an underscore
    var a: String
        get() = _a
        private set(value) {
            _a = value
        }
}
