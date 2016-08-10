package app
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
