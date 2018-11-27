package com.school.nfcard


fun test(){
    val production1:Production<Food> = FoodStore()
    val production2:Production<FastFood> = FastFoodStore()
    val production3:Production<Burger> = InOutBurger()
}


class FoodStore : Production<Food> {
    override fun produce(): Food {
        println("Produce food")
        return Food()
    }
}

class FastFoodStore : Production<FastFood> {
    override fun produce(): FastFood {
        println("Produce FastFood")
        return FastFood()
    }
}

class InOutBurger : Production<Burger> {
    override fun produce(): Burger {
        println("Produce Burger")
        return Burger()
    }
}