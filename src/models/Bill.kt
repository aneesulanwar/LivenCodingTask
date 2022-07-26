package models

import enums.PaymentType
import repositories.ItemPriceRepositories

class Bill {
    private val items:HashMap<Int,Int> = hashMapOf()
    private var discount:Float = 0f
    private var surCharge:Float = 0f
    private var paymentType:Int = PaymentType.BY_CASH.paymentTye //Default payment Type
    private val itemRepository = ItemPriceRepositories()
    private var subTotal:Double = 0.0
    private var total:Double = 0.0
    private var tax:Double = 0.0
    private var isDiscountPercentage = false
    companion object{
        const val CARD_SURCHARGE = 1.2f
    }

    fun addItem(itemId: Int,quantity:Int){
        items[itemId] = quantity
    }


    fun getItems():HashMap<Int,Int>{
        return this.items
    }

    fun setDiscount(value: Float,isDiscountPerc:Boolean){
        this.discount = value
        this.isDiscountPercentage = isDiscountPerc
    }

    fun setPayment(value:Int){
        this.paymentType = value
        if (value == PaymentType.BY_CARD.paymentTye){
            setSurCharge(CARD_SURCHARGE)
        }
    }

    fun setSurCharge(value: Float){
        this.surCharge = value
    }

    fun getPaymentType():Int{
        return this.paymentType
    }

    fun getDiscount():Float{
        return this.discount
    }

    fun getSurcharge():Float{
        return this.surCharge
    }

    fun getSubTotalAmount():Double{
        for (item in items){
            subTotal += (itemRepository.getItemPrice(item.key)*item.value) //item multiply with quantity
        }
        return subTotal
    }

    fun getTotal():Double{
        total = subTotal
        applySurcharge()
        applyDiscount()
        return total
    }

    fun applyDiscount(){
        if(!isDiscountPercentage){
            total -= discount
        }else{
            total -= (total/100)*discount
        }
    }

    fun applySurcharge(){
        total+= (total/100)*surCharge
    }

    fun getIsDisountPercentage():Boolean{
        return isDiscountPercentage
    }

    fun calculateTax(){
        //no-info about the tax is provided in the document
    }

    fun generateInvoices(){

    }
}