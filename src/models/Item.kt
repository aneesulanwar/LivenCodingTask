package models

import kotlin.properties.Delegates

class Item(id: Int, name: String) {

    private var id:Int = id
    private var name:String = name

    fun getId():Int {
        return this.id
    }

    fun getName(): String{
        return this.name
    }
}