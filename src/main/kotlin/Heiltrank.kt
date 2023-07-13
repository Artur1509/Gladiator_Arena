class Heiltrank(name: String, price: Int, value: Int) : Item(name, price, value) {

    override var isPotion = true

    // Funktion zur Heilung bei Einnahme
    override fun equipItem(player: Player){
        player.health += value

        if(player.health > player.endurance * 10){
            player.health = player.endurance * 10
            player.inventory.remove(this)
        }
        else{
            player.inventory.remove(this)
        }

        println("Du hast ${this.value} HP regeneriert.")

    }
}