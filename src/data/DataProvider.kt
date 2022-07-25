package data

import enums.FoodItems
import models.Item

class DataProvider {
    companion object {
        fun getDataProvider():HashMap<Int,Pair<Item,Double>>{
            var dataProvider = hashMapOf<Int, Pair<Item, Double>>()//map to store (items,price) pair with the item id as key
            dataProvider[FoodItems.BIG_BREKKIE.foodId] = Pair(Item(FoodItems.BIG_BREKKIE.foodId,"Big Brekkie"),16.0)
            dataProvider[FoodItems.BRUSCHETTA.foodId] = Pair(Item(FoodItems.BIG_BREKKIE.foodId,"Bruschetta"),8.0)
            dataProvider[FoodItems.POACHED_EGGS.foodId] = Pair(Item(FoodItems.BIG_BREKKIE.foodId,"Poached Eggs"),12.0)
            dataProvider[FoodItems.COFFEE.foodId] = Pair(Item(FoodItems.BIG_BREKKIE.foodId,"Coffee"),5.0)
            dataProvider[FoodItems.TEA.foodId] = Pair(Item(FoodItems.BIG_BREKKIE.foodId,"Tea"),3.0)
            dataProvider[FoodItems.SODA.foodId] = Pair(Item(FoodItems.BIG_BREKKIE.foodId,"Soda"),4.0)
            dataProvider[FoodItems.GARDEN_SALAD .foodId] = Pair(Item(FoodItems.GARDEN_SALAD.foodId,"Garden Salad"),10.0)
            return dataProvider
        }
    }
}