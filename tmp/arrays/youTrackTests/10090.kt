// Original bug: KT-3180

// In Java: interface XMLStreamReader extends XMLStreamConstants
val tmp1 = javax.xml.stream.XMLStreamConstants.START_ELEMENT // direct access to declaration place works good
val tmp2 = javax.xml.stream.XMLStreamReader.START_ELEMENT    // inherited constant - access fails
