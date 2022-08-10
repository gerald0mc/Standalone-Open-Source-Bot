package me.gerald.bot.managers.config;

public class Player {
    //player name
    private final String name;
    //player stats
    private final int prestige;
    private final int level;
    private final int xp;
    private final int balance;
    //player inventory
    private final int diamonds;
    private final int blocks;

    public Player(String name, int prestige, int level, int xp, int balance, int diamonds, int blocks) {
        this.name = name;
        this.prestige = prestige;
        this.level = level;
        this.xp = xp;
        this.balance = balance;
        this.diamonds = diamonds;
        this.blocks = blocks;
    }

    public String getName() {
        return name;
    }

    public int getPrestige() {
        return prestige;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getBalance() {
        return balance;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public int getBlocks() {
        return blocks;
    }
}
