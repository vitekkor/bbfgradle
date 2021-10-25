// Original bug: KT-19640

import javax.swing.tree.DefaultMutableTreeNode

class TestJavaExpectedTypeInference {
    fun test(node: DefaultMutableTreeNode) {
        val e = node.children() // (*)
        while (e.hasMoreElements()) {
            // ...
        }
    }
}
