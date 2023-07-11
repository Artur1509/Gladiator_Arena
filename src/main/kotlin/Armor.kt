class Armor(name: String, price: Int, value: Int): Item(name, price, value) {
    override var isArmor = true
}