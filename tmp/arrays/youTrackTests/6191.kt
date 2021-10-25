// Original bug: KT-31155

class SampleClass(sampleField: Int, val callback: (sampleField: Int) -> Unit) {
	var sampleField = sampleField
	fun callCallback0() = callback(sampleField)
	val callCallback1 = {
		callback(sampleField)
	}
}
