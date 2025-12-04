
//Michael Perrotta, Alex Magun, Robert Yawson - russianroulette.java
class russsianRoulette{
    class Player{
        public int health = 3;
        String[] inventory = new String[5];

    }
    class Revolver{
        String[] chambers = new String[6];
        int currentChamber;
        
        void buildRevolver(){
            for (int i = 0; i < chambers.length; i++)
            {
                switch((int)(Math.random()*3)+1){
                    case 3 -> chambers[i] = "Live Round";
                    case 2 -> chambers[i] = "Blank Round";
                    case 1 -> chambers[i] = "Empty Chamber";
                    default -> chambers[i] = "Empty Chamber";
                }
            }
        }

        void printRevolver(){
            IO.println("CURRENT REVOLVER");
            IO.println("================================================");
            IO.println("        [" + chambers[0] + "]");
            IO.println("  [" + chambers[5] + "]      [" + chambers[1] + "]");
            IO.println("  [" + chambers[4] + "]      [" + chambers[2] + "]");
            IO.println("        [" + chambers[3] + "]");
            IO.println("================================================");
        }

        void  shoot(Player target){
            IO.println("You fired a " + chambers[currentChamber]);
            switch (chambers[currentChamber]){
                case "Live Round" ->  {
                    target.health = 0;
                    IO.println("You Died");
                }
                case "Blank Round" -> {
                    target.health--;
                    IO.println("You lost 1 HP " + target.health + " HP Remaining");
                }
                case "Empty Chamber" -> IO.println("Lucky You");
            }
            chambers[currentChamber] = "Empty Chamber";
            currentChamber = (int)(Math.random()*6);
        }

        void shoot(Computer target){
            IO.println("The Computer fired a " + chambers[currentChamber]);
            switch (chambers[currentChamber]){
                case "Live Round" ->  {
                    target.health = 0;
                    IO.println("The Computer Died");
                }
                case "Blank Round" -> {
                    target.health--;
                    IO.println("The computer lost 1 HP " + target.health + " HP Remaining");
                }
                case "Empty Chamber" -> IO.println("unucky");
            }
            chambers[currentChamber] = "Empty Chamber";
            currentChamber = (int)(Math.random()*6);
        }
        
    }
    class Computer{
        public int health = 3;
    }
    void main(){
        Revolver gun = new Revolver();
        gun.buildRevolver();
        IO.println("Welcome to Russian Roullete");
        String cont = "hi"; 
        gun.printRevolver();
       int round = 1;
        Player player = new Player();
        Computer computer = new Computer();
        while (!cont.toLowerCase().equals("no")){ 
            cont = IO.readln("Are you Ready to Play (yes or no): ");
            
            int turn = 0;
            int skips = 2;
            player.health =3;
            computer.health =3;
            while (player.health > 0 && computer.health > 0){
                IO.println("Round " + round);
                round++;
                if (turn % 2 == 0){
                    switch(IO.readln("Spin and Shoot (Spin), Skip, or Shoot: ")){
                        case "Spin" -> {
                            gun.currentChamber = (int)(Math.random()*6);
                            gun.shoot(player);
                        }
                        case "Shoot" -> gun.shoot(player);
                        case "Skip" -> {
                            skips--;
                            IO.println("You skipped a turn, you have " + skips + " remaining.");
                        }
                        default -> {
                            IO.println("Whoops thats not an option looks like you're getting shot buddy");
                            gun.shoot(player);
                        }
                    }
                }

                else{
                    switch ((int)(Math.random()*3)){
                        
                        case 0 -> gun.shoot(computer);
                        case 1 -> {
                            gun.currentChamber = (int)(Math.random()*6);
                            gun.shoot(computer);
                        }
                        case 2 ->  IO.println("the computer used a skip");
                    }

                }
                if (computer.health <= 0){
                    IO.println("You won");
                }
                if (player.health <= 0){
                    IO.println("You lost better luck next time");
                }
                turn++;
            }
        }

    }
}