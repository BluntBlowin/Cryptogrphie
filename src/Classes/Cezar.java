
package Classes;


public class Cezar {
    private String textClair;
    private String textChiffre;
    
    private int[] textChiffreInt;
    private int[] textClairInt;
    int clé;
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    int N=Cezar.alphabet.length();

    public Cezar(){
        
    }
    public String getTextChiffre() {
        return textChiffre;
    }
    public void setTextClair(String textClair) {
        this.textClair = textClair;   
    }
    
    public String chiffrer(int clé,String textClair){
        this.clé=clé;
        this.textClair=textClair;
        this.textClairInt=new int[textClair.length()];   
        this.textChiffreInt=new int[textClair.length()];
        this.textChiffre="";
        
        for(int i=0;i<textClair.length();i++){
            this.textChiffreInt[i]=( lettreVersChiffre(textClair.charAt(i))+clé)%N;//remplire  table textChiffreInt par entiers corespendants aux lettres du text clair et rajouter le décalage
            this.textChiffre=textChiffre+ChiffreVerslettre(textChiffreInt[i]);
        }    
        return this.textChiffre;
    }
    
    
    public String dechiffrer(int clé,String textClair){
        this.clé=clé;
        this.textClair=textClair;
        this.textClairInt=new int[textClair.length()];   
        this.textChiffreInt=new int[textClair.length()];
        this.textChiffre="";
        
        for(int i=0;i<textClair.length();i++){
            this.textChiffreInt[i]=( lettreVersChiffre(textClair.charAt(i))-clé)%N;//remplire  table textChiffreInt par entiers corespendants aux lettres du text clair et rajouter le décalage
            this.textChiffre=textChiffre+ChiffreVerslettre(textChiffreInt[i]);
        }    
        return this.textChiffre;
    }
    
    
      private static int lettreVersChiffre(char c){
       return Cezar.alphabet.indexOf(c);                
    }
        private static char ChiffreVerslettre(int c){
        return  Cezar.alphabet.charAt(c);
    }
}
