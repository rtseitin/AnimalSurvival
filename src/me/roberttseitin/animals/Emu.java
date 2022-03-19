package me.roberttseitin.animals;

/*
 * The class for the Emu animal, extends the Animal class and edits countless methods to customize the experience and
 * feel of playing as an Emu.
 */

import me.roberttseitin.Animal;

import java.util.Random;

public class Emu extends Animal {
    /*
     * Constructor for the animal, defines its maximum stats and sets starting stats
     */
    public Emu() {
        super("Emu", 10, 10, 13);
        super.setCurrentHunger(9);
        super.setCurrentThirst(8);
    }

    /*
     * The method is what gets executed when the user initiates the find food task, it regenerates the user's hunger by
     * a varying amount in several circumstances depending on several factors
     */
    protected void findFood() {
        Random rand = new Random();
        int replenishBy = rand.nextInt(6) + 6;
        super.setCurrentHunger(getCurrentHunger() + replenishBy);

        System.out.printf("Your hunger has replenished by %s points\n", replenishBy);
    }

    /*
     * The method is what gets executed when the user initiates the find water task, it regenerates the user's thirst by
     * a varying amount in several circumstances depending on several factors
     */
    protected void findWater() {
        setCurrentThirst(getCurrentThirst() + 12);
        setCurrentWarmth(getCurrentWarmth() - 2);

        System.out.println("Your thirst has replenished by 12 points");
        System.out.println("Your warmth has reduced by 2 points");
    }

    /*
     * The method is what gets executed when the user initiates the find shelter task, it regenerates the user's warmth by
     * a varying amount in several circumstances depending on several factors
     */
    protected void findWarmth() {
        Random rand = new Random();
        boolean willFindWarmth = rand.nextBoolean();

        if (willFindWarmth) {
            super.setCurrentWarmth(getMaxWarmth());
            System.out.println("Your warmth has been replenished to its maximum capacity!");
        } else {
            System.out.println("Looks like you did not find any shelter today :/");
        }
    }

    /*
     * The method that is responsible for adjusting any stats of the animal on a daily basis, is executed regardless of
     * the chosen daily task. Responsible for the animal losing stats every day. Crucial for the game.
     */
    protected void dailyStatsLoss() {
        Random rand = new Random();
        int loseStatChoice = rand.nextInt(2) + 1;
        boolean regenerateAllStats = rand.nextInt(500) == 0;

        if (regenerateAllStats) {
            super.setCurrentHunger(super.getMaxHunger());
            super.setCurrentWarmth(super.getMaxWarmth());
            super.setCurrentThirst(super.getMaxThirst());

            System.out.println("WOW! Today was amazing! You somehow managed to do everything despite aiming for for a specific task");
            System.out.println("All your stats were regenerated fully!");
        } else {
            if (loseStatChoice == 1) {
                super.setCurrentThirst(getCurrentThirst() - 3);
                System.out.println("During your task today you worked very hard and got more thirsty than usual. As a result you lost 3 thirst points");

                super.setCurrentWarmth(getCurrentWarmth() - 1);
                System.out.println("During your task today it got mildly cold outside and as a result you lost 1 warmth point");

                super.setCurrentHunger(getCurrentHunger() - 2);
                System.out.println("You saw a caterpillar, sadly it ran away, this made you hungry. You lost 2 hunger points as a result.");
            } else{
                super.setCurrentWarmth(getCurrentWarmth() - 2);
                System.out.println("During your task today it got very cold outside and as a result you lost 2 warmth points");
            }
        }

    }
}
