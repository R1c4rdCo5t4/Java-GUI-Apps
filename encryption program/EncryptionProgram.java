import java.util.*;

public class EncryptionProgram {
    
    private Scanner sc;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String line;
    private char[] letters;
    

    EncryptionProgram(){
        sc = new Scanner(System.in);
        random = new Random();
        list = new ArrayList();
        shuffledList = new ArrayList();
        character = ' ';
        
        newKey();
        userInput();
    }

    private void userInput(){
        while(true) {
            System.out.println('\n');
            for (int i = 0; i < 30; i++){
                System.out.print('*');
            }
            System.out.println("\nWhat do you want to do?");
            System.out.println("(N)ew Key/(G)et Key/(E)ncrypt/(D)ecrypt/(Q)uit");
            try{
                char response = Character.toUpperCase(sc.nextLine().charAt(0));
                switch(response){
                    case 'N':
                        newKey();
                        break;
                    case 'G':
                        getKey();
                        break;
                    case 'E':
                        encrypt();
                        break;
                    case 'D':
                        decrypt();
                        break;
                    case 'Q':
                        quit();
                        break;
                    default:
                        System.out.println("Sorry, that input was invalid. Please try again.");
            }
        }

            catch(StringIndexOutOfBoundsException e){
                System.out.println("Please input something");
            }

            
                    
            
        }

    }

    private void newKey(){
        character = ' ';
        list.clear();
        shuffledList.clear();

        for(int i = 32; i < 127; i++){
            list.add(Character.valueOf(character));
            character++;

        }
        shuffledList = new ArrayList(list);
        Collections.shuffle(shuffledList);
        System.out.println("A new key has been generated.");
    }

    private void getKey(){
        System.out.println("Key: ");
        for (Character x: list){
            System.out.print(x);
        }
        System.out.println();
        for (Character y:shuffledList){
            System.out.print(y);
            
        }
        System.out.println();
    }

    private void encrypt(){
        System.out.println("Enter a message to be encrypted: ");
        String request = sc.nextLine();
        letters = request.toCharArray();
        System.out.println("Encrypted:");
        for(int i = 0; i<letters.length;i++){
            Character output = shuffledList.get(list.indexOf(letters[i]));
            System.out.print(output);
        }
        

    }

    private void decrypt(){
        System.out.println("Enter a message to be decrypted: ");
        String request = sc.nextLine();
        letters = request.toCharArray();
        System.out.println("Decrypted:");
        for(int i = 0; i<letters.length;i++){
            Character output = list.get(shuffledList.indexOf(letters[i]));
            System.out.print(output);
        }

    }

    private void quit(){
        System.out.println("Bye!");
        System.exit(0);

    }
}
