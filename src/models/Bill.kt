package models

import enums.PaymentType
import repositories.ItemPriceRepositories

class Bill {
    private val items:ArrayList<Item> = ArrayList()
    private var discount:Float = 0f
    private var surCharge:Float = 0f
    private var paymentType:Int = PaymentType.BY_CASH.paymentTye //Default payment Type
    val itemRepository = ItemPriceRepositories()
    var total:Double = 0.0
    var tax:Double = 0.0
    var isDiscountPercentage = false
    companion object{
        const val CARD_SURCHARGE = 1.2f
    }

    fun addItem(item: Item){
        items.add(item)
    }

    fun addItems(items: List<Item>){
        this.items.addAll(items)
    }

    fun getItems():ArrayList<Item>{
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

    fun getTotalAmount():Double{
        for (item in items){
            total += itemRepository.getItemPrice(item.getId())
        }
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
        total+= total*surCharge
    }

    fun calculateTax(){
        //no-info about the tax is provided in the document
    }
}