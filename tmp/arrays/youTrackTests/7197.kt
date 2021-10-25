// Original bug: KT-23402

package sandbox.kotlin

import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtScript
import org.jetbrains.kotlin.psi.psiUtil.getChildOfType

class TestBug {
    fun test(psiFile: KtFile): KtScript? {
        return psiFile.getChildOfType<KtScript>()
    }
}
