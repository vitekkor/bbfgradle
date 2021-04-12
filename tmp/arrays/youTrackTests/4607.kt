// Original bug: KT-35860

enum class ROLES {
    TECHNICAL_PROGNOSIS,
    ESTIMATION,
    APPROVAL,
    PLAN,
    SPECIALIST,
    PRE_BID,
    CONTRACT,
    INVESTIGATION,
    SETTLEMENT,
    WARRANTY
}

object RoleManager {
    fun hasRoles(encoding: Int, vararg roles: ROLES): Boolean =
        roles.fold(encoding) { acc, element -> acc and (1 shl element.ordinal)} != 0
    fun encode(vararg roles: ROLES) =
        roles.fold(0) {acc, element -> acc and (1 shl element.ordinal)} != 0
}
