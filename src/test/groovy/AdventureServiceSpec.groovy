import spock.lang.*
import app.*

/* 	An example specification testing our AdventureService class.
   	change the assertions to see what unrolled feature method output
	looks like.
*/
class AdventureServiceSpec extends Specification {
    // TODO: Play with asserts to see how Spock failure output looks
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
