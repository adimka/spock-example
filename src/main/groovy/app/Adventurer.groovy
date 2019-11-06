package app
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

  	//Takes an adventureServive which generates and adventure whose loot in our items
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
	
	def faint(){
		println "You have fainted and lost all your possessions!"
		gold = 0
		items = []
	}

}
