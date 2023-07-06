class Game {
    // Hauptattribute von Game
    var levels: MutableList<GameChar> = mutableListOf()
    var player: Player? = null

    // Skillpunkte zur einmaligen Vergabe bei der erstellung eines neuen Gladiators.
    var skillPoints = 10

    // Hilfsvariablen zur Spielererstellung
    var name = ""
    var endurance = 0
    var strength = 0

    // Spieler erstellen
    fun createPlayer(): Player {

        println("Gladiator Name:")
        this.name = readln()

        while (this.skillPoints > 0) {
            println("Du hast ${this.skillPoints} Skillpunkte die du frei verteilen kannst.")

            println(
                """
            |===== Attribute =====
            |1. Ausdauer (${this.endurance})
            |2. Stärke (${this.strength})
        """.trimMargin()
            )

            val input = readln()
            when (input) {
                "1" -> {
                    println("Ausdauer: (${this.skillPoints} Punkte übrig)")
                    try {
                        val input1 = readln().toInt()
                        if (input1 > this.skillPoints) {
                            throw Exception("Zu wenig Skillpunkte.")
                        } else {
                            this.endurance += input1
                            this.skillPoints -= input1
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                    }

                }

                "2" -> {
                    println("Stärke: (${this.skillPoints} Punkte übrig)")
                    try {
                        val input1 = readln().toInt()
                        if (input1 > this.skillPoints) {
                            throw Exception("Zu wenig Skillpunkte.")
                        } else {
                            this.strength += input1
                            this.skillPoints -= input1
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                    }
                }
            }
        }
        println("Dein Gladiator wurde erstellt. Viel Glück in der Arena ${this.name}!")
        println()
        return Player(this.name, this.endurance, this.strength)
    }

    // Spieler Stats anzeigen
    fun showStats(){
        this.player!!.showStats()
    }

}