package models

import data.DataProvider
import enums.PaymentType
import repositories.ItemPriceRepositories
import java.util.concurrent.atomic.AtomicInteger

class Transaction {

    companion object {
        private val count: AtomicInteger = AtomicInteger(1)
        val itemRepository = ItemPriceRepositories()
    }

    private val id = count.getAndIncrement()
    private var paidAmount = 0.0
    private var returnedAmount = 0.0
    private var remainingAmount = 0.0

    fun getId():Int{
        return this.id
    }

    fun printTransaction(){
        println("Transaction# \t $id \t\t Amount Paid \t $$paidAmount \t\t Amount Returned \t $$returnedAmount \t\t Amount Remaining \t $$remainingAmount")
    }

    fun setPaidAmount(value:Double){
        this.paidAmount = value
    }

    fun setReturnedAmount(value:Double){
        this.returnedAmount = value
        this.remainingAmount = paidAmount - returnedAmount
    }

    fun setRemainingAmount(value:Double){
        this.remainingAmount = value
    }

    fun getRemainingAmount():Double{
        return this.remainingAmount
    }

    fun getReturnedAmount():Double{
        return this.returnedAmount
    }

    fun getPaidAmount():Double{
        return this.paidAmount
    }
}