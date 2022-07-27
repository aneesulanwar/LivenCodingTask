package models

import enums.PaymentType
import repositories.ItemPriceRepositories
import java.util.concurrent.atomic.AtomicInteger

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
    private var tab:Double = 0.0
    companion object{
        const val CARD_SURCHARGE = 1.2f
        private val count: AtomicInteger = AtomicInteger(1)
    }
    private val invoiceNumber = Bill.count.getAndIncrement()

    fun setTab(value: Double){
        this.tab = value
    }

    fun addItem(itemId: Int,quantity:Int){
        if(items[itemId]!=null){
            val tempQuantity = items[itemId]?:0
            items[itemId] = tempQuantity+quantity
        }else{
            items[itemId] = quantity
        }
    }

    fun printInvoice(groupNo:Int){
        println("Group No \t $groupNo")
        println("Invoice# \t $invoiceNumber")
        println("Payment Type \t ${if(getPaymentType()==PaymentType.BY_CASH.paymentTye)"BY CASH" else "BY CARD"}")
        println("Items")
        println("Item No \t\t Item ID \t\t Item Name \t\t Price \t\t Quantity")
        var itemNo = 1
        for (item in items){
            println("$itemNo \t\t ${item.key} \t\t ${itemRepository.getItemName(item.key)} \t\t $${itemRepository.getItemPrice(item.key)} \t\t ${item.value}")
            ++itemNo
        }
        println("Sub Total \t\t\t\t\t\t $${getSubTotalAmount()}")
        println("Surcharge \t\t\t\t\t\t $${getSurcharge()}%")
        println("Discount \t\t\t\t\t\t $${if(isDiscountPercentage) "$discount%" else discount.toString()}")
        println("Tab Amount \t\t\t\t\t\t $$tab")
        println("Total Amount \t\t\t\t\t\t $${getTotal()}")

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

    private fun setSurCharge(value: Float){
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

    private fun getSubTotalAmount():Double{
        subTotal = 0.0
        for (item in items){
            subTotal += (itemRepository.getItemPrice(item.key)*item.value) //item multiply with quantity
        }
        return subTotal
    }

    fun getTotal():Double{
        total = getSubTotalAmount()
        applyTab()
        applySurcharge()
        applyDiscount()
        return total
    }

    private fun applyTab(){
        total -= this.tab
    }

    private fun applyDiscount(){
        if(!isDiscountPercentage){
            total -= discount
        }else{
            total -= (total/100)*discount
        }
    }

    private fun applySurcharge(){
        total += (total/100)*surCharge
    }

    fun getIsDisountPercentage():Boolean{
        return isDiscountPercentage
    }

    fun getTab():Double{
        return this.tab
    }

    fun calculateTax(){
        //no-info about the tax is provided in the document
    }
}