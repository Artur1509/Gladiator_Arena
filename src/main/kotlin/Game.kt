class Game {
    // Hauptattribute von Game
    var levels: MutableList<GameChar> = mutableListOf(
        GameChar("Augustus", 1, 4),
        GameChar("Magnus", 1, 3),
        GameChar("Julius", 1, 5 ),
        GameChar("Asterix", 1, 5),
        GameChar("Obelix", 1, 5),
        GameChar("Champion", 1, 5)
    )
    // Spieler
    var player: Player? = null
    var playerWins = 0

    // Skillpunkte zur einmaligen Vergabe bei der erstellung eines neuen Gladiators.
    var skillPoints = 10

    // Spieler erstellen
    fun createPlayer(): Player {

        var name = ""
        var endurance = 0
        var strength = 0

        println("Gladiator Name:")
        name = readln()

        while (this.skillPoints > 0) {
            println("Du hast ${this.skillPoints} Skillpunkte die du frei verteilen kannst.")

            println(
                """
            |===== Attribute =====
            |1. Ausdauer (${endurance})
            |2. Stärke (${strength})
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
                            endurance += input1
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
                            strength += input1
                            this.skillPoints -= input1
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                    }
                }
                else -> println("Ungültige Eingabe.")
            }
        }
        println("Dein Gladiator wurde erstellt. Viel Glück in der Arena ${name}!")
        println()
        return Player(name, endurance, strength)
    }

    // Spieler Stats anzeigen
    fun showStats(){
        this.player!!.showStats()
    }

    // Kämpfen
    fun fight(enemy: GameChar){
        var playerHealth = this.player!!.health
        var enemyHealth = this.levels.first().health

        println("${this.player!!.name} vs. ${enemy.name}")

        if(this.playerWins == 5){
            println("Besiege den Champion ${enemy.name}!")
        }
        else{
            println("Du musst noch ${5 - this.playerWins} Kämpfer besiegen bis du gegen den Champion antreten darfst.")
        }
        println()

        // Kampf Loop
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
                playerHealth -= enemyDamage
                println("${enemy.name} trifft dich mit '$enemyTurn' und verursacht $enemyDamage Schaden.")
            }
            if(playerTurn == "schnitt" && enemyTurn == "hieb"){
                enemyHealth -= playerDamage
                println("Du triffst ${enemy.name} mit '$playerTurn' und verursachst $playerDamage Schaden.")
            }
            //Hieb(Schere) schlägt Stich(Papier)
            if(playerTurn == "hieb" && enemyTurn == "stich"){
                enemyHealth -= playerDamage
                println("Du triffst ${enemy.name} mit '$playerTurn' und verursachst $playerDamage Schaden.")
            }
            if(playerTurn == "stich" && enemyTurn == "hieb"){
                playerHealth -= enemyDamage
                println("${enemy.name} trifft dich mit '$enemyTurn' und verursacht $enemyDamage Schaden.")
            }
            //Stich(Papier) schlägt Schnitt(Stein)
            if(playerTurn == "stich" && enemyTurn == "schnitt"){
                enemyHealth -= playerDamage
                println("Du triffst ${enemy.name} mit '$playerTurn' und verursachst $playerDamage Schaden.")
            }
            if(playerTurn == "schnitt" && enemyTurn == "stich"){
                playerHealth -= enemyDamage
                println("${enemy.name} trifft dich mit '$enemyTurn' und verursacht $enemyDamage Schaden.")
            }

            // Lebenspunkte auf 0 stellen wenn diese kleiner sind als 0
            if(enemyHealth < 0){
                enemyHealth = 0
            }
            if(playerHealth < 0){
                playerHealth = 0
            }

            // Anzeige der Lebenspunkte des Spielers & Gegners
            println("=== ${this.player!!.name} | HP: $playerHealth ===")
            println("=== ${enemy.name} | HP: $enemyHealth ===")

            // Schleife unterbrechen
            if(playerHealth <= 0){
                break
            }
            if(enemyHealth <= 0){
                break
            }


        }while(true)

        // Ergebnis des Kampfes
        if(playerHealth <= 0 && enemyHealth <= 0){
            println("Unentschieden!")
        }
        else if(playerHealth <= 0){
            println("Du bist Tot...")
            println("=== GAME OVER ===")
            mainLoop = false
        }
        else{
            println("Du hast gewonnen!")
            println("Skillpunkte: +1")
            this.player!!.skillPoints++
            this.player!!.gold += 50
            this.levels.remove(enemy)
            this.playerWins++

            // Endgültiger Sieg Bedingung
            if(this.playerWins == 6){
                println("Gratulation, du bist der neue Champion der Arena ${this.player!!.name}!")
                mainLoop = false
            }
        }
    }

    // Ingame Händler funktion
    fun shop(player: Player) {
        do {
            var shopItems = mutableListOf<Item>(
                Weapon("Kurzschwert", 25, 2),
                Armor("Lederrüstung", 45, 6),
                Weapon("Langschwert", 25, 5)
            )
            println(
                """=== Händler ===
            |Gold: ${this.player!!.gold}
        """.trimMargin()
            )

            for (item in shopItems) {
                println("${shopItems.indexOf(item) + 1}. ${item.name}")
            }
            println(
                """
            Auswahl:
            (1 - ${shopItems.size}) Item kaufen
            (${shopItems.size + 2}) Zurück
        """.trimIndent()
            )

            val input = readln().toInt()

            when (input) {
                shopItems.size + 2 -> break

                else -> {
                    try{
                        shopItems[input - 1].buyItem(this.player!!)

                    }catch(ex: Exception){
                        println("Ungültige eingabe.")
                    }
                }
            }
        }while(true)
    }

}