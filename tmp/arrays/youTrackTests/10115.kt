// Original bug: KT-9449

public class Foo(arraySize: Int, defaultArraySize: Int) {
	var byteArray: ByteArray
		private set
	protected var byteArray2: ByteArray
		private set

	init {
		val arrSize = if (arraySize > 0) arraySize else defaultArraySize
		byteArray = ByteArray(arrSize) // ERROR: This property has a custom setter, so initialization using backing field required
		byteArray2 = ByteArray(arrSize) // ERROR: This property has a custom setter, so initialization using backing field required
	}
}
