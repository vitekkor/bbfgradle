// Original bug: KT-24384

private const val DEFAULT_PRICE = 0
class C {
	fun parse() {
		val first: String? = null // coming from third party/user IRL
		val second: String? = null // coming from third party/user IRL
		val outbound = getOutbound(first, second)
		val inbound = getInbound(first, second)
	}

	private fun getOutbound(first: String?, second: String?) = when {
		isOutbound(first) -> getPrice(first!!)
		isOutbound(second) -> getPrice(second!!)
		else -> DEFAULT_PRICE
	}

	private fun getInbound(first: String?, second: String?) = when {
		isInbound(first) -> getPrice(first!!)
		isInbound(second) -> getPrice(second!!)
		else -> DEFAULT_PRICE
	}

	private fun isOutbound(value: String?) = value != null // complex decision IRL
	private fun isInbound(value: String?) = value != null // complex decision IRL
	private fun getPrice(value: String) = value.toInt() // complex calculation IRL
}
