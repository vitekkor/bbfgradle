// Original bug: KT-37919

package org.intellij.plugins.markdown.ui.preview.javafx

import org.intellij.plugins.markdown.ui.preview.javafx.FileDocumentManagerTest

object Test {
  private fun showHeadersPopup1() {
    val headersPopup: A = object : A() {
      fun getTextFor(value: K): String {
        val document = FileDocumentManagerTest.instance.getDocument(
          value.containingFile.virtualFile)  // here
        return "hi"
      }
    }
  }

  internal open class A
}

internal class FileDocumentManagerTest {
  fun getDocument(k: K?): DocumentTest {
    return DocumentTest()
  }

  companion object {
    val instance: FileDocumentManagerTest
      get() = FileDocumentManagerTest()
  }
}

internal class DocumentTest
internal class K {
  val containingFile: K
    get() = K()

  val virtualFile: K
    get() = K()
}
