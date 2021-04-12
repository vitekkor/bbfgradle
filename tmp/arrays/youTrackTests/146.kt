// Original bug: KT-42924

/**
 * You can edit, run, and share this code. 
 * play.kotlinlang.org 
 */

fun main() {
    println("Hello, world!!!")
    val refundRequestData = RefundRequestData().apply {
            this.amount = 123L
            this.currencyCode = "EUR"
            this.countryCode = "DE"
            this.merchantReference = "hallo"
            this.iban = "NL91ABNA0417164300"
            this.firstName="sander"
            this.surname = "bullet"
            this.contactDetailsEmailAddress ="pradeep.bhardwaj@epay.ingenico.com"
            this.companyInformationName ="hahaa"
        }
    
    val response = SDKRefund(refundRequestData).createRefundCustomer()!!
    
    println(response.address)
    println(response.companyInformation)
    println(response.contactDetails)
}

class SDKRefund(val refundRequestData:RefundRequestData){
    private val personalAddressList = listOfNotNull(this.refundRequestData.additionalInfo,
            this.refundRequestData.city, this.refundRequestData.countryCode,
            this.refundRequestData.houseNumber, this.refundRequestData.state,
            this.refundRequestData.stateCode, this.refundRequestData.street,
            this.refundRequestData.zip)
    private val companyInformationElements = listOfNotNull(refundRequestData.companyInformationName,
            refundRequestData.companyInformationVatNumber)
    private val contactDetailsElements = listOfNotNull(refundRequestData.contactDetailsEmailAddress,
            refundRequestData.contactDetailsEmailAddressType)
    
    fun createRefundCustomer(): RefundCustomer? {

        return RefundCustomer().also {

            when {
                personalAddressList.isNotEmpty() -> {it.address = "createRefundCustomerAddress()"}
                companyInformationElements.isNotEmpty() -> {it.companyInformation = "createRefundCompanyInformation()"}
                contactDetailsElements.isNotEmpty() -> {it.contactDetails = "createContactDetails()"}
                else -> println("i am in when :)")
            }
        }
    }
}

class RefundCustomer(){
    val hallo="i am good to go"
    var address:String?=null
    var companyInformation:String?=null
    var contactDetails:String?=null
}



data class RefundRequestData(
        var currencyCode: String? = null,
        var amount: Long? = null,
        var bBanAccountHolderName: String? = null,
        var accountNumber: String? = null,
        var bankCity: String? = null,
        var bankCode: String? = null,
        var bankName: String? = null,
        var branchCode: String? = null,
        var checkDigit: String? = null,
        var bbanCountryCode: String? = null,
        var patronymicName: String? = null,
        var swiftCode: String? = null,
        var bankAccountIbanAccountHolderName: String? = null,
        var iban: String? = null,
        var bankRefundSpecificCountryCode: String? = null,
        var additionalInfo: String? = null,
        var city: String? = null,
        var countryCode: String? = null,
        var houseNumber: String? = null,
        var state: String? = null,
        var stateCode: String? = null,
        var street: String? = null,
        var zip: String? = null,
        var firstName: String? = null,
        var surname: String? = null,
        var surnamePrefix: String? = null,
        var title: String? = null,
        var companyInformationName: String? = null,
        var companyInformationVatNumber: String? = null,
        var contactDetailsEmailAddress: String? = null,
        var contactDetailsEmailAddressType: String? = null,
        var fiscalNumber: String? = null,
        var refundDate: String? = null,
        var merchantReference: String? = null

)