import java.util.Scanner;
import java.util.Random;

//change screen size and dot count
class GameConfig {
    public static int x = 250;
    public static int y = 360;
    public static int dotCount = 97;
}

class Hunter {
    private String name;
    private String color;

    private int positionX;
    private int positionY;

    public Hunter(String playerName, String playerColor) {
        name = playerName;
        color = playerColor;
        positionX = 0;
        positionY = 0;
    }


    public String getName() {
        return name;
    }


    public String getColor() {
        return color;
    }


    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) throws Exception {
        if (positionX > GameConfig.x)
            throw new Exception("Oh oo!!");
        else
            this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) throws Exception {
        if (positionY > GameConfig.y)
            throw new Exception("Oh oo!!");
        else
            this.positionY = positionY;
    }
}

class Solder {
    private int positionX;
    private int positionY;
    private String color;
    private String killingMethod;

    public Solder(String colorParameter,String killingParameter) {
        color=colorParameter;
        killingMethod=killingParameter;
        // generate random position for solder initially
        positionX = new Random().nextInt(GameConfig.x);
        positionY = new Random().nextInt(GameConfig.y);
    }


    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getColor() {
        return color;
    }

    public String getKillingMethod() {
        return killingMethod;
    }
}

/**
 * board value
 * 0 for empty space
 * 1 for dots
 * 2 for super dots
 * 5 for solders
 * 8 for hunter
 */
class Board {
    private int[][] board;
    private Hunter myHunter;
    private Solder[] solders;
    private int collectedDots;
    private int collectedSuperDots;

    public Board(Hunter hunterNew, Solder[] solderList) {
        collectedDots = 0;
        collectedSuperDots = 0;

        Random random = new Random();
        board = new int[GameConfig.x][GameConfig.y];
        myHunter = hunterNew;
        solders = solderList;
        // set solders
        board[solderList[0].getPositionX()][solderList[0].getPositionY()] = 5;
        board[solderList[1].getPositionX()][solderList[1].getPositionY()] = 5;
        board[solderList[2].getPositionX()][solderList[2].getPositionY()] = 5;
        // set super dots
        board[random.nextInt(GameConfig.x)][random.nextInt(GameConfig.y)] = 2;
        board[random.nextInt(GameConfig.x)][random.nextInt(GameConfig.y)] = 2;
        board[random.nextInt(GameConfig.x)][random.nextInt(GameConfig.y)] = 2;
        // set dots
        for (int i = 0; i < GameConfig.dotCount; i++) {
            int x;
            int y;
            // find a place without any other object placements
            do {
                x = random.nextInt(GameConfig.x);
                y = random.nextInt(GameConfig.y);
            } while (board[x][y] != 0);
            // mark find place
            board[x][y] = 1;
        }
        // set hunter
        board[0][0] = 8;
    }

    public int[] move(Hunter newHunter) throws Exception {
        System.out.println(newHunter.getPositionX());
        System.out.println(newHunter.getPositionY());
        int[] returnArray = new int[3];
        if (collectedDots != GameConfig.dotCount || collectedSuperDots != 3) {
            // set return state default
            returnArray[0] = 1;
            // set hunter current position 0
            board[myHunter.getPositionX()][myHunter.getPositionY()] = 0;
            // check what is in the new position
            // if 1 increase dot count
            if (board[newHunter.getPositionX()][newHunter.getPositionY()] == 1)
                collectedDots++;
            // if 2 increase super dot count
            if (board[newHunter.getPositionX()][newHunter.getPositionY()] == 2)
                collectedSuperDots++;
            // if 5 game over
            if (board[newHunter.getPositionX()][newHunter.getPositionY()] == 5){
                if(collectedSuperDots>0){
                    System.out.println("A solder kill by the hunter.");
                    collectedSuperDots--; 
                }else{
                    for(int i=0;i<3;i++){
                        if(solders[i].getPositionX()==newHunter.getPositionX() && solders[i].getPositionY()==newHunter.getPositionY()){
                            System.out.printf("%s color solder kill using %s\n",solders[i].getColor(),solders[i].getKillingMethod());
                            break;
                        }
                    }
                    returnArray[0] = 0;
                }
            }
            
            // set hunter new position and update class attribute
            myHunter.setPositionX(newHunter.getPositionX());
            myHunter.setPositionY(newHunter.getPositionY());
            board[newHunter.getPositionX()][newHunter.getPositionY()] = 8;


        } else {
            returnArray[0] = 2;
        }
        returnArray[1] = collectedDots;
        returnArray[2] = collectedSuperDots;
        return returnArray;
    }

    public void getBoard() {
        for (int[] is : board) {
            for (int i : is) {
                System.out.printf("| %d |", i);
            }
            System.out.println();
        }
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Hunter getMyHunter() {
        return myHunter;
    }

    public void setMyHunter(Hunter myHunter) {
        this.myHunter = myHunter;
    }

    public Solder[] getSolders() {
        return solders;
    }

    public void setSolders(Solder[] solders) {
        this.solders = solders;
    }
}


class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int state = 1;
        int[] mark = new int[2];
        try {
            System.out.println("Welcome to Dot hunter by TechSavvy....");

            Hunter hunter = new Hunter("Matt", "Red");
            System.out.println("Player registered.");

            Solder[] solderList = {new Solder("Red","Hand"), new Solder("Green","Knife"), new Solder("Blue","Gun")};
            Board gameBoard = new Board(hunter, solderList);

            //can see current situation of gameBoard
            gameBoard.getBoard();

            do {
                System.out.println("Make your move by adding x,y coordinate one after the other{first number -> press enter -> second number -> press enter}");
                Hunter moveHunter = new Hunter("Matt", "Red");
                moveHunter.setPositionX(scanner.nextInt() - 1);
                moveHunter.setPositionY(scanner.nextInt() - 1);
                int[] result = gameBoard.move(moveHunter);

                state = result[0];
                mark[0] = result[1];
                mark[1] = result[2];

                System.out.printf("Current Score: dots-%d super dots-%d\n", result[1], result[2]);
                gameBoard.getBoard();

            } while (state == 1);

            if (state == 0) {
                System.out.println("Game over....");
            } else if (state == 2) {
                System.out.println("Congratulations, you won.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
