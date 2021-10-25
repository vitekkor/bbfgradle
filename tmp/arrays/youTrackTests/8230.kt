// Original bug: KT-22105

private fun test(chars: CharSequence) {
	val lines = hashMapOf<Int, ArrayList<CharSequence>>()
	val string: String? = chars as? String
	if (string != null) {
		lines[1] = arrayListOf(chars) // works on 1.1.60, error on 1.2.10
		lines[1] = arrayListOf(chars as CharSequence) // works on both
	}
}
