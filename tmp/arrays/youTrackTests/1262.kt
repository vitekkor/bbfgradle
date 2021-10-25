// Original bug: KT-22142

class Person(email: String) {

var email: String = email
    set(value) {
        require(value.isNotBlank(), { "The email cannot be blank" })
        field = value
    }
}
