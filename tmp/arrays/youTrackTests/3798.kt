// Original bug: KT-37919

// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.intellij.plugins.markdown.ui.preview

object Test {
  private fun showHeadersPopup1() {
    val headersPopup = object : A() {

      fun getTextFor(value: K): String {
        val document = FileDocumentManagerTest.instance.getDocument(value.containingFile.virtualFile)
        return "hi"
      }
    }
  }

  internal open class A
}

internal class FileDocumentManagerTest {

  fun getDocument(k: K): DocumentTest {
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
