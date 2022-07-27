package models.ordergrouptypes

import models.OrderGroup
import models.Transaction

class OrderGroupCombined: OrderGroup() {

    override fun processOrder() {

        for (item in orderedItems){
            bill.addItem(item.key,item.value)
        }
        bill.setPayment(paymentType)
        bill.setDiscount(this.discount,this.isDiscountPercentage)
        bill.setTab(this.tabAmount)

        payment(bill.getTotal())//// for ease calling payment internally instead of main
    }

    fun payment(amount:Double){
        if (amount>=bill.getTotal()){
            val transaction = Transaction()
            transaction.setPaidAmount(amount)
            transaction.setReturnedAmount(amount - bill.getTotal())
            transactionsBatch.add(transaction)//adding transaction to batch
        }
    }

}