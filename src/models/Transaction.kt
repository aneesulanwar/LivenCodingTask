package models

import data.DataProvider
import enums.PaymentType
import repositories.ItemPriceRepositories
import java.util.concurrent.atomic.AtomicInteger

class Transaction {
    val itemRepository = ItemPriceRepositories()

    companion object {
        private val count: AtomicInteger = AtomicInteger(1)
    }

    private val id = count.getAndIncrement()
    private var bill:Bill? = null

    fun setBill(bill: Bill) {
        this.bill = bill
    }

    fun getBill():Bill{
        return this.bill?: Bill()
    }

    fun getId():Int{
        return this.id
    }

    fun printInvoice(){
        println("Invoice# \t $id")
        println("Payment Type \t ${if(bill?.getPaymentType()==PaymentType.BY_CASH.paymentTye)"BY CASH" else "BY CARD"}")
        println("Items")
        println("Item No \t\t Item ID \t\t Item Name \t\t Price \t\t Quantity")
        var itemNo = 1
        for (item in bill?.getItems()?: hashMapOf()){
            println("$itemNo \t\t ${item.key} \t\t ${itemRepository.getItemName(item.key)} \t\t $${itemRepository.getItemPrice(item.key)} \t\t ${item.value}")
            ++itemNo
        }
        println("Sub Total \t\t\t\t\t\t $${bill?.getSubTotalAmount()?:0.0}")
        println("Surcharge \t\t\t\t\t\t $${bill?.getSurcharge()}%")
        println("Discount \t\t\t\t\t\t $${if(bill?.getIsDisountPercentage() != false)bill?.getDiscount().toString() + '%' else bill?.getDiscount().toString()}")
        println("Total Amount \t\t\t\t\t\t $${bill?.getTotal()?:0.0}")
    }
}