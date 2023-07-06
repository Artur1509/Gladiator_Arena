fun main(){
    var game = Game()
    game.player = Player("Artur", 1, 9)

    var gegner = GameChar("Augustus", 1, 9)

    game.fight(gegner)

    game.showStats()
}

//TODO: Training Funktion, Siegbedingung, Hauptmenü, spieler.zug try catch

