// Original bug: KT-21447

import javax.xml.stream.XMLInputFactory

class Something {
  val factory: XMLInputFactory = XMLInputFactory.newFactory()   // << Unresolved reference: newFactory
}
