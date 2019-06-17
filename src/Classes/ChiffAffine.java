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
public class ChiffAffine {
    private String textClair;
    private String textChiffre;
    private int[] textChiffreInt;
    private int[] textClairInt;
    
     private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
     int N=ChiffAffine.alphabet.length();
    
    public String chiffrer(int a,int b,String textClair){
        this.textClair=textClair;
        this.textClairInt=new int[textClair.length()];   
        this.textChiffreInt=new int[textClair.length()];
        
        this.textChiffre="";
        for(int i=0;i<textClair.length();i++){
            this.textChiffreInt[i]=(a* lettreVersChiffre(textClair.charAt(i))+b)%N;//convertire le text choffré en entier + ajouter la clé
            this.textChiffre=textChiffre+ChiffreVerslettre(textChiffreInt[i]);  
        }    
        return this.textChiffre;
    }
    
    public String Dechiffrer(int a,int b,String textClair){
        this.textClair=textClair;
        this.textClairInt=new int[textClair.length()];   
        this.textChiffreInt=new int[textClair.length()];
        
        this.textChiffre="";
        int alpha=mul_inv(a,N);
        
        for(int i=0;i<textClair.length();i++){
           
            this.textChiffreInt[i]=((lettreVersChiffre(textClair.charAt(i))-b)*alpha)%N;
            if(this.textChiffreInt[i]<0)this.textChiffreInt[i]=this.textChiffreInt[i]+N;
            this.textChiffre=textChiffre+ChiffreVerslettre(this.textChiffreInt[i]);  
            
        }    
        return this.textChiffre;
    }
    
    private static int mul_inv(int a, int b){

	int b0 = b, t, q;
	int x0 = 0, x1 = 1;
	if (b == 1) return 1;
	while (a > 1) {
		q = a / b;
		t = b; b = a % b; a = t;
		t = x0; x0 = x1 - q * x0;x1 = t;
	}
	if (x1 < 0) x1 += b0;
	return x1;
     }
    
    private static int lettreVersChiffre(char c){
       return ChiffAffine.alphabet.indexOf(c);                
    }
        private static char ChiffreVerslettre(int c){
            
            if(c>26+10+26 || c<0) System.out.println("SUPERIEUR à N");
        return ChiffAffine.alphabet.charAt(c);
    }
 
}
