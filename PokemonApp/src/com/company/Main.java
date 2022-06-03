package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import netscape.javascript.JSObject;


import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import static com.company.Pokemon.War;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Pokemon> poke = new ArrayList<>();
        Random rand = new Random();
        Gson gson = new Gson();
        String json;
        boolean x = true;
        boolean y = true;
        int i;
        FileReader read = new FileReader("pokemon.json");
        try {

            JsonArray array = (JsonArray) new JsonParser().parse(new FileReader("pokemon.json"));
            for (Object temp : array) {
                JsonObject jsonobject = (JsonObject) temp;
                String rename = jsonobject.get("name").getAsString();
                int health = jsonobject.get("health").getAsInt();
                int strength = jsonobject.get("strength").getAsInt();
                int agility = jsonobject.get("agility").getAsInt();
                Pokemon pokemon = new Pokemon(rename, health, strength, agility);
                poke.add(pokemon);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            System.out.println("Menu");
            System.out.println("1. Make new Pokemon");
            System.out.println("2. Eat");
            System.out.println("3. War");
            System.out.println("4. Info");
            System.out.println("5. Delete Pokemon");
            System.out.println("6. Quit");
            int input = keyboard.nextInt();
            if (input == 1) {
                do {
                    PrintWriter out = new PrintWriter(new FileWriter("pokemon.json"));
                    int health = 100;
                    int strength = 75;
                    int agility = 60;
                    int rand_strength = rand.nextInt(strength);
                    int rand_agility = rand.nextInt(agility);
                    System.out.println("You got a new Pokemon!! enter name : ");
                    String name = keyboard.next();
                    Pokemon pokemon = new Pokemon(name, health, rand_strength, rand_agility);
                    poke.add(pokemon);
                    json = gson.toJson(poke);
                    System.out.println("your pokemon's name is " + name + " with " + health + " HP ," + rand_strength + " power, " + rand_agility + " agility .");
                    out.write(json);
                    out.close();

                    System.out.println("Make a new one ? Y/N");
                    String choice = keyboard.next();
                    if (choice.equalsIgnoreCase("Y")) {
                        y = true;
                    } else {
                        y = false;
                    }
                } while (y);
            } else if (input == 2) {
                PrintWriter out = new PrintWriter(new FileWriter("pokemon.json"));
                System.out.println("choose your pokemon to be given food: ");
                i = 1;
                int pokemonnumber;
                for (Pokemon obj : poke) {
                    System.out.println(i + ". " + obj.getname());
                    i++;
                }
                System.out.println("input number : ");
                pokemonnumber = keyboard.nextInt();
                if (poke.get(pokemonnumber - 1).getHealth() < 100) {
                    poke.get(pokemonnumber - 1).eat();
                    json = gson.toJson(poke);
                    out.write(json);
                    out.close();
                } else {
                    System.out.println("your pokemon has max health");
                }


            } else if (input == 3) {
                PrintWriter out = new PrintWriter(new FileWriter("pokemon.json"));
                int pokemonnumber1;
                int pokemonnumber2;
                System.out.println("Choose your first pokemon");
                i = 1;
                for (Pokemon obj : poke) {
                    System.out.println(i + ". " + obj.getname());
                    i++;
                }
                System.out.println("input number : ");
                pokemonnumber1 = keyboard.nextInt();
                System.out.println("Choose your second pokemon");
                i = 1;
                for (Pokemon obj : poke) {
                    System.out.println(i + ". " + obj.getname());
                    i++;
                }
                do {
                    System.out.println("input number : ");
                    pokemonnumber2 = keyboard.nextInt();
                } while (pokemonnumber1 == pokemonnumber2);
                War(poke.get(pokemonnumber1 - 1), poke.get(pokemonnumber2 - 1));
                json = gson.toJson(poke);
                out.write(json);
                out.close();


            } else if (input == 4) {
                i = 1;
                int pokemonnumber;
                if (poke.isEmpty()) {
                    System.out.println("you dont have any pokemon!");
                } else {
                    System.out.println("choose your pokemon: ");
                    for (Pokemon obj : poke) {
                        System.out.println(i + ". " + obj.getname());
                        i++;
                    }
                    System.out.println("your pokemon's info :");
                    pokemonnumber = keyboard.nextInt();
                    System.out.println("name : " + poke.get(pokemonnumber - 1).getname() + " , health : " + poke.get(pokemonnumber - 1).getHealth() + " , strength : " + poke.get(pokemonnumber - 1).getStrength() + " , agility : " + poke.get(pokemonnumber - 1).getAgility());
                }
            } else if (input == 5) {
                PrintWriter out = new PrintWriter(new FileWriter("pokemon.json"));
                System.out.println("delete pokemon :");
                i = 1;
                int pokemonnumber;
                for (Pokemon obj : poke) {
                    System.out.println(i + ". " + obj.getname());
                    i++;
                }
                System.out.println("input number : ");
                pokemonnumber = keyboard.nextInt();
                System.out.println("are you sure? Y/N");
                String choice = keyboard.next();
                if (choice.equalsIgnoreCase("Y")) {
                    y = true;
                    poke.remove(pokemonnumber - 1);
                    json = gson.toJson(poke);
                    out.write(json);
                    out.close();

                }
            } else if (input == 6) {
                x = false;
            }

        } while (x);


    }

}
