/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import java.awt.Point;
import java.util.Scanner;
/**
 *
 * @author Blunt_Blowin
 */
public class playfaire {
      // length of digraph array
  private int length = 0;
  
  // table for Playfair cipher
  private String [][] table;
  
  // main method to test Playfair method
  
  // main run of the program, Playfair method
  public playfaire(){
    
  }
  
  // parses any input string to remove numbers, punctuation,
  // replaces any J's with I's, and makes string all caps
  private String parseString(Scanner s){
    String parse = s.nextLine();
    parse = parse.toUpperCase();
    parse = parse.replaceAll("[^A-Z]", "");
    parse = parse.replace("J", "I");
    return parse;
  }
  
  // creates the cipher table based on some input string (already parsed)
  private String[][] cipherTable(String key){
    String[][] playfairTable = new String[5][5];
    String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    
    // fill string array with empty string
    for(int i = 0; i < 5; i++)
      for(int j = 0; j < 5; j++)
        playfairTable[i][j] = "";
    
    for(int k = 0; k < keyString.length(); k++){
      boolean repeat = false;
      boolean used = false;
      for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
          if(playfairTable[i][j].equals("" + keyString.charAt(k))){
            repeat = true;
          }else if(playfairTable[i][j].equals("") && !repeat && !used){
            playfairTable[i][j] = "" + keyString.charAt(k);
            used = true;
          }
        }
      }
    }
    return playfairTable;
  }
  
  // cipher: takes input (all upper-case), encodes it, and returns output
  private String cipher(String in){
    length = (int) in.length() / 2 + in.length() % 2;
    
    // insert x between double-letter digraphs & redefines "length"
    for(int i = 0; i < (length - 1); i++){
      if(in.charAt(2 * i) == in.charAt(2 * i + 1)){
        in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
        length = (int) in.length() / 2 + in.length() % 2;
      }
    }
    
    // adds an x to the last digraph, if necessary
    String[] digraph = new String[length];
    for(int j = 0; j < length ; j++){
      if(j == (length - 1) && in.length() / 2 == (length - 1))
        in = in + "X";
      digraph[j] = in.charAt(2 * j) +""+ in.charAt(2 * j + 1);
    }
    
    // encodes the digraphs and returns the output
    String out = "";
    String[] encDigraphs = new String[length];
    encDigraphs = encodeDigraph(digraph);
    for(int k = 0; k < length; k++)
      out = out + encDigraphs[k];
    return out;
  }
  
  // encodes the digraph input with the cipher's specifications
  private String[] encodeDigraph(String di[]){
    String[] enc = new String[length];
    for(int i = 0; i < length; i++){
      char a = di[i].charAt(0);
      char b = di[i].charAt(1);
      int r1 = (int) getPoint(a).getX();
      int r2 = (int) getPoint(b).getX();
      int c1 = (int) getPoint(a).getY();
      int c2 = (int) getPoint(b).getY();
      
      // case 1: letters in digraph are of same row, shift columns to right
      if(r1 == r2){
        c1 = (c1 + 1) % 5;
        c2 = (c2 + 1) % 5;
        
      // case 2: letters in digraph are of same column, shift rows down
      }else if(c1 == c2){
        r1 = (r1 + 1) % 5;
        r2 = (r2 + 1) % 5;
      
      // case 3: letters in digraph form rectangle, swap first column # with second column #
      }else{
        int temp = c1;
        c1 = c2;
        c2 = temp;
      }
      
      //performs the table look-up and puts those values into the encoded array
      enc[i] = table[r1][c1] + "" + table[r2][c2];
    }
    return enc;
  }
  
  // decodes the output given from the cipher and decode methods (opp. of encoding process)
  private String decode(String out){
    String decoded = "";
    for(int i = 0; i < out.length() / 2; i++){
      char a = out.charAt(2*i);
      char b = out.charAt(2*i+1);
      int r1 = (int) getPoint(a).getX();
      int r2 = (int) getPoint(b).getX();
      int c1 = (int) getPoint(a).getY();
      int c2 = (int) getPoint(b).getY();
      if(r1 == r2){
        c1 = (c1 + 4) % 5;
        c2 = (c2 + 4) % 5;
      }else if(c1 == c2){
        r1 = (r1 + 4) % 5;
        r2 = (r2 + 4) % 5;
      }else{
        int temp = c1;
        c1 = c2;
        c2 = temp;
      }
      decoded = decoded + table[r1][c1] + table[r2][c2];
    }
    return decoded;
  }
  
  // returns a point containing the row and column of the letter
  private Point getPoint(char c){
    Point pt = new Point(0,0);
    for(int i = 0; i < 5; i++)
      for(int j = 0; j < 5; j++)
        if(c == table[i][j].charAt(0))
          pt = new Point(i,j);
    return pt;
  }
  
  public String chiffrer(String keyword,String input){
      table = this.cipherTable(keyword);
      String claire = input;
    // encodes and then decodes the encoded message
      String output = cipher(input);
      return output;
  }
   public String dechiffrer(String keyword,String input){
      table = this.cipherTable(keyword);
      String claire = input;
    // encodes and then decodes the encoded message
      String decodedOutput = decode(input);
      return decodedOutput;
  }
  // prints the cipher table out for the user
  private void printTable(String[][] printedTable){
    System.out.println("This is the cipher table from the given keyword.");
    System.out.println();
    
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        System.out.print(printedTable[i][j]+" ");
      }
      System.out.println();
    }
    System.out.println();
  }
  
  // prints results (encoded and decoded)
  private void printResults(String enc, String dec){
    System.out.println("This is the encoded message:");
    System.out.println(enc);
    System.out.println();
    System.out.println("This is the decoded message:");
    System.out.println(dec);
}
}
