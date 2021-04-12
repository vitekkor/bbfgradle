// Original bug: KT-19491

data class Version(val versionString: String)

fun toVersion(str: String?): Version? {
  return if (str != null) {
    Version(str)
  } else {
    null
  }
}
