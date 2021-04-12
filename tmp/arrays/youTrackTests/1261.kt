// Original bug: KT-22142

class Person {

constructor(email: String) {
    this.email = email
}

var email: String
    set(value) {
        require(value.isNotBlank(), { "The email cannot be blank" })
        field = value
    }
}
