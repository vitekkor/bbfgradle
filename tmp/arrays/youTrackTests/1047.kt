// Original bug: KT-13108

data class Product(val id: Int)
data class User(val id: Int)
data class Issue(val id: Int)
data class Comment(val id: Int)

data class HasProduct(val product: Product)
data class HasUser(val user: User)
data class HasIssue(val issue: Issue)
data class HasComment(val comment: Comment)

object ProductRepository { fun get(id: Int) = Product(id) }
object UserRepository    { fun get(id: Int) = User(id)    }
object IssueRepository   { fun get(id: Int) = Issue(id)   }
object CommentRepository { fun get(id: Int) = Comment(id) }

// How many âprocessâ overloads do we need now?
