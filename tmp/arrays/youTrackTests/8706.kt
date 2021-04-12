// Original bug: KT-18818

fun findUserId(username: String): Long? = null

fun main(args: Array<String>) {
	val userId = findUserId("abcd")
	
	when (userId) {
		null -> println("User not found")
		else -> println("User ID: $userId")
	}
	
	if (userId == null) {
		println("User not found")
	} else {
		println("User ID: $userId")
	}
}
