// Original bug: KT-44722

class Source {
    companion object {
        fun updateSomething(completion: (Result<String>) -> Unit) {
            completion(Result.success("Awesome"))
        }
    }
}

fun main() {
    Source.updateSomething { result ->
    	result.onSuccess { message -> 
            println(message)
        }
        result.onFailure { message ->
        	println(message)
        }
    }
}
