package me.roberttseitin;

/*
 * Animal class, responsible for the base features of individual animals setting their design and implementation whilst
 * enabling the easy creation of additional animals with a lot of control as to their individual implementations
 */

import java.util.Scanner;

public abstract class Animal {
    // Instance variables
    private final int maxHunger;
    private final int maxThirst;
    private final int maxWarmth;
    private String name;

    private int currentHunger;
    private int currentThirst;
    private int currentWarmth;

    private String lastAction = "";

    /*
     * Overloaded constructor that purely assigns the name of the animal
     * Pre: String of name
     */
    public Animal(String name) {
        this.name = name;

        maxHunger = 15;
        maxThirst = 15;
        maxWarmth = 20;

        currentHunger = maxHunger;
        currentThirst = maxThirst;
        currentWarmth = maxWarmth;
    }

    /*
     * Overloaded constructor that assigns the name alongside stats for the animal
     * Pre: String of name, max hunger integer, max thirst integer, max warmth integer
     */
    public Animal(String name, int maxHunger, int maxThirst, int maxWarmth) {
        this.name = name;

        this.maxHunger = maxHunger;
        this.maxThirst = maxThirst;
        this.maxWarmth = maxWarmth;

        currentHunger = maxHunger;
        currentThirst = maxThirst;
        currentWarmth = maxWarmth;
    }

    /*
     * Returns to the lastAction instance variable which represents the last action/task done by the animal
     * Post: Returns the lastAction string
     */
    public String getLastAction() {
        return lastAction;
    }

    /*
     * Sets the name of the animal
     * Pre: Takes in a String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * Returns the name of the animal
     * Post: Returns the String name
     */
    public String getName() {
        return name;
    }

    /*
     * Sets the current hunger of the animal
     * Pre: Takes in an Integer currentHunger
     */
    public void setCurrentHunger(int currentHunger) {
        this.currentHunger = currentHunger;
    }

    /*
     * Returns the current hunger of the animal
     * Post: Returns the Integer currentHunger
     */
    public int getCurrentHunger() {
        return currentHunger;
    }

    /*
     * Sets the current thirst of the animal
     * Pre: Takes in an Integer currentThirst
     */
    public void setCurrentThirst(int currentThirst) {
        this.currentThirst = currentThirst;
    }

    /*
     * Returns the current thirst of the animal
     * Post: Returns the Integer currentThirst
     */
    public int getCurrentThirst() {
        return currentThirst;
    }

    /*
     * Sets the current warmth of the animal
     * Pre: Takes in an Integer currentWarmth
     */
    public void setCurrentWarmth(int currentWarmth) {
        this.currentWarmth = currentWarmth;
    }

    /*
     * Returns the current warmth of the animal
     * Post: Returns the Integer currentWarmth
     */
    public int getCurrentWarmth() {
        return currentWarmth;
    }

    /*
     * Returns the maximum hunger of the animal
     * Post: Returns the Integer maxHunger
     */
    public int getMaxHunger() {
        return maxHunger;
    }

    /*
     * Returns the maximum warmth of the animal
     * Post: Returns the Integer maxWarmth
     */
    public int getMaxWarmth() {
        return maxWarmth;
    }

    /*
     * Returns the maximum hunger of the animal
     * Post: Returns the Integer maxHunger
     */
    public int getMaxThirst() {
        return maxThirst;
    }

    /*
     * Returns a string displaying all the information about the animal (name, and stats)
     * Post: Returns a String
     */
    public String toString() {
        String animalInfo = "";
        animalInfo += String.format("ANIMAL: %s\n", name);
        animalInfo += String.format("HUNGER: %s/%s\n", currentHunger, maxHunger);
        animalInfo += String.format("THIRST: %s/%s\n", currentThirst, maxThirst);
        animalInfo += String.format("WARMTH: %s/%s", currentWarmth, maxWarmth);

        return animalInfo;
    }

    /*
     * Displays the task selection menu and executes different tasks depending on the
     * chosen selection option
     */
    public void menu() {
        Scanner input = new Scanner(System.in);

        System.out.println(this);
        System.out.println("\nWhat would you like to do?");
        System.out.println("1 - Hunt for food");
        System.out.println("2 – Search for water");
        System.out.println("3 – Find Shelter");
        System.out.println("4 – Do Nothing Today");

        int userChoice;
        do {
            System.out.print("Choice: ");
            userChoice = input.nextInt();
            input.nextLine();
        } while (userChoice < 1 || userChoice > 4);
        System.out.println();

        System.out.println("= Today's Task =");
        if (userChoice == 1) {
            findFood();
            lastAction = "FOOD";
        } else if (userChoice == 2) {
            findWater();
            lastAction = "WATER";
        } else if (userChoice == 3) {
            findWarmth();
            lastAction = "SHELTER";
        } else {
            lastAction = "SKIP";
            System.out.println("You did not do anything today, the pinnacle of laziness");
            System.out.println("However, you had a good rest!");
        }

        System.out.println();

        System.out.println("= Daily Stats Loss =");
        dailyStatsLoss();


        System.out.println();
        repairStatsOverflow();
    }

    /*
     * The method that is executed to find food, it is expected to be overwritten by the subclasses
     * thus it is the action run when the find food task is initiated
     */
    protected abstract void findFood();

    /*
     * The method that is executed to find water, it is expected to be overwritten by the subclasses
     * thus it is the action run when the find water task is initiated
     */
    protected abstract void findWater();

    /*
     * The method that is executed to find warmth, it is expected to be overwritten by the subclasses
     * thus it is the action run when the find warmth task is initiated
     */
    protected abstract void findWarmth();

    /*
     * The method that is responsible for adjusting any stats of the animal on a daily basis, is executed regardless of
     * the chosen daily task. Will generally be used as a daily stats' loss.
     */
    protected abstract void dailyStatsLoss();

    /*
     * Depending on the day will print out an encouraging message
     * Pre: Integer of the day
     */
    public void messageOfEncouragement(int day) {
        if (day % 10 == 0 && day % 15 == 0) {
            System.out.println("Wow, you're doing extremely well! Keep going! You might even have a day that goes extremely well!");
        } else if (day == 10) {
            System.out.println("Day 10 o-o, impressive, especially for someone just starting out");
        } else if (day % 15 == 0) {
            System.out.printf("%s days in, this is just the start, you got this, you can stay alive!\n", day);
        }
    }

    /*
     * Adjusts the animals stats according if any stat exceeds its maximum or is below zero
     */
    private void repairStatsOverflow() {
        if (currentWarmth > maxWarmth) currentWarmth = maxWarmth;
        if (currentHunger > maxHunger) currentHunger = maxHunger;
        if (currentThirst > maxThirst) currentThirst = maxThirst;

        if (currentWarmth < 0) currentWarmth = 0;
        if (currentHunger < 0) currentHunger = 0;
        if (currentThirst < 0) currentThirst = 0;
    }

    /*
     * Returns whether the animal has died (If any stat is zero)
     * Post: Boolean representing if the animal has died
     */
    public boolean hasAnimalDied() {
        boolean diedOfThirst = currentThirst <= 0;
        boolean diedOfHunger = currentHunger <= 0;
        boolean diedOfWarmth =  currentWarmth <= 0;

        return diedOfThirst || diedOfHunger || diedOfWarmth;
    }

    /*
     * Returns a string representing what caused the animal's cause of death, or "None" if it has not died
     * Post: String representing cause of death
     */
    public String causeOfDeath() {
        if (currentThirst <= 0) return "Thirst";
        if (currentHunger <= 0) return "Hunger";
        if (currentWarmth <= 0) return "Warmth";

        return "None";
    }
}
