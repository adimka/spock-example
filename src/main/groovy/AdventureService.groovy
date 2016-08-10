package app
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
