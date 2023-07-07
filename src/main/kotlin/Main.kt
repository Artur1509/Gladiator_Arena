fun menu(){
    val menu = listOf<String>("1. Kämpfen", "2. Trainieren", "3. Attribute", "4. Tutorial", "5. Spiel beenden")

    println("=== Gladiator Arena ===")
    for(i in menu){
        println(i)
    }

    val input = readln()
    when(input){
        "1" -> game.fight(game.levels.first())
        "2" -> println("leer")
        "3" -> game.showStats()
        "4" -> println("tutorial")
        "5" -> mainLoop = false
        else -> println("Ungültige Eingabe.")
    }
}


val game = Game()
var mainLoop = true

fun main(){

    game.createPlayer()

    while(mainLoop) {
        menu()
    }
}

//TODO: Training Funktion, Siegbedingung, game.Intro, game.Tutorial, Waffen, Mehr Attribute, Items, Shop

