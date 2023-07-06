// Spieler Klasse
class Player(name: String, endurance: Int, strength: Int) : GameChar(name, endurance, strength) {

    // Skill Punkte die der Spieler bei einem Sieg bekommt.
    var skillPoints = 0

    // Spieler Zug
    override fun turn(): String{
        println("""Wähle eine Attacke:
            |1. Hieb
            |2. Schnitt
            |3. Stich
        """.trimMargin())

        val input = readln().toInt()
        return this.attackList[input - 1]
    }

    // Spieler Stats anzeigen
    override fun showStats(){
        println("""
            ====== Gladiator Stats ======
            Name: ${this.name}
            HP: ${this.health}
            DMG: ${this.damage.first()} - ${this.damage.last()}
            
            Attribute:
            Ausdauer: ${this.endurance}
            Stärke: ${this.strength}
            
            Skillpunkte: ${this.skillPoints}
            =============================
        """.trimIndent())
    }
}