open class Item(var name: String, var price: Int, var value: Int) {

    // Item Arten
    open var isArmor = false
    open var isWeapon = false
    open var isPotion = false

    // Item Ausrüsten / Benutzen funktion
    open fun equipItem(player: Player){
        if(this.isArmor){
            if(player.armor != null){
                player.inventory.add(player.armor!!)
                player.health - player.armor!!.value
            }
            player.armor = this
            player.health += this.value
            player.inventory.remove(this)
        }
        if(this.isWeapon){
            if(player.weapon != null){
                player.inventory.add(player.weapon!!)
                player.damage = 1..player.strength
            }
            player.weapon = this
            player.damage = 1..(player.strength + this.value)
            player.inventory.remove(this)
        }
    }

    // Item kaufen Funktion
    fun buyItem(player: Player){
        if(player.gold >= this.price){
            player.inventory.add(this)
            player.gold -= this.price
        }
        else{
            println("Nicht genug Gold.")
        }
    }

    // Art des Attributes was bei Item erhöht wird, wenn man es anlegt (Sichtbar im Shop / Inventar)
    fun attributeBonus(): String{
        var bonusFor = ""

        if (this.isArmor){
            bonusFor = "HP"
        }
        if(this.isWeapon){
            bonusFor = "DMG"
        }
        if(this.isPotion){
            bonusFor = "HP Regeneration"
        }
        return bonusFor
    }
}
