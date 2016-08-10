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

  	}

  	def "selling treasure generates gold and prints #x items sold"(int x){
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
