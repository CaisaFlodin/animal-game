package com.company.game.animals;

public abstract class Animal {

    public enum AnimalPrice {
        HORSE(300),
        COW(150),
        SHEEP(50),
        PIG(35),
        HEN(10);

        public int price;

        AnimalPrice(int price){
            this.price = price;
        }
    }

    private String name;
    private String gender;
    private int health = 100;

    public Animal (String name, String gender, int health){
        this.name = name;
        this.gender = gender;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
