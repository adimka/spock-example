import spock.lang.*
import app.*

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
		setup:
			def a = new Adventurer(100)
			def aServ = Mock(AdventureService)
			def adv = Mock(Adventure)
			def t = Mock(Treasure)
			t.name >> "Test Treasure"
			t.value >> 100
			adv.loot >> [t]
			aServ.create() >> adv
		when:
			a.findTreasure(aServ)
		then:
			a.items.size() == 1
			a.items[0].name == "Test Treasure"
			a.items[0].value == 100 
  	}

  	def "selling treasure generates gold and prints items sold"(int x){
    // TODO: Implement this feature method using a data pipe to test different amounts of loot
		setup:
			def a = new Adventurer(100)
			x.times{
				def t = Mock(Treasure)
				t.value >> 10
				t.name >> "item${it}"
				a.items << t
			}
		when:
			a.sellTreasure()
		then:
			a.gold == x*10
			a.items.size() == 0 
		where:
			x << [1, 10, 25, 100]
  	}

	// Next two are good examples of Exception thrown
  	def "verify can't use potion at full health"(){
    // TODO: Implement this feature method using an exception condition

		setup:
			def a = new Adventurer(100)
			Treasure pot = Mock()
			pot.name >> "Health Potion"
		when:
			a.items << pot
			a.usePotion()
		then:
			thrown(CannotHealMoreException)
  	}

  	def "potion heals properly"(int hpLevel, int expectedHP){
    //TODO: Implement this feature method using a data table or data pipes
		setup:
			def a = new Adventurer(hpLevel)
			Treasure pot = Mock()
			pot.name >> "Health Potion"
		when:
			a.items << pot
			a.usePotion()
		then:
			a.currHP == expectedHP
		where:
			hpLevel |	expectedHP
			50		|	60
			90		| 	100
			0		|	10
  	}

	def "fighting a weak monster causes it to take damage and faint"(){
	//TODO: Implement this feature method using a mocked interaction
		setup:
			def m = Mock(Monster)
			def a = new Adventurer(10)
			m.currHP >>> [50,0]
			m.damage >> 5
		when:
			a.fight(m)
		then:
			(1.._) * m.takeDamage(a.damage)
			1 * m.faint()
	}
}
