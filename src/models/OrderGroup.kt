package models

import enums.PaymentType
import enums.SplitTypes

open class OrderGroup {
    protected var noOfCustomers = 0
    protected val orderedItems:HashMap<Int,Int> = hashMapOf()// stores (ItemId,Quantity)
    protected val bill:Bill = Bill()
    protected var paymentType:Int = PaymentType.BY_CASH.paymentTye //default value is by cash
    protected val transactionsBatch:ArrayList<Transaction> = ArrayList()
    protected var tabAmount:Double = 0.0
    protected var discount:Float = 0f
    protected var isDiscountPercentage = false

    fun addTransaction(transaction: Transaction){
        this.transactionsBatch.add(transaction)
    }

    fun setTabAmountValue(value:Double){
        this.tabAmount = value
    }


    fun addItemToOrder(itemId:Int,quantity:Int){
        orderedItems[itemId] = quantity
    }


    open fun processOrder(){
        //each sub type process order accordingly
    }

    fun setDiscount(value: Float,isDiscountPercentage:Boolean){
        this.discount = value
        this.isDiscountPercentage = isDiscountPercentage
    }

    open fun executeTransactions(){
        for (transaction in transactionsBatch){
            transaction.printTransaction()
        }
    }

    fun printInvoice(groupNo:Int){
        bill.printInvoice(groupNo)
    }

    open fun payment(){

    }

    fun setPaymentTypeValue(value:Int){
        this.paymentType = value
    }

    fun setCustomersNumber(value:Int){
        this.noOfCustomers = value
    }

}