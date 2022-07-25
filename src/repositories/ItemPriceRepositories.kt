package repositories

import data.DataProvider

class ItemPriceRepositories {

    fun getItemPrice(id:Int):Double {
        return DataProvider.getDataProvider()[id]?.second?:0.0
    }

    fun getItemName(id: Int):String{
        return DataProvider.getDataProvider()[id]?.first?.getName()?:""
    }
}