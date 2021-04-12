// Original bug: KT-22701

class Location {
  // void setClientData(Object)
  var clientData: Any? = null
  // Location setClientData(Object)
  open fun setClientData(clientData: Any?): Location {
    this.clientData = clientData
    return this
  }
}
