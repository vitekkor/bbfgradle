// Original bug: KT-27861

enum class AndroidGoogleSource(val path: String) {
	Platform("${Companion.Companion.url}/platform/frameworks/base"),
	GradlePlugin("${Companion.url}/platform/tools/base");

	private class Companion {
		companion object {
			const val url = "https://android.googlesource.com"
		}
	}
}
