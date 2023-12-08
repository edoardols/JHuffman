import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCodecmd {

    //implement data structure Tree
    public static class Node implements Comparable<Node> {

        //attributes class Node
        public char character;
        public int frequency;
        public Node leftChild;
        public Node rightChild;

        //constructor class Node
        public Node(char character, int frequency, Node leftChild, Node rightChild) {
            this.character = character;
            this.frequency = frequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        //methods class Node

        //compare node on frequency
        public int compareTo(Node that) {
            return (this.frequency - that.frequency);
        }

        //control if the node is a leaf
        boolean isLeaf() {
            return (this.leftChild ==null && this.rightChild ==null);
        }
    }

    //attributes class HuffmanCodecmd
    public static int alphabet_size = 256; //extended ASCII
    public static String encodedString = "";
    public static String[] huffmanCode = new String[alphabet_size];

    //----------------------------------------encoder----------------------------------------

    /* reads string from cmd, encode using Huffman codes and write the encoded input,
    frequency table, Huffman codes, execution characteristics in cmd
    */

    //attributes for encoder
    public static String dataEncode = "";
    public static int[] bitEncodedCharacter = new int[alphabet_size];
    public static int characterInput = 0;
    public static int bitEncodedInput = 0;
    public static int compressionFactor = 0;
    public static long timeStart = 0;
    public static long timeStartEncode = 0;
    public static long encodeTime = 0;
    public static long buildTableTime = 0;
    public static long buildCodeTime = 0;


    public static void encode(String input) {
        //input
        dataEncode = input;

        //build frequency table
        timeStart = System.currentTimeMillis();
        int[] frequency = frequency(dataEncode);
        buildTableTime = System.currentTimeMillis() - timeStart;

        //build Huffman code
        timeStart = System.currentTimeMillis();
        buildHuffmanCodeEncode(huffmanCode, huffmanTree(frequency), "", 0);
        buildCodeTime = System.currentTimeMillis() - timeStart;

        //write encodedInput
        timeStartEncode = System.currentTimeMillis();
        encodeData(dataEncode, huffmanCode);
        encodeTime = System.currentTimeMillis() - timeStartEncode;

        //write compressedInput
        compressData();

        //write frequency table and Huffman code
        frequencyTableAndHuffmanCode(frequency, huffmanCode);

        //write statistics
        statisticsE();
    }

    //build frequency table
    public static int[] frequency(String input){

        int[] frequencyCharacter = new int[alphabet_size];
        for(char character : input.toCharArray()) {
            //read each character and increase its frequency
            frequencyCharacter[character]++;
            characterInput++;
        }
        return frequencyCharacter;
    }

    //build HuffmanTree
    public static Node huffmanTree(int[] frequency) {

        //insert all node "character and character frequency in the PriorityQueue
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for(char i = 0 ; i < alphabet_size; i++){
            if(frequency[i]>0) {

                //character
                priorityQueue.add(new Node(i, frequency[i], null, null));
            }
        }

        //if there is only one character in input
        if(priorityQueue.size() == 1){
            priorityQueue.add(new Node('\0', 1 , null, null));
        }

        //the priorityQueue has more than one Node
        while(priorityQueue.size() > 1) {

            //return and delete two node with lower frequency in the queue
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            //create a new node "parent" whose frequency is the sum of the child's frequency
            Node parent = new Node ('\0', left.frequency + right.frequency, left, right);

            //add the new node "parent" in the queue
            priorityQueue.add(parent);
        }

        //return tree root
        return priorityQueue.poll();
    }

    //build HuffmanCode
    public static void buildHuffmanCodeEncode(String[] huffmanCode, Node x, String s, int path){

        if(!x.isLeaf()) {
            //walk trough the HuffmanTree
            buildHuffmanCodeEncode(huffmanCode, x.leftChild, s + "0", path + 1);
            buildHuffmanCodeEncode(huffmanCode, x.rightChild, s + "1", path + 1);
        }
        else {
            huffmanCode[x.character] = s;
            bitEncodedCharacter[x.character] = path;
        }
    }

    //write encodedString
    public static void encodeData(String input, String[] huffmancode) {

        System.out.println("\r\n" + "Encoded message");
        for(char character : input.toCharArray()) {
                System.out.print(huffmancode[character]);
                encodedString = encodedString + huffmancode[character];
        }
        System.out.println("");
    }

    //write compressedData
    public static void compressData(){

        System.out.println("\r\n" + "Compressed message");
        int bitChar = 0;
        //count bit read
        int nbitChar = 0;
        String lastChar = "";
        for(char character : encodedString.toCharArray()) {
            nbitChar++;
            lastChar += character;
            if (character==49){
                //1
                bitChar += Math.pow(2,8-nbitChar);
            }
            if(nbitChar==8){
                System.out.print((char)bitChar);
                bitChar = 0;
                nbitChar = 0;
                lastChar = "";
            }
        }
        if(nbitChar!=0){
            //less the 8 bit to compress
            System.out.print(" last bit non compressed " + lastChar);
        }
        System.out.println("");
    }
    //write frequency table and Huffman code
    public static void frequencyTableAndHuffmanCode(int[] frequency, String[] huffmancode) {

        System.out.println("\r\n" + "character frequency HuffmanCode");
        for (int i = 0; i < alphabet_size; i++) {
            if(frequency[i] > 0){
                bitEncodedInput = bitEncodedInput + frequency[i]*bitEncodedCharacter[i];
                if(i!=32){
                    //write only character who are in input
                    System.out.println((char)i + " " + frequency[i] + " " + huffmancode[i]);
                }
                else{
                    //only character space
                    System.out.println("space " + frequency[i] + " " + huffmancode[i]);
                }
            }
        }
    }

    public static void statisticsE(){

        System.out.println("\r\n" + "statisticsEncode");
        compressionFactor = 100*(8*characterInput-bitEncodedInput)/(8*characterInput);
        System.out.println("Build time table " + buildTableTime + " ms"+"\r\n" +
                           "Build time code " + buildCodeTime + " ms" + "\r\n" +
                           "Encode time " + encodeTime + " ms" + "\r\n" +
                           "Character input " + characterInput + "\r\n" +
                           "Bit input " + 8*characterInput + "\r\n" +
                           "Bit encoded input " + bitEncodedInput + "\r\n" +
                           "Compression factor " + compressionFactor + "%");
    }

    //----------------------------------------decoder----------------------------------------

    /* reads encodedString, write the decoded input and execution characteristics in cmd */

    //attributes for encoder
    public static long timeStartDecode;
    public static long decodeTime;

    public static void decode(){
        timeStartDecode = System.currentTimeMillis();

        //input file and Huffman code

        //write decodedInput
        decodeData(encodedString, huffmanCode);

        //creation file statisticsDecode
        decodeTime = System.currentTimeMillis() - timeStartDecode;
        statisticsD();
    }


    //write decodedInput
    public static void decodeData(String data, String[] huffmanCode){

        System.out.println("\r\n" + "Decoded message");
        String codeRead = "";
        //read character 0 or 1
        for(char character : data.toCharArray()) {
            //concatenate code
            codeRead += character;
            for(int i = 0; i < alphabet_size; i++){
                //check for valid code
                if(codeRead.equals(huffmanCode[i])){
                    //write character
                    System.out.print((char)i);
                    codeRead = "";
                    break;
                }
            }
        }
    }

    //write statisticDecode
    public static void statisticsD(){
        System.out.println("\r\n" + "\r\n" + "statisticDecode" + "\r\n" + "Decode time " + (decodeTime) + " ms" +"\r\n");
    }

    //----------------------------------------main----------------------------------------

    public static void main(String[] args){

        //verification of a valid command
        boolean valid_command=true;
        while(valid_command){

            //request for a command
            System.out.println("Enter a command, choose between ENCODE or EXIT");
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();

            if( !command.equals("EXIT") ) {
                if( command.equals("ENCODE") ) {

                    System.out.println("Write a string and press Enter");
                    //read the commandline string
                    Scanner scanner = new Scanner(System. in);
                    String inputcmd = scanner.nextLine();

                    encode(inputcmd);
                    decode();
                }
                else {
                    System.out.println("An invalid command has been entered");
                }
            }
            else {
                valid_command=false;
            }
        }
    }
    //----------------------------------------end program----------------------------------------
}