// Spieler Klasse
class Player(name: String, endurance: Int, strength: Int) : GameChar(name, endurance, strength) {

    // Skill Punkte die der Spieler bei einem Sieg bekommt.
    var skillPoints = 0
    var gold = 0

    // Spieler Zug
    override fun turn(): String{
        println("""Wähle eine Attacke:
            |1. Hieb
            |2. Schnitt
            |3. Stich
        """.trimMargin())

        var input: Int
            try{
                input = readln().toInt()
                if (input < 1 || input > 3){
                    throw Exception("Ungültige Eingabe: Wähle eine Option (1-3)")
                }
            }catch(ex: Exception){
                println(ex.message)
                input = readln().toInt()
            }


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

    // Spieler Attribute verbessern
    fun training() {
        do {
            println(
                """=== Training ===
            |Skillpunkte: ${this.skillPoints}
            |
            |HP: ${this.health}
            |DMG: ${this.damage.first()} - ${this.damage.last()}
            |======================
            |Attribute: 
            |1. Ausdauer: ${this.endurance}
            |2. Stärke: ${this.strength}
            |3. Zurück
            |Wähle ein Attribut aus welches du verbessern möchtest.
        """.trimMargin()
            )

            val input = readln()
            when (input) {
                "1" -> {
                    try {
                        if (this.skillPoints < 1) {
                            throw Exception("Nicht genügend Skillpunkte.")
                        } else {
                            this.endurance ++
                            this.skillPoints --
                            this.health = this.endurance * 10
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                    }
                }

                "2" -> {
                    try {
                        if (this.skillPoints < 1) {
                            throw Exception("Nicht genügend Skillpunkte.")
                        } else {
                            this.strength ++
                            this.skillPoints --
                            this.damage = 1..this.strength
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                    }
                }
                "3" -> break
                else -> println("Ungültige Eingabe.")
            }

        }while(true)
    }
}