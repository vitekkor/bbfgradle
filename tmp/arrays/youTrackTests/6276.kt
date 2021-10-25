// Original bug: KT-30780

inline class Test(val x: Int){
	private companion object{
		private const val CONSTANT = 0
	}
	
	fun crash(){
		getInlineConstant()
	}
	
	private inline fun getInlineConstant(): Int{
		return CONSTANT
	}
}
