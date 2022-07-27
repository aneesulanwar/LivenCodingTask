package models.ordergrouptypes

import enums.PaymentType
import models.OrderGroup
import models.Transaction

class OrderGroupSplitIndividually: OrderGroup() {
    private val customersOrders:HashMap<Int,MutableMap<Int,Int>> = hashMapOf() //(userid,(itemId,quantity)) stores items ordered by each user and item's quantity for individual split
    private val payableAmountByEach:HashMap<Int,Double> = hashMapOf() // saves user id and payable amount e.g(1.100)

    companion object{
        const val GROUP_SURCHARGE = 1.2f
    }

    override fun processOrder() {
        for (customer in customersOrders){
            var amount = 0.0
            for (item in customer.value){
                bill.addItem(item.key,item.value)
                amount += Transaction.itemRepository.getItemPrice(item.key)*item.value
            }

            var payableAmount = applyDiscount(amount)
            payableAmount = applySurcharge(amount)
            payableAmountByEach[customer.key]=amount
            payment(customer.key,payableAmount)// for ease calling payment internally instead of main
        }
        bill.setPayment(paymentType)
        bill.setDiscount(this.discount,this.isDiscountPercentage)
        bill.setTab(this.tabAmount)
    }

    private fun payment(userId: Int, amount:Double){
        if (amount >= payableAmountByEach[userId]?:0.0){
            val transaction = Transaction()
            transaction.setPaidAmount(amount)
            transaction.setReturnedAmount(amount - payableAmountByEach[userId]!!)
            transactionsBatch.add(transaction)//adding transaction to batch
        }
    }

    fun addUserItem(itemId: Int,userId: Int,quantity: Int){
        if (customersOrders[userId]!=null){
            customersOrders[userId]?.set(itemId, quantity)
        }else{
            customersOrders[userId] = mutableMapOf()
            customersOrders[userId]?.set(itemId,quantity)
        }
    }

    private fun applyDiscount(amount: Double):Double{
        var tempAmount = amount
        tempAmount -= (amount/100)*this.discount
        return tempAmount
    }

    private fun applySurcharge(amount: Double):Double{
        var tempAmount = amount
        if(this.paymentType == PaymentType.BY_CARD.paymentTye){
            tempAmount += (tempAmount/100)* GROUP_SURCHARGE
        }

        return tempAmount
    }
}