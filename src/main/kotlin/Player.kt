// Spieler Klasse
class Player(name: String, endurance: Int, strength: Int) : GameChar(name, endurance, strength) {

    // Skill Punkte die der Spieler bei einem Sieg bekommt.
    var skillPoints = 0

    // Gold für den Händler
    var gold = 25

    // Spieler Inventar
    var inventory = mutableListOf<Item>()

    var armor: Item? = null
    var weapon: Item? = null

    // Spieler Zug
    override fun turn(): String{
        repeat(20){
            println()
        }

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
            ====== Deine Attribute ======
            Name: ${this.name}
            HP: ${this.health}
            DMG: ${this.damage.first()} - ${this.damage.last()}
            
            Ausrüstung:
            Waffe: ${this.weapon?.name ?: "Leer" }
            Rüstung: ${this.armor?.name ?: "Leer"}
            
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
            repeat(20){
                println()
            }

            println(
                """=== Training ===
            |Skillpunkte: ${this.skillPoints}
            |
            |HP: ${this.health}
            |DMG: ${this.damage.first()} - ${this.damage.last()}
            |
            |Attribute: 
            |1. Ausdauer: ${this.endurance}
            |2. Stärke: ${this.strength}
            |3. Zurück
            |
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
                            println("Deine Ausdauer wurde auf ${this.endurance} erhöht.")
                            println("Drücke 'Enter' um fortzufahren.")
                            readln()

                            if(this.armor != null){
                                this.health = this.endurance * 10 + this.armor!!.value
                            }
                            else{
                                this.health = this.endurance * 10
                            }
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                        println("Drücke 'Enter' um fortzufahren.")
                        readln()
                    }
                }

                "2" -> {
                    try {
                        if (this.skillPoints < 1) {
                            throw Exception("Nicht genügend Skillpunkte.")
                        } else {
                            this.strength ++
                            this.skillPoints --
                            println("Deine Stärke wurde auf ${this.strength} erhöht.")
                            println("Drücke 'Enter' um  fortzufahren.")
                            readln()

                            if(this.weapon != null) {
                                this.damage = 1..(this.strength + this.weapon!!.value)
                            }
                            else{
                                this.damage = 1..this.strength
                            }
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                        println("Drücke 'Enter' um fortzufahren.")
                        readln()
                    }
                }
                "3" -> break
                else -> println("Ungültige Eingabe.")
            }

        }while(true)
    }

    fun showInventory() {
        do {
            repeat(20){
                println()
            }

            println("=== Inventar ===")
            for (item in this.inventory) {
                println("${this.inventory.indexOf(item) + 1}. ${item.name} | +${item.value} ${item.attributeBonus()}")
            }
            if(this.inventory.size == 0){
                println("Leer")
            }

            println()

            println("""Auswahl:
                |Item ausrüsten (1 - ${this.inventory.size})
                |Zurück (${this.inventory.size + 2}) 
            """.trimMargin())

            val input = readln().toInt()
            when(input){
                this.inventory.size + 2 -> break

                else -> {
                    try{
                        println("Du hast ${this.inventory[input - 1].name} ausgerüstet.")
                        this.inventory[input - 1].equipItem(this)
                        println("Drücke 'Enter' um fortzufahren.")
                        readln()

                    }catch(ex: Exception){
                        println("Ungültige Eingabe.")
                        println("Drücke 'Enter' um fortzufahren.")
                        readln()
                    }
                }
            }

        }while(true)
    }

}


