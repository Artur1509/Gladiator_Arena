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

    // Kämpfen
    fun fight(enemy: GameChar){
        println("${this.player!!.name} vs. ${enemy.name}")

        do{
            // Spieler & Gegner wählen ihren Angriff
            val playerTurn = this.player!!.turn()
            val playerDamage = this.player!!.damage.random()

            // Schaden den der Angriff bei Erfolg ausgibt
            val enemyTurn = enemy.turn()
            val enemyDamage = enemy.damage.random()

            //Kampflogik Hieb(Schere) Schnitt(Stein) Stich(Papier)
            // Unentschieden
            if(playerTurn == enemyTurn){
                println("*Kling*")
            }
            //Schnitt(Stein) schlägt Hieb(Schere)
            if(playerTurn == "hieb" && enemyTurn == "schnitt"){
                this.player!!.health -= enemyDamage
                println("${enemy.name} trifft dich mit '$enemyTurn' und verursacht $enemyDamage Schaden.")
            }
            if(playerTurn == "schnitt" && enemyTurn == "hieb"){
                enemy.health -= playerDamage
                println("Du triffst ${enemy.name} mit '$playerTurn' und verursachst $playerDamage Schaden.")
            }
            //Hieb(Schere) schlägt Stich(Papier)
            if(playerTurn == "hieb" && enemyTurn == "stich"){
                enemy.health -= playerDamage
                println("Du triffst ${enemy.name} mit '$playerTurn' und verursachst $playerDamage Schaden.")
            }
            if(playerTurn == "stich" && enemyTurn == "hieb"){
                this.player!!.health -= enemyDamage
                println("${enemy.name} trifft dich mit '$enemyTurn' und verursacht $enemyDamage Schaden.")
            }
            //Stich(Papier) schlägt Schnitt(Stein)
            if(playerTurn == "stich" && enemyTurn == "schnitt"){
                enemy.health -= playerDamage
                println("Du triffst ${enemy.name} mit '$playerTurn' und verursachst $playerDamage Schaden.")
            }
            if(playerTurn == "schnitt" && enemyTurn == "stich"){
                this.player!!.health -= enemyDamage
                println("${enemy.name} trifft dich mit '$enemyTurn' und verursacht $enemyDamage Schaden.")
            }

            // Lebenspunkte auf 0 stellen wenn diese kleiner sind als 0
            if(enemy.health < 0){
                enemy.health = 0
            }
            if(this.player!!.health < 0){
                this.player!!.health = 0
            }

            // Anzeige der Lebenspunkte des Spielers & Gegners
            println("=== ${this.player!!.name} | HP: ${this.player!!.health} ===")
            println("=== ${enemy.name} | HP: ${enemy.health} ===")

            // Schleife unterbrechen
            if(this.player!!.health <= 0){
                break
            }
            if(enemy.health <= 0){
                break
            }


        }while(true)

        // Ergebnis des Kampfes
        if(this.player!!.health <= 0 && enemy.health <= 0){
            println("Unentschieden!")
        }
        else if(this.player!!.health <= 0){
            println("Du bist Tot...")
        }
        else{
            println("Du hast gewonnen!")
            println("Skillpunkte: +1")
            this.player!!.health = this.player!!.endurance * 10
            this.player!!.skillPoints++
            this.levels.remove(enemy)
        }
    }

}