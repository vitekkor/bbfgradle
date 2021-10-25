// Original bug: KT-27299

import javax.xml.parsers.DocumentBuilderFactory

fun f(x: DocumentBuilderFactory) {
	// mock around with this comment
	fun DocumentBuilderFactory.foo() {
		schema.newValidator().errorHandler = null
	}
	x.foo()
}
