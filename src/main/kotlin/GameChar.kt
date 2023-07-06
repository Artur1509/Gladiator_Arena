// Computergegner Klasse & Hauptklasse von Spieler
open class GameChar(var name: String, var endurance: Int, var strength: Int) {

    // Attribute
    var health = this.endurance * 10
    var damage = 1..this.strength
    open var attackList = mutableListOf<String>("hieb", "schnitt", "stich")

    // Computer Zug
    open fun turn(): String{
        return attackList.random()
    }
}