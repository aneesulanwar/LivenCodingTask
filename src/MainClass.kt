import enums.FoodItems
import enums.PaymentType
import models.Bill
import models.Transaction
import models.ordergrouptypes.OrderGroupCombined
import models.ordergrouptypes.OrderGroupSplitEqually
import models.ordergrouptypes.OrderGroupSplitIndividually


val transactionsBatch:ArrayList<Transaction> = ArrayList()

fun main() {

    val group1 = OrderGroupSplitIndividually()
    val group2 = OrderGroupCombined()
    val group3 = OrderGroupSplitEqually()
    setUpGroup1(group1)
    setUpGroup2(group2)
    setUpGroup3(group3)
    println("\n\n \t\t\t\t --------Group 1 Invoice---------")
    group1.printInvoice(1)
    println("\n\n \t\t\t\t --------Group 1 Transactions---------")
    group1.executeTransactions()
    println("\n\n \t\t\t\t --------Group 2 Invoice---------")
    group2.printInvoice(3)
    println("\n\n \t\t\t\t --------Group 2 Transactions---------")
    group2.executeTransactions()
    println("\n\n \t\t\t\t --------Group 3 Invoice---------")
    group3.printInvoice(3)
    println("\n\n \t\t\t\t --------Group 3 Transactions---------")
    group3.executeTransactions()
}

fun setUpGroup1(groupSplitIndividually: OrderGroupSplitIndividually){

    groupSplitIndividually.addUserItem(FoodItems.BIG_BREKKIE.itemId,1,1)
    groupSplitIndividually.addUserItem(FoodItems.BIG_BREKKIE.itemId,2,1)
    groupSplitIndividually.addUserItem(FoodItems.BRUSCHETTA.itemId,3,1)
    groupSplitIndividually.addUserItem(FoodItems.POACHED_EGGS.itemId,3,1)
    groupSplitIndividually.addUserItem(FoodItems.COFFEE.itemId,2,1)
    groupSplitIndividually.addUserItem(FoodItems.TEA.itemId,1,1)
    groupSplitIndividually.addUserItem(FoodItems.SODA.itemId,3,1)
    groupSplitIndividually.setCustomersNumber(3)
    groupSplitIndividually.setPaymentTypeValue(PaymentType.BY_CASH.paymentTye)
    groupSplitIndividually.processOrder()

}

fun setUpGroup2(groupCombined: OrderGroupCombined){
    groupCombined.addItemToOrder(FoodItems.TEA.itemId,1)
    groupCombined.addItemToOrder(FoodItems.COFFEE.itemId,3)
    groupCombined.addItemToOrder(FoodItems.SODA.itemId,1)
    groupCombined.addItemToOrder(FoodItems.BIG_BREKKIE.itemId,3)
    groupCombined.addItemToOrder(FoodItems.POACHED_EGGS.itemId,1)
    groupCombined.addItemToOrder(FoodItems.GARDEN_SALAD.itemId,1)
    groupCombined.setCustomersNumber(5)
    groupCombined.setPaymentTypeValue(PaymentType.BY_CARD.paymentTye)
    groupCombined.setDiscount(10f,true)
    groupCombined.processOrder()
}

fun setUpGroup3(groupSplitEqually: OrderGroupSplitEqually){
    groupSplitEqually.addItemToOrder(FoodItems.TEA.itemId,2)
    groupSplitEqually.addItemToOrder(FoodItems.TEA.itemId,3)
    groupSplitEqually.addItemToOrder(FoodItems.TEA.itemId,2)
    groupSplitEqually.addItemToOrder(FoodItems.BRUSCHETTA.itemId,5)
    groupSplitEqually.addItemToOrder(FoodItems.BIG_BREKKIE.itemId,5)
    groupSplitEqually.addItemToOrder(FoodItems.POACHED_EGGS.itemId,2)
    groupSplitEqually.addItemToOrder(FoodItems.GARDEN_SALAD.itemId,3)
    groupSplitEqually.setCustomersNumber(7)
    groupSplitEqually.setPaymentTypeValue(PaymentType.BY_CASH.paymentTye)
    groupSplitEqually.setTabAmountValue(50.0)
    groupSplitEqually.setDiscount(25f,false)
    groupSplitEqually.processOrder()


}