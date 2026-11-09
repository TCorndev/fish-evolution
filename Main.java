import java.util.*;

public class Main {
    public static int foodcount = 6;
    public static int gen = 1;
    public static boolean foodPresent = false;
    public static int[] color = new int[3];
    public static ArrayList<Fish> fishes = new ArrayList<Fish>();
    public static GameFrame frame = new GameFrame();
    public static Timer timer = new Timer();
    public static Food food = new Food();
    public static Random rng = new Random();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            fishes.add(new Fish(gen, i + 1));
            fishes.get(i).setColor(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
        }
        print();
        addFish();
        timer.scheduleAtFixedRate(task, 0, 1000 / 20);
    }

    public static void mutate() {
        for (int i = 0; i < fishes.size(); i++) {
            fishes.get(i).setSpeed(fishes.get(i).getSpeed() + (rng.nextInt(6) - 2));
            fishes.get(i).setColor(fishes.get(i).getR() + (rng.nextInt(31) - 15),
                    fishes.get(i).getG() + (rng.nextInt(31) - 15), fishes.get(i).getB() + (rng.nextInt(31) - 15));
        }
    }

    public static void print() {
        for (int i = 0; i < fishes.size(); i++) {
            System.out.println("Gen: " + fishes.get(i).getGen() + " No: " + fishes.get(i).getNumber() + " Speed: "
                    + fishes.get(i).getSpeed() + " R: " + fishes.get(i).getR() + " G: " + fishes.get(i).getG() + " B: "
                    + fishes.get(i).getB());
        }
    }

    public static void survive() {
        System.out.println("---Fish fight---");
        for (int i = 0; i < fishes.size(); i++) {
            if (fishes.get(i).getFull() == false) {
                fishes.remove(i);
                i--;
            }
        }
    }

    public static void breed() {
        int total = fishes.size();
        for (int i = 0; i < total; i++) {
            fishes.add(new Fish(fishes.get(i).getGen(), fishes.get(i).getNumber(), fishes.get(i).getSpeed(),
                    fishes.get(i).getR(), fishes.get(i).getG(), fishes.get(i).getB()));
        }
        gen = gen + 1;
        for (int i = 0; i < fishes.size(); i++) {
            fishes.get(i).setGen(gen);
            fishes.get(i).setNumber(i + 1);
            fishes.get(i).setFull(false);
        }
    }

    public static void addFish() {
        Random rng = new Random();
        for (int i = 0; i < fishes.size(); i++) {
            fishes.get(i).setPos(rng.nextInt(1001), rng.nextInt(1001));
            frame.panel.setColor(fishes.get(i).getColor());
            frame.panel.addFish(fishes.get(i).getXPos(), fishes.get(i).getYPos(), i);
        }
    }

    public static void addFood() {
        Random rng = new Random();
        food.setPos(rng.nextInt(1001), rng.nextInt(1001));
        frame.panel.addFood(food.getXPos(), food.getYPos());
        foodcount--;
        foodPresent = true;
        System.out.println(foodcount);
    }

    public static void Move() {
        for (int i = 0; i < fishes.size(); i++) {
            if (fishes.get(i).getFull() == false) {
                int foodx = food.getXPos();
                int foody = food.getYPos();
                int fishx = fishes.get(i).getXPos();
                int fishy = fishes.get(i).getYPos();

                if (foodx > fishx) {
                    fishes.get(i).setXPos(fishx + fishes.get(i).getSpeed());
                    frame.panel.setDir(1, i);
                    if (fishes.get(i).getXPos() > foodx) {
                        fishes.get(i).setXPos(foodx);
                    }
                }
                if (foodx < fishx) {
                    frame.panel.setDir(0, i);
                    fishes.get(i).setXPos(fishx - fishes.get(i).getSpeed());
                    if (fishes.get(i).getXPos() < foodx) {
                        fishes.get(i).setXPos(foodx);
                    }
                }
                if (foody > fishy) {
                    fishes.get(i).setYPos(fishy + fishes.get(i).getSpeed());
                    if (fishes.get(i).getYPos() > foody) {
                        fishes.get(i).setYPos(foody);
                    }
                }
                if (foody < fishy) {
                    fishes.get(i).setYPos(fishy - fishes.get(i).getSpeed());
                    if (fishes.get(i).getYPos() < foody) {
                        fishes.get(i).setYPos(foody);
                    }
                }
                if (fishx == foodx && fishy == foody) {
                    foodPresent = false;
                    fishes.get(i).setFull(true);
                    break;
                }
                frame.panel.addFish(fishx, fishy, i);
            }
        }
        frame.panel.repaint();
    }

    public static TimerTask task = new TimerTask() {
        public void run() {
            if (foodcount > 0 && foodPresent == false) {
                addFood();
            }
            if (foodcount > 0 && foodPresent == true) {
                Move();
            }
            if (foodcount == 0) {
                foodPresent = false;
                frame.panel.reset();
                Collections.sort(fishes);
                survive();
                breed();
                mutate();
                print();
                addFish();
                foodcount = 6;
            }
        }
    };
}