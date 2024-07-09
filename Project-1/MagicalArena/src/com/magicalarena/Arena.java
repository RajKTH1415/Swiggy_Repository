package com.magicalarena;

public class Arena {
    private Player playerA;
    private Player playerB;

    public Arena(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public Player fight() {
        while (playerA.isAlive() && playerB.isAlive()) {
            if (playerA.getHealth() <= playerB.getHealth()) {
                attack(playerA, playerB);
                if (playerB.isAlive()) {
                    attack(playerB, playerA);
                }
            } else {
                attack(playerB, playerA);
                if (playerA.isAlive()) {
                    attack(playerA, playerB);
                }
            }
        }
        return playerA.isAlive() ? playerA : playerB;
    }

    private void attack(Player attacker, Player defender) {
        int attackRoll = Dice.roll();
        int defenseRoll = Dice.roll();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defense = defender.getStrength() * defenseRoll;
        int damage = attackDamage - defense;

        if (damage > 0) {
            defender.reduceHealth(damage);
        }

        System.out.println(attacker + " attacked " + defender + " for " + damage + " damage. Defender's health: " + defender.getHealth());
    }
}
