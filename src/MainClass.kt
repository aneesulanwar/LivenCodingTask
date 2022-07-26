import enums.FoodItems
import enums.PaymentType
import models.Bill
import models.Transaction


fun main() {
    val bill = Bill()
    bill.addItem(FoodItems.SODA.itemId,3)
    bill.addItem(FoodItems.TEA.itemId,2)
    bill.addItem(FoodItems.GARDEN_SALAD.itemId,5)
    bill.setDiscount(10f,true)
    val bill2 = Bill()
    bill2.addItem(FoodItems.COFFEE.itemId,1)
    bill2.addItem(FoodItems.POACHED_EGGS.itemId,4)
    bill2.addItem(FoodItems.BIG_BREKKIE.itemId,2)
    bill2.setPayment(PaymentType.BY_CARD.paymentTye)
    val transaction = Transaction()
    val transaction1 = Transaction()
    val transaction2 = Transaction()
    val transaction3 = Transaction()
    transaction.setBill(bill)
    transaction1.setBill(bill2)
    transaction.printInvoice()
    transaction1.printInvoice()
}