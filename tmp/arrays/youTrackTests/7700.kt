// Original bug: KT-26160

#!/usr/bin/env kscript


fun <T> T.executeRequest(): T {
  return this
}


object AClient {

  private val aService: IAService
    get() {
     TODO()
    }

  fun getServiceBuildDef(): Any {
    return aService.getServiceBuildDef().executeRequest()
  }

  interface IAService {
    fun getServiceBuildDef(): Any
  }

}


