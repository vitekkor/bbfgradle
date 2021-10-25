// Original bug: KT-23282

class A {
	private var _b: String? = null

	fun b(): String {
		var b = this._b
		if (b == null) {
			b = synchronized(this) {
				var _b = this._b
				if (_b == null) {
					_b = "test"
				}
				_b
			}
		}
		return b // ERROR: Type mismatch: inferred type is String? but String was expected
	}
}
