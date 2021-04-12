// Original bug: KT-14565

const val DISCIPLINE_AGILITY = 1
const val PROGRAMME_NONE = 0

enum class PointsMethod { FAULTS }

enum class ClassTemplate(
        // var bug: Int = 1,

        val parent: ClassTemplate? = null,
        val previous: ClassTemplate? = null,
        val progressionEquivalent: ClassTemplate? = null,
        var code: Int,
        var nameTemplate: String = "",
        var idDiscipline: Int = DISCIPLINE_AGILITY,
        var strictRunningOrder: Boolean = false,
        var pointsMethod: PointsMethod = PointsMethod.FAULTS,

        var noTimeFaults: Boolean = false,
        var combineHeights: Boolean = false,

        var column: Int = 1,
        var runningOrderSort: String = "rand()",
        var programme: Int = PROGRAMME_NONE,
        var eliminationTime: Int = 0,
        var courseTimeCode: String = "",
        var teamSize: Int = 1,
        var sponsor: String = "",
        var lateEntryCredits: Int = 1,
        var lateEntryFee: Int = 0,

        var courseLengthNeeded: Boolean = true,

        var discretionaryCourseTime: Boolean = false,
        var isRelay: Boolean = false,
        var isQualifier: Boolean = false,
        var generateChildren: Boolean = false,
        var feedFromParent: Boolean = false,

        var isNfcAllowed: Boolean = false,
        var isAddOnAllowed: Boolean = false,
        var isSpecialEntry: Boolean = false,
        var isUkaProgression: Boolean = false,
        var canEnterDirectly: Boolean = true,
        var isPointRanked: Boolean = false,
        var isPointRankedDesc: Boolean = false
) {
    UNDEFINED(code = 999999, nameTemplate = "Undefined"),
    BLAH(code = 999999, nameTemplate = "Undefined")
}
