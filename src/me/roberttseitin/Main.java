package me.roberttseitin;

/*
 * Student: Robert Tseitin
 * Course: ICS4U
 * Date: Dec 6, 2021
 * Lets the user play as an animal that they select completing daily tasks while trying to maintain specified stats
 * corresponding to hunger, thirst, and warmth. The user's goal is to try and keep the animal alive, if any stats
 * reaches 0 the animal dies
 */

import me.roberttseitin.animals.Emu;
import me.roberttseitin.animals.Kangaroo;
import me.roberttseitin.animals.Dingo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Animal[] animals = { new Dingo(), new Emu(), new Kangaroo() };

        // Introduction to the game
        System.out.println("Welcome to the animal survival game!");
        System.out.println("Choose one of the following animals to play as.");
        System.out.println("You will need to keep your hunger, thirst, and warmth levels high enough to survive!");
        System.out.println();

        System.out.println("Who would you like to play as?");

        // Listing animals
        for (int i = 0; i < animals.length; i++) {
            System.out.printf("%s - %s\n", i+1, animals[i].getName());
        }

        System.out.println();

        // Selecting animal
        int choice;
        do {
            System.out.print("Choice: ");
            choice = input.nextInt() - 1;
            input.nextLine();
        } while (choice < 0 || choice  > animals.length-1);

        // Setting selected animal
        Animal a = animals[choice];

        System.out.println();

        int day = 1;

        // Looping through the menu until the animal dies
        while (!a.hasAnimalDied()) {
            System.out.printf("Day %s:\n", day);
            a.messageOfEncouragement(day);
            a.menu();
            day++;
        }

        // Final message when the animal dies, sends cause of death and date
        System.out.printf("Day %s:\n", day);
        System.out.println(a);

        System.out.println();
        System.out.printf("You have died of %s!\n", a.causeOfDeath());

        System.out.printf("You have survived for %s days!", day);
    }
}
