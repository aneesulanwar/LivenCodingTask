package models

import enums.PaymentType
import enums.SplitTypes

class OrderGroup {
    private var noOfUsers:Int = 0
    private var splitType:Int = SplitTypes.SPLIT_EQUALLY.split //default type
    private val orderedItems:HashMap<Int,Int> = hashMapOf()// stores (ItemId,Quantity)
    private val userOrders:HashMap<Int,MutableMap<Int,Int>> = hashMapOf() //(userid,(itemId,quantity)) stores items ordered by each user and item's quantity for individual split
    private val transactionsBatch:ArrayList<Transaction> = ArrayList()
    private var paymentChoices:HashMap<Int,Int> = hashMapOf() //payment choices of users

    fun addTransaction(transaction: Transaction){
        this.transactionsBatch.add(transaction)
    }

    fun setSplitType(split:Int){
        this.splitType = splitType
    }

    fun setPaymentType(userId:Int,paymentType:Int){
        paymentChoices[userId] = paymentType
    }

    fun addItemToOrder(itemId:Int,quantity:Int){
        orderedItems[itemId] = quantity
    }

    fun addUserItem(itemId: Int,userId: Int,quantity: Int){
        if (userOrders[userId]!=null){
            userOrders[userId]?.set(itemId, quantity)
        }else{
            userOrders[userId] = mutableMapOf()
            userOrders[userId]?.set(itemId,quantity)
        }
    }

}