// Computergegner Klasse & Hauptklasse von Spieler
open class GameChar(var name: String, var endurance: Int, var strength: Int) {

    // Gladiator Attribute
    var health: Int = this.endurance * 10
    var damage = 1..(this.strength)
    var attackList = mutableListOf<String>("hieb", "schnitt", "stich")

    // Computer Zug
    open fun turn(): String{
        return attackList.random()
    }

    // Computer Gegner Stats anzeigen
    open fun showStats(){
        println("""
            ====== Gladiator Stats ======
            Name: ${this.name}
            HP: ${this.health}
            DMG: ${this.damage.first()} - ${this.damage.last()}
            =============================
        """.trimIndent())
    }
}