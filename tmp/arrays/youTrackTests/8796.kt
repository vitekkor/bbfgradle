// Original bug: KT-18779

sealed class Result {

	class Failure(val exception: Exception) : Result()
	class Success : Result()
}


fun main(args: Array<String>) {
	var result: Result
	try {
		result = Result.Success()
	}
	catch (e: Exception) {
		result = Result.Failure(Exception())
	}

	if (result is Result.Failure) {
		println(result.exception)
	}
}
