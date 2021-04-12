// Original bug: KT-41670

interface CommonListener<Left, Right, Data> {

        // We have default implementation for this method
	fun onCellClicked(left: Left, right: Right, data: Data?) {
		onCellClicked(data)
	}

	fun onCellClicked(data: Data?)

}

abstract class AbstractCell<Left, Right, Data>(
	private val left: Left,
	private val right: Right,
	private val data: Data?,
	private val clickListener: CommonListener<Left, Right, Data>?
) {
	
	fun beginCrash() {
		clickListener?.onCellClicked(left, right, data)
	}

}
