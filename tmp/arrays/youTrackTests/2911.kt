// Original bug: KT-40278

interface CompanyStateStatus {
    fun onSuccess()
    fun onFailure()
}

private suspend fun isLastSavedCompanyValid(companyStatus: CompanyStateStatus): Boolean {
    return true
}

suspend fun main() {
    if (!isLastSavedCompanyValid(object : CompanyStateStatus {
            override fun onSuccess() {
                TODO("Not yet implemented")
            }

            override fun onFailure() {
                TODO("Not yet implemented")
            }
        })) { // write body here
    } else {
        TODO("Not yet implemented")
    }
}
