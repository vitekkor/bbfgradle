// Original bug: KT-26160

object wrapper {
  fun String.executeRequest(): String {
    return this
  }

  object AClient {


    fun getServiceBuildDef(): Any {
      return "".executeRequest()
    }


  }
}
