/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Blunt_Blowin
 */
public class Vigenaire {
    private String textClair;
    private String textChiffre;
    private  String clé;
    
    private int[] RepeteClé; 
    private int[] textClairInt;
    private int[] textChiffreInt;
    private int[] cléInt;
    static String  alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    int N=Vigenaire.alphabet.length();
    
    
    
    public Vigenaire(){

    }
    
    public String  chiffrer(String clé,String textClair){
        this.clé=clé;
        this.textClair=textClair;
        textChiffre="";
        textChiffreInt=new int[textClair.length()]; //creer la table entiere du texte chiffrer
        textClairInt=new int[textClair.length()];//creer la table entiere du texte claire
        for(int i=0;i<textClair.length();i++) textClairInt[i]=textClair.charAt(i); //remplire  table text claire par entiers corespendants aux lettres
        cléInt =new int[clé.length()];
        for(int i=0;i<clé.length();i++)cléInt[i]=clé.charAt(i);//Conversion de la clé en entier
        RepeteClé=new int[textClair.length()];
        for(int i=0;i<textClair.length();i++){
           int j=i%clé.length();
           RepeteClé[i]=cléInt[j];    
        }

       for(int i=0;i<textClair.length();i++){
            textChiffreInt[i]=(lettreVersChiffre(textClair.charAt(i))+RepeteClé[i])%N;
            textChiffre=textChiffre+ChiffreVerslettre(textChiffreInt[i]);
        }   
       return textChiffre;
    }
     
    public String Dechiffrer(String clé,String textClair){
        this.clé=clé;
        this.textClair=textClair;
        textChiffre="";
        textChiffreInt=new int[textClair.length()]; //creer la table entiere du texte chiffrer
        textClairInt=new int[textClair.length()];//creer la table entiere du texte claire
        for(int i=0;i<textClair.length();i++) textClairInt[i]=textClair.charAt(i); //remplire  table text claire par entiers corespendants aux lettres
        cléInt =new int[clé.length()];
        for(int i=0;i<clé.length();i++)cléInt[i]=clé.charAt(i);//Conversion de la clé en entier
        RepeteClé=new int[textClair.length()];
        for(int i=0;i<textClair.length();i++){
           int j=i%clé.length();
           RepeteClé[i]=cléInt[j];    
        }     
       for(int i=0;i<textClair.length();i++){
            textChiffreInt[i]=(lettreVersChiffre(textClair.charAt(i))-RepeteClé[i])%N;
            if(textChiffreInt[i]<0)textChiffreInt[i]=textChiffreInt[i]+N;
            textChiffre=textChiffre+ChiffreVerslettre(textChiffreInt[i]);
        }   
       return textChiffre;       
    }
    
     private static int lettreVersChiffre(char c){
       
       return Vigenaire.alphabet.indexOf(c);                
    }
        private static char ChiffreVerslettre(int c){
            
            if(c>26+10+26) System.out.println("SUPERIEUR à N");
            if(c<0) System.out.println("inferieur à N");
        return Vigenaire.alphabet.charAt(c);
    }
}
