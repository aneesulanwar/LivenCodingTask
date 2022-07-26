package data

import enums.FoodItems
import models.Item

class DataProvider {
    companion object {
        fun getDataProvider():HashMap<Int,Pair<Item,Double>>{
            var dataProvider = hashMapOf<Int, Pair<Item, Double>>()//map to store (items,price) pair with the item id as key
            dataProvider[FoodItems.BIG_BREKKIE.itemId] = Pair(Item(FoodItems.BIG_BREKKIE.itemId,"Big Brekkie"),16.0)
            dataProvider[FoodItems.BRUSCHETTA.itemId] = Pair(Item(FoodItems.BIG_BREKKIE.itemId,"Bruschetta"),8.0)
            dataProvider[FoodItems.POACHED_EGGS.itemId] = Pair(Item(FoodItems.BIG_BREKKIE.itemId,"Poached Eggs"),12.0)
            dataProvider[FoodItems.COFFEE.itemId] = Pair(Item(FoodItems.BIG_BREKKIE.itemId,"Coffee"),5.0)
            dataProvider[FoodItems.TEA.itemId] = Pair(Item(FoodItems.BIG_BREKKIE.itemId,"Tea"),3.0)
            dataProvider[FoodItems.SODA.itemId] = Pair(Item(FoodItems.BIG_BREKKIE.itemId,"Soda"),4.0)
            dataProvider[FoodItems.GARDEN_SALAD.itemId] = Pair(Item(FoodItems.GARDEN_SALAD.itemId,"Garden Salad"),10.0)
            return dataProvider
        }
    }
}