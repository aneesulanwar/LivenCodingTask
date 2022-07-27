package models.ordergrouptypes

import models.OrderGroup
import models.Transaction

class OrderGroupSplitEqually: OrderGroup() {

    var amountPayableByEach = 0.0

    override fun processOrder() {
        for (item in orderedItems){
            bill.addItem(item.key,item.value)
        }
        bill.setPayment(paymentType)
        bill.setDiscount(this.discount,this.isDiscountPercentage)
        bill.setTab(this.tabAmount)
        amountPayableByEach = bill.getTotal()/noOfCustomers
        for (i in 1..noOfCustomers){
            payment(amountPayableByEach)//// for ease calling payment internally instead of main
        }
    }

    fun payment(amount: Double){
        if(amount>=amountPayableByEach){
            val transaction = Transaction()
            transaction.setPaidAmount(amount)
            transaction.setReturnedAmount(amount - amountPayableByEach)
            transactionsBatch.add(transaction)//adding transaction to batch
        }
    }
}