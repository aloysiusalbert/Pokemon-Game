package com.company;




class Pokemon {
    private String name;
    private int health;
    private int strength;
    private int agility;

    public  Pokemon( String name,int health,int strength,int agility){
        this.name=name;
        this.health=health;
        this.strength=strength;
        this.agility=agility;
    }
    public void setName(String name){
        this.name=name;
    }
    public void sethealth(int num){
        this.health=num;
    }
    public  String getname(){
        return name;
    }
    public int getHealth(){
        return health;
    }
    public int getStrength(){
        return strength;
    }
    public int getAgility(){
        return agility;
    }
    public void eat()  {

            health = health + 20;
            if (health > 100) {
                health = 100;
            }
            System.out.println("Successful! health becomes : " + health);
            sethealth(health);
    }
      static int War(Pokemon pokemon1, Pokemon pokemon2) {
        System.out.println(pokemon1.name+" hits "+ pokemon2.name);
        pokemon2.health= pokemon2.health-pokemon1.strength;
        System.out.println(pokemon1.name+" gives "+ pokemon1.strength+" damage to "+pokemon2.name+ " and "+pokemon2.name+"'s health is "+pokemon2.health+" left.");
        System.out.println(pokemon2.name+" hits back");
        pokemon1.health= pokemon1.health-pokemon2.strength;
        System.out.println(pokemon2.name+" gives "+ pokemon2.strength+" damage to "+pokemon1.name+ " and "+pokemon1.name+"'s health is "+pokemon1.health+" left.");
         if(pokemon1.health<0){
             System.out.println(pokemon1.name+" died ");
         }
         if(pokemon2.health<0){
             System.out.println(pokemon2.name+" died ");
         }

          return pokemon1.health;
      }

}

