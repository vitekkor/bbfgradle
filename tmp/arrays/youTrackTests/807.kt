// Original bug: KT-44562

object KotlinTest {
	
	private val SHIFTS = IntArray(32)
	
	@JvmStatic
	fun main(args: Array<String>) {
		var offset = 2
		for (i in 0..31) {
			SHIFTS[i] = offset - 1
			offset += offset
		}
		
		val rig = 1
		val aug = 2
		val pre = 3
		
		var v = 0
		
		v = doSet(v, rig, rig, 1)
		println("Rig $v")
		v = doSet(v, aug, aug, 1)
		println("Aug $v")
		v = doSet(v, pre, pre, 1)
		println("Pre $v")
	}
	
	fun doSet(varpValue: Int, least: Int, most: Int, value: Int): Int {
		var shift: Int = SHIFTS[most - least]
		var value = value
		if (value < 0 || value > shift) {
			value = 0
		}
		shift = shift shl least
		return ((varpValue and (shift.inv())) or value shl least and shift)
	}
}
