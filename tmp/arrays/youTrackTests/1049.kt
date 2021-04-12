// Original bug: KT-13108

sealed class ContactPoint {
    sealed class Phone {
        class Eligible : EligibleContactPoint
        class Ineligible
    }

    sealed class Email {
        class Eligible : EligibleContactPoint
        class Ineligible
    }
}

// would be awesome if this could be a union type of `ContactPoint.Phone.Eligible` and `ContactPointPhone.Ineligible`
// certain pieces of my UI only accept `EligibleContactPoint`, and I would like to do an exhaustive `when` on them
interface EligibleContactPoint
