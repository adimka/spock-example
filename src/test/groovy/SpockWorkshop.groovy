import spock.lang.*

/* 	An example specification testing our AdventureService class
   	change the assertions to see what unrolled feature method output
	looks like.
*/
class AdventureServiceSpec extends Specification {
    @Unroll
	def "adventure service produces #x valid adventures"(int x) {
    	setup:
    		def advServ = new AdventureService()
    	when: "we create an adventure with items"
    		def a = advServ.create(x)
    	then: "treasures with names and values get created"
    		a.loot.size() == x
    		a.loot.each{
      			assert Treasure.names.contains(it.name)
      			assert it.value >= 0
    		}
    	where:
    	x << [1, 10, 50, 1000]
  	}

}

/*	Here is where you'll add methods to test our
	Adventurer class!
*/
class AdventurerSpec extends Specification {

  	// Example of a basic feature method
 	def "new adventurer has expected properties"() {
  		when: "spawning a new adventurer"
    	def a = new Adventurer(100)

    	then: "adventurer has default values for stats"
    	a.maxHP == 100
    	a.currHP == 100 
    	a.gold == 0
  	}

  	def "findTreasure method nets treasure"() {
    // TODO: Implement this feature method using mocking

  	}

  	def "selling treasure generates gold and prints items sold"(int x){
    // TODO: Implement this feature method using a data pipe to test different amounts of loot
	
  	}

	// Next two are good examples of Exception thrown
  	def "verify can't use potion at full health"(){
    // TODO: Implement this feature method using an exception condition

  	}

  	def "potion heals properly"(int hpLevel, int expectedHP){
    //TODO: Implement this feature method using a data table or data pipes

  	}

	def "fighting a weak monster causes it to take damage and faint"(){
	//TODO: Implement this feature method using a mocked interaction

	}
}

class Adventurer {
	int gold
	int currHP
  	int maxHP 
	int damage
  	List<Treasure> items

  	public Adventurer( int percentHP ){
  		gold = 0
  		maxHP = 100
  		currHP = (percentHP*maxHP)/100
		damage = 5
  		items = []
  	}

  	//Takes an adventure and puts its loot in our items
  	def findTreasure(AdventureService adv){
		def a = adv.create()
    	items += a.loot
    	a.loot = []
  	}

  	// Add each items' value to gold and remove all items
  	def sellTreasure(){
    	items.removeAll{
      		println "Selling ${it.name} for ${it.value} gold."
      		gold += it.value;
   		}
  	}

	// Heal 10 (or to max) if we have a health potion
  	def usePotion() {
    	if (!items.find{it.name == "Health Potion"}) {
      		throw new NoPotionException()
    	}
    	else if (currHP >= maxHP) {
      		throw new CannotHealMoreException()
   		}
    	else {
      		maxHP - currHP >= 10 ? currHP += 10 : (currHP = maxHP)
      		items.removeElement{
        		it.name == "Health Potion"
      		}
    	}
  	}

	def fight(Monster m){
		while(true){
			m.takeDamage(damage)
			if(m.currHP <= 0){
				m.faint()
				break
			}
			currHP -= m.damage
			if(currHP <= 0){
				faint()
				break
			}	
		}
	}
	
	private void faint(){
		println "You have fainted and lost all your possessions!"
		gold = 0
		items = []
	}

}

class Monster {

	def currHP
	def maxHP
	def damage

	public Monster(int hp, int dmg){
		maxHP = hp
		currHP = maxHP
		damage = dmg
	}

	def faint(){
		println "You have slain the monster!"
	}
	
	def takeDamage(int dmg){
		currHP -= dmg
	}

}

class AdventureService {
	// Creates and returns an adventure with the specified number of treasures
  	def rng = new Random()
  	Adventure create(){
    	def adv = new Adventure()
    	adv.loot = []
		numTreasures = (Math.abs.(rng.nextInt()%5)+1)
    	numTreasures.times{
    		adv.loot << (new Treasure(name:Treasure.names.getAt(Math.abs(rng.nextInt()%11)), value:Math.abs(rng.nextInt()%500)))
    	}
    	adv
  	}

	Adventure create(int numTreasures){
    	def adv = new Adventure()
    	adv.loot = []
    	numTreasures.times{
    		adv.loot << (new Treasure(name:Treasure.names.getAt(Math.abs(rng.nextInt()%11)), value:Math.abs(rng.nextInt()%500)))
    	}
    	adv
	}
}

class Adventure {
  	List<Treasure> loot
}

class Treasure {
  	static names = ["Gemstone", "Ancient Rune", "Magical Boots",
      "Rock", "Scroll of Eternal Life", "Health Potion",
      "Necronomicon", "Liquid of Questionable Origin", "Beer",
      "Solid Gold Wombat Statue", "Bag of Stale Jellybeans"] 
  	String name
  	int value
}

class NoPotionException extends Exception{

}

class CannotHealMoreException extends Exception {

}
