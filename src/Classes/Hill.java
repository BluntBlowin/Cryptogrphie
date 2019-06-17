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
public class Hill {
    int N=26+10+26;
    private String textClair;
    private int[] textClairInt;
    private String textChiffre;
    private int[] textChiffreInt;
    
    public String chiffrer(int a,int b,int c,int d,String textClair){
        if(verifierLaMatrice(a, b, c, d)){
        int length=textClair.length();
        this.textClair=textClair;
        this.textChiffre="";
        if(length%2!=0){this.textClair+="x";length++;} 
       // System.out.println("Length: "+length);
        this.textClairInt=new int[length]; 
        this.textChiffreInt=new int[length];
        for(int i=0;i<length;i++){
            this.textClairInt[i]=lettreVersChiffre(textClair.charAt(i)); // remplacer les lettres par leur rang dans l'alphabet
        }
        for(int i=0;i<length-1;i=i+2){
           // System.out.println(i+":"+(i+1));
            this.textChiffreInt[i]=(a*this.textClairInt[i]+b*this.textClairInt[i+1])%N;
            this.textChiffreInt[i+1]=(c*this.textClairInt[i]+d*this.textClairInt[i+1])%N;
            
             
            this.textChiffre=textChiffre+ChiffreVerslettre(this.textChiffreInt[i]);
            this.textChiffre=textChiffre+ChiffreVerslettre(this.textChiffreInt[i+1]);   
        }     
        return this.textChiffre;  
        }
        else return null;
    }
    String Dechiffrer(int a,int b,int c,int d,String textClair){
        //Calcul de coefecients de la matrice inverse;
        int determinant=a*d-b*c;
        if(determinant==0)return null;
        int delta=mul_inv(determinant,N);
        System.out.println(delta);
        //les coefficients de la matrice inverse
        int A=d,B=-b,C=-c,D=a;
        
        int length=textClair.length();
        
        this.textClair=textClair;
        this.textChiffre="";
        
        if(length%2!=0){this.textClair+="x";length++;} 
       // System.out.println("Length: "+length);
        this.textClairInt=new int[length]; 
        this.textChiffreInt=new int[length];
        
        for(int i=0;i<length;i++){
            this.textClairInt[i]=lettreVersChiffre(textClair.charAt(i)); // remplacer les lettres par leur rang dans l'alphabet
        }
        
        
        for(int i=0;i<length-1;i=i+2){
            //System.out.println(i+":"+(i+1));
            
            this.textChiffreInt[i]=(delta*(A*this.textClairInt[i]+B*this.textClairInt[i+1]))%N;
            this.textChiffreInt[i+1]=(delta*(C*this.textClairInt[i]+D*this.textClairInt[i+1]))%N;
            
            if( this.textChiffreInt[i]<0) this.textChiffreInt[i]= this.textChiffreInt[i]+N;
            if( this.textChiffreInt[i+1]<0) this.textChiffreInt[i+1]= this.textChiffreInt[i+1]+N;
                       
            this.textChiffre=textChiffre+ChiffreVerslettre(this.textChiffreInt[i]);
            this.textChiffre=textChiffre+ChiffreVerslettre(this.textChiffreInt[i+1]);  
        }    
        return this.textChiffre;  
    }
    private boolean verifierLaMatrice(int a,int b,int c,int d){
        if(a*d-b*c==0)return false;
        else return true;
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
       String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
       return alphabet.indexOf(c);                
    }
        private static char ChiffreVerslettre(int c){
            
            if(c>26+10+26) System.out.println("SUPERIEUR à N");
            if(c<0) System.out.println("inferieur à N");
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        return alphabet.charAt(c);
    }

   
}
