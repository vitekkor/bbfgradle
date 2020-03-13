//File Main.kt
// IGNORE_BACKEND_FIR: JVM_IR
// TARGET_BACKEND: JVM

// FULL_JDK
// WITH_RUNTIME

//This front-end problem test added to box ones only cause of FULL_JDK support
import org.w3c.dom.Element

fun box() : String {
    val touch = MyElement::class.java
    return "OK"
}



//File MyElement.java
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

public final class MyElement implements Element {
   // $FF: synthetic field
   private final Element $$delegate_0;

   @NotNull
   public final String bar() {
      return "OK";
   }

   public MyElement(@NotNull Element e) {
      super();
      this.$$delegate_0 = e;
   }

   public Node appendChild(Node p0) {
      return this.$$delegate_0.appendChild(p0);
   }

   public Node cloneNode(boolean p0) {
      return this.$$delegate_0.cloneNode(p0);
   }

   public short compareDocumentPosition(Node p0) {
      return this.$$delegate_0.compareDocumentPosition(p0);
   }

   public String getAttribute(String p0) {
      return this.$$delegate_0.getAttribute(p0);
   }

   public String getAttributeNS(String p0, String p1) {
      return this.$$delegate_0.getAttributeNS(p0, p1);
   }

   public Attr getAttributeNode(String p0) {
      return this.$$delegate_0.getAttributeNode(p0);
   }

   public Attr getAttributeNodeNS(String p0, String p1) {
      return this.$$delegate_0.getAttributeNodeNS(p0, p1);
   }

   public NamedNodeMap getAttributes() {
      return this.$$delegate_0.getAttributes();
   }

   public String getBaseURI() {
      return this.$$delegate_0.getBaseURI();
   }

   public NodeList getChildNodes() {
      return this.$$delegate_0.getChildNodes();
   }

   public NodeList getElementsByTagName(String p0) {
      return this.$$delegate_0.getElementsByTagName(p0);
   }

   public NodeList getElementsByTagNameNS(String p0, String p1) {
      return this.$$delegate_0.getElementsByTagNameNS(p0, p1);
   }

   public Object getFeature(String p0, String p1) {
      return this.$$delegate_0.getFeature(p0, p1);
   }

   public Node getFirstChild() {
      return this.$$delegate_0.getFirstChild();
   }

   public Node getLastChild() {
      return this.$$delegate_0.getLastChild();
   }

   public String getLocalName() {
      return this.$$delegate_0.getLocalName();
   }

   public String getNamespaceURI() {
      return this.$$delegate_0.getNamespaceURI();
   }

   public Node getNextSibling() {
      return this.$$delegate_0.getNextSibling();
   }

   public String getNodeName() {
      return this.$$delegate_0.getNodeName();
   }

   public short getNodeType() {
      return this.$$delegate_0.getNodeType();
   }

   public String getNodeValue() {
      return this.$$delegate_0.getNodeValue();
   }

   public Document getOwnerDocument() {
      return this.$$delegate_0.getOwnerDocument();
   }

   public Node getParentNode() {
      return this.$$delegate_0.getParentNode();
   }

   public String getPrefix() {
      return this.$$delegate_0.getPrefix();
   }

   public Node getPreviousSibling() {
      return this.$$delegate_0.getPreviousSibling();
   }

   public TypeInfo getSchemaTypeInfo() {
      return this.$$delegate_0.getSchemaTypeInfo();
   }

   public String getTagName() {
      return this.$$delegate_0.getTagName();
   }

   public String getTextContent() {
      return this.$$delegate_0.getTextContent();
   }

   public Object getUserData(String p0) {
      return this.$$delegate_0.getUserData(p0);
   }

   public boolean hasAttribute(String p0) {
      return this.$$delegate_0.hasAttribute(p0);
   }

   public boolean hasAttributeNS(String p0, String p1) {
      return this.$$delegate_0.hasAttributeNS(p0, p1);
   }

   public boolean hasAttributes() {
      return this.$$delegate_0.hasAttributes();
   }

   public boolean hasChildNodes() {
      return this.$$delegate_0.hasChildNodes();
   }

   public Node insertBefore(Node p0, Node p1) {
      return this.$$delegate_0.insertBefore(p0, p1);
   }

   public boolean isDefaultNamespace(String p0) {
      return this.$$delegate_0.isDefaultNamespace(p0);
   }

   public boolean isEqualNode(Node p0) {
      return this.$$delegate_0.isEqualNode(p0);
   }

   public boolean isSameNode(Node p0) {
      return this.$$delegate_0.isSameNode(p0);
   }

   public boolean isSupported(String p0, String p1) {
      return this.$$delegate_0.isSupported(p0, p1);
   }

   public String lookupNamespaceURI(String p0) {
      return this.$$delegate_0.lookupNamespaceURI(p0);
   }

   public String lookupPrefix(String p0) {
      return this.$$delegate_0.lookupPrefix(p0);
   }

   public void normalize() {
      this.$$delegate_0.normalize();
   }

   public void removeAttribute(String p0) {
      this.$$delegate_0.removeAttribute(p0);
   }

   public void removeAttributeNS(String p0, String p1) {
      this.$$delegate_0.removeAttributeNS(p0, p1);
   }

   public Attr removeAttributeNode(Attr p0) {
      return this.$$delegate_0.removeAttributeNode(p0);
   }

   public Node removeChild(Node p0) {
      return this.$$delegate_0.removeChild(p0);
   }

   public Node replaceChild(Node p0, Node p1) {
      return this.$$delegate_0.replaceChild(p0, p1);
   }

   public void setAttribute(String p0, String p1) {
      this.$$delegate_0.setAttribute(p0, p1);
   }

   public void setAttributeNS(String p0, String p1, String p2) {
      this.$$delegate_0.setAttributeNS(p0, p1, p2);
   }

   public Attr setAttributeNode(Attr p0) {
      return this.$$delegate_0.setAttributeNode(p0);
   }

   public Attr setAttributeNodeNS(Attr p0) {
      return this.$$delegate_0.setAttributeNodeNS(p0);
   }

   public void setIdAttribute(String p0, boolean p1) {
      this.$$delegate_0.setIdAttribute(p0, p1);
   }

   public void setIdAttributeNS(String p0, String p1, boolean p2) {
      this.$$delegate_0.setIdAttributeNS(p0, p1, p2);
   }

   public void setIdAttributeNode(Attr p0, boolean p1) {
      this.$$delegate_0.setIdAttributeNode(p0, p1);
   }

   public void setNodeValue(String p0) {
      this.$$delegate_0.setNodeValue(p0);
   }

   public void setPrefix(String p0) {
      this.$$delegate_0.setPrefix(p0);
   }

   public void setTextContent(String p0) {
      this.$$delegate_0.setTextContent(p0);
   }

   public Object setUserData(String p0, Object p1, UserDataHandler p2) {
      return this.$$delegate_0.setUserData(p0, p1, p2);
   }
}
