// Original bug: KT-623

class TreeNode<T: TreeNode<T>>(val item: Int, val left: T?, val right: T?) {
 fun itemCheck() : Int {
   var res = item
   if (left != null)
     res += left.itemCheck()
   if (right != null)
     res -= right.itemCheck()
   return res
 }
}
