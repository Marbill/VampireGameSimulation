package dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

/* @author marbi */
public class Dungeon {

    private Random rand;
    private int length;
    private int height;
    private List<Vampire> vamps;
    private int moves;
    private boolean vampiresMove;
    private Player player;

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.rand = new Random();
        this.length = length;
        this.height = height;
        this.vamps = new ArrayList<Vampire>();

        while (this.vamps.size() < vampires) {
            boolean found = false;
            int x = rand.nextInt(this.length);
            int y = rand.nextInt(this.height);

            if (!this.vamps.isEmpty()) {
                for (Vampire vamp : vamps) {
                    if (vamp.getX() == x && vamp.getY() == y) {
                        found = true;
                    }
                }
            }
            if (x == 0 && y == 0) {
                found = true;
            }
            if (!found) {
                this.vamps.add(new Vampire(x, y));
            }
        }

        this.moves = moves;
        this.vampiresMove = vampiresMove;
        this.player = new Player();
    }

    private void printPlayer() {
        System.out.println(player);
    }

    private void printVampires() {
        //System.out.println(this.vamps.size());
        for (Vampire vampire : vamps) {
            System.out.println(vampire);
        }
    }

    private void map() {

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.length; j++) {
                boolean skip = false;
                if (this.player.getX() == j && this.player.getY() == i) {
                    System.out.print("@");
                    skip = true;
                }
                for (Vampire vamp : vamps) {
                    if (vamp.getX() == j && vamp.getY() == i) {
                        System.out.print("v");
                        skip = true;
                    }
                }
                if (!skip) {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private void playerMoves(String m) {
        if (!m.isEmpty()) {
            char[] moves = m.toCharArray();

            for (char move : moves) {
                if (move == 'w') {
                    if (this.player.getY() > 0) {
                        this.player.setY(this.player.getY() - 1);
                    }
                } else if (move == 's') {
                    if (this.player.getY() != this.height) {
                        this.player.setY(this.player.getY() + 1);
                        if (this.player.getY() == this.height) {
                            this.player.setY(this.player.getY() - 1);
                        }
                    }
                } else if (move == 'a') {
                    if (this.player.getX() > 0) {
                        this.player.setX(this.player.getX() - 1);
                    }
                } else if (move == 'd') {
                    if (this.player.getX() != this.length) {
                        this.player.setX(this.player.getX() + 1);
                        if (this.player.getX() == this.length) {
                            this.player.setX(this.player.getX() - 1);
                        }

                    }
                }

            }
        }

        return;
    }

    private void killVampires() {
        List<Vampire> toBeRemoved = new ArrayList<Vampire>();

        for (Vampire vampire : this.vamps) {
            if (vampire.getX() == this.player.getX() && vampire.getY() == this.player.getY()) {
                toBeRemoved.add(vampire);
            }
        }

        if (!toBeRemoved.isEmpty()) {
            this.vamps.removeAll(toBeRemoved);

        }

    }

    private void moveVampires() {
        for (int i = 0; i < this.vamps.size(); i++) {
            int x = rand.nextInt(this.length);
            int y = rand.nextInt(this.height);

            if (this.vamps.get(i).getX() != x && this.vamps.get(i).getY() != y) {
                this.vamps.get(i).setX(x);
                this.vamps.get(i).setY(y);
            }
        }
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        boolean youWin = false;
        boolean youLose = false;

        System.out.println(this.moves);
        System.out.println();

        this.printPlayer();
        this.printVampires();
        System.out.println();

        this.map();
        System.out.println();

        while (true) {

            String pMoves = in.nextLine();

            this.playerMoves(pMoves);

            this.killVampires();
            if (this.vamps.isEmpty()) {
                youWin = true;
                break;
            }
            this.moves -= 1;
            if (this.moves == 0) {
                youLose = true;
                break;
            }

            if (vampiresMove) {
                this.killVampires();
                this.moveVampires();

            }

            System.out.println(this.moves);
            System.out.println();
            this.printPlayer();
            this.printVampires();
            System.out.println();

            this.map();
            System.out.println();

        }

        if (youLose) {
            System.out.println("YOU LOSE");
        }

        if (youWin) {
            System.out.println("YOU WIN");
        }
    }

}
