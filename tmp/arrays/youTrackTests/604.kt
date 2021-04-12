// Original bug: KT-41670

interface CommonListener<Left, Right, Data> {

	fun onCellClicked(left: Left, right: Right, data: Data?) {
		onCellClicked(data)
	}

	fun onCellClicked(left: Left, right: Right) {
		onCellClicked(left, right, null)
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


// Expecting, that we need to override only one single method -> `fun onCellClicked(data: Data?)`
fun interface CellImplListener<Data> : CommonListener<String, String, Data> 

class CellImpl<Data>(
	left: String,
	right: String,
	data: Data? = null,
	listener: CellImplListener<Data>? = null
): AbstractCell<String, String, Data>(
	left = left,
	right = right,
	data = data,
	clickListener = listener
)


fun main() {
	println("Main function")
	val myCell = CellImpl<Int>(
		left = "cell-left",
		right = "cell-right",
		data = 1,
		listener = getListener()
	)
	myCell.beginCrash()
}

private fun getListener() = CellImplListener<Int> { println("Listener! $it") }













