// Original bug: KT-22906

package kt1230

import javax.swing.SwingUtilities

private class C {
    private fun startTemplate() {
        val y = object  {
            fun foo() {
                SwingUtilities.invokeLater(this::bar)
            }

            private fun bar() {
            }
        }
    }
}
