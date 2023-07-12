class Game {
    // Hauptattribute von Game
    var levels: MutableList<GameChar> = mutableListOf(
        GameChar("Augustus", 3, 4),
        GameChar("Magnus", 4, 4),
        GameChar("Julius", 9, 3 ),
        GameChar("Asterix", 6, 6),
        GameChar("Obelix", 11, 5),
        GameChar("Spartacus", 10, 9)
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
            repeat(20){
                println()
            }

            println("""Trainer: Du hast ${this.skillPoints} Skillpunkte die du frei verteilen kannst.
                |Ausdauer erhöht die Gesundheit deines Gladiators während Stärke dafür sorgt, dass du mehr Schaden austeilst.
            """.trimMargin())

            println()

            println("""
            |===== Attribute =====
            |1. Ausdauer (${endurance})
            |2. Stärke (${strength})
            |
            |Wähle ein Attribut welches du verbessern möchtest.
        """.trimMargin()
            )

            val input = readln()
            when (input) {
                "1" -> {

                    repeat(20){
                        println()
                    }

                    println("Ausdauer: (${this.skillPoints} Punkte übrig)")
                    println()
                    println("Nenne die Anzahl der Punkte die du vergeben möchtest und drücke 'Enter' um zu bestätigen.")
                    try {
                        val input1 = readln().toInt()
                        if (input1 > this.skillPoints) {
                            throw Exception("Zu wenig Skillpunkte.")
                        } else {
                            endurance += input1
                            this.skillPoints -= input1
                            println("Deine Ausdauer wurde um $input1 erhöht.")
                            println("Drücke 'Enter um fortzufahren.")
                            readln()
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                    }

                }

                "2" -> {

                    repeat(20){
                        println()
                    }

                    println("Stärke: (${this.skillPoints} Punkte übrig")
                    println()
                    println("Nenne die Anzahl der Punkte die du vergeben möchtest und drücke 'Enter' um zu bestätigen.")
                    try {
                        val input1 = readln().toInt()
                        if (input1 > this.skillPoints) {
                            throw Exception("Zu wenig Skillpunkte.")
                        } else {
                            strength += input1
                            this.skillPoints -= input1
                            println("Deine Stärke wurde um $input1 erhöht.")
                            println("Drücke 'Enter um fortzufahren.")
                            readln()
                        }
                    } catch (ex: Exception) {
                        println(ex.message)
                    }
                }
                else -> println("Ungültige Eingabe.")
            }
        }
        println("Trainer: Nimm etwas Gold, damit kannst du dir bei unserem Händler Ausrüstungsgegenstände kaufen...")
        println()
        Thread.sleep(1500)
        println("25 Gold wurden dem Inventar hinzugefügt.")
        println()
        Thread.sleep(1500)
        println("Trainer: Viel Glück in der Arena ${name}!")
        println("Drücke 'Enter um fortzufahren.")
        readln()

        return Player(name, endurance, strength)
    }

    // Spieler Stats anzeigen
    fun showStats(){
        repeat(20){
            println()
        }
        this.player!!.showStats()
        println("Drücke 'Enter' um zurück zum Menü zu gelangen.")
        readln()
    }

    // Kämpfen
    fun fight(enemy: GameChar){

        // Lebenspunkte Spieler & Gegner im Kampf
        var playerHealth = this.player!!.health
        var enemyHealth = this.levels.first().health

        repeat(20){
            println()
        }

        println("=== Level: ${this.playerWins + 1} ===")
        println()
        Thread.sleep(1000)
        println("${this.player!!.name} vs. ${enemy.name}")
        println()
        Thread.sleep(1000)

        // Wins bis Endboss
        if(this.playerWins == 5){
            println("Besiege den Champion ${enemy.name}!")
        }
        else{
            println("Du musst noch ${5 - this.playerWins} Kämpfer besiegen bis du gegen den Champion antreten darfst.")
        }
        println()
        println("Drücke 'Enter' um den Kampf zu beginnen!")
        readln()

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
                println("*Kling* Eure Waffen kreuzen sich, niemand erleidet Schaden.")
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
            println("${this.player!!.name} HP: $playerHealth     |     ${enemy.name} HP: $enemyHealth")
            println()
            println("Drücke 'Enter' um fortzufahren.")
            readln()

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
            repeat(20){
                println()
            }

            println("Unentschieden!")
        }

        else if(playerHealth <= 0){
            repeat(20){
                println()
            }
            println("=== GAME OVER ===")
            println("Du bist Tot...")
            println("Drücke 'Enter' um das Spiel zu beenden.")
            readln()
            mainLoop = false
        }
        else{
            repeat(20){
                println()
            }

            println("Du hast gewonnen!")
            println("Skillpunkte: +1 | Gold: +25")
            this.player!!.skillPoints++
            this.player!!.gold += 25
            this.levels.remove(enemy)
            this.playerWins++
            println("Drücke 'Enter' um fortzufahren.")
            readln()

            // Endgültiger Sieg Bedingung
            if(this.playerWins == 6){
                repeat(20){
                    println()
                }

                println("Gratulation, du bist der neue Champion der Arena ${this.player!!.name}!")
                println("Drücke 'Enter' um das Spiel zu beenden.")
                readln()
                mainLoop = false
            }
        }
    }

    // Händler funktion
    fun shop(player: Player) {
        do {
            val shopItems = mutableListOf<Item>(
                Weapon("Kurzschwert", 25, 2),
                Armor("Einfache Lederrüstung", 25, 5),
                Weapon("Einfaches Langschwert", 50, 5),
                Armor("Verbesserte Lederrüstung", 50, 8),
                Weapon("Germanisches Langschwert", 150, 8),
                Armor("Plattenrüstung", 150, 16 )
            )

            repeat(20){
                println()
            }

            println(
                """=== Händler ===
            |Gold: ${this.player!!.gold}
            |
            |Bestand:
        """.trimMargin()
            )

            for (item in shopItems) {
                var attributeBonus: String

                println("${shopItems.indexOf(item) + 1}. ${item.name} | Preis: ${item.price} Gold | +${item.value} ${item.attributeBonus()}")
            }
            println()
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
                        println("${shopItems[input - 1].name} wurde deinem Inventar hinzugefügt. ")
                        println("Drücke 'Enter' um fortzufahren.")
                        readln()

                    }catch(ex: Exception){
                        println("Ungültige eingabe.")
                        println("Drücke Enter um fortzufahren.")
                        readln()
                    }
                }
            }
        }while(true)
    }

    //Game Intro
    fun intro(){
        println("""===== Gladiator Arena Action RPG =====
            |
            |Trainer: Willkommen in der Arena Sklave, wirst du es schaffen, alle deine Gegner besiegen zu können?
            |Kämpfe auf Leben und Tod und besiege den Champion der Arena.  
            |Wenn du überlebst und es schaffst der neue Champion zu werden, wird der Kaiser dir die Freiheit schenken!
        """.trimMargin())
        Thread.sleep(5000)
        println()
        println("Trainer: Starten wir mit der erstellung deines Kämpfers...")
        println()
        Thread.sleep(2000)
    }

}