fun menu(){
    val menu = listOf<String>("1. Kämpfen", "2. Trainieren", "3. Attribute", "4. Tutorial", "5. Händler", "6. Inventar", "7. Spiel beenden")

    repeat(20){
        println()
    }

    println("=== Gladiator Arena ===")
    for(i in menu){
        println(i)
    }

    val input = readln()
    when(input){
        "1" -> game.fight(game.levels.first())
        "2" -> game.player!!.training()
        "3" -> game.showStats()
        "4" -> game.tutorial()
        "5" -> game.shop(game.player!!)
        "6" -> game.player!!.showInventory()
        "7" -> mainLoop = false
        else -> println("Ungültige Eingabe.")
    }
}


val game = Game()
var mainLoop = true

fun main(){
    game.intro()
    game.player = game.createPlayer()

    while(mainLoop) {
        menu()
    }

    println()
    println("Danke das du Gladiator Arena gespielt hast, bis zum nächsten mal.")
}

