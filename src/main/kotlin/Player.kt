// Spieler Klasse
class Player(name: String, endurance: Int, strength: Int) : GameChar(name, endurance, strength) {

    // Skill Punkte die der Spieler bei einem Sieg bekommt.
    var skillPoints = 0

    // Spieler Zug
    override fun turn(): String{
        println("""Attacken:
            |1. Hieb
            |2. Schnitt
            |3. Stich
        """.trimMargin())

        val input = readln().toInt()
        return this.attackList[input - 1]
    }
}