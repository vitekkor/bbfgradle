// Original bug: KT-31276

inline  class Texture(val id: Int) {
	fun generate() {
		checkBound()
	}
	companion object {
		private inline fun Texture.checkBound(){
			check(boundTexture == this)
		}
		private var boundTexture: Texture? = null
	}
}
