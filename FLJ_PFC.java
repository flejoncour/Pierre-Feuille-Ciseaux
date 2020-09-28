/* Projet NFA031 - Frédéric Le Joncour
 * Il s'agit d'une version "doucement avancée" dans laquelle l'IA peut jouer aléatoirement plusieurs fois la même figure pour influencer le joueur. 
 */
import java.util.ArrayList;
import java.util.Scanner;
public class FLJ_PFC {  
// Fonction de détermination du jeu de l'IA :
  public static String choixIA(){
    String c = " "; 
    double x = Math.random();
    if ((x<=0.33)){
      c = "Pierre";
    }
    else if ((x>0.33) && (x<=0.66)){
      c = "Feuille";
    }
    else if ((x>0.66) && (x<1)){
      c = "Ciseaux";
    }
    return c;
  }
  
// Fonction renvoyant le mot correspondant à l'entier entré par le joueur : 
  public static String choixJoueur(int jj){
    String cj = ""; 
    
    if (jj == 1) {
      cj = "Pierre";
    }
    else if (jj == 2) {
      cj = "Feuille";
    }
    else if (jj == 3) {
      cj = "Ciseaux";
    }
    
    return cj; 
  }
  
  
// Fonction du duel, renvoyant le point si le joueur gagne :  
  public static int duel(String ci, int cj){
    int pj = 0;
    
    // Si victoire du joueur : 
    if ((ci == "Pierre") && (cj == 2)) {
      pj = 2;
    }
    else if ((ci == "Feuille") && (cj == 3)) {
      pj = 2;    
    }
    else if ((ci == "Ciseaux") && (cj == 1)) {
      pj = 2;
    }
    
    // si Ex aequo : 
    else if ((ci == "Ciseaux") && (cj == 3)) {
      pj = 1;
    }
    else if ((ci == "Pierre") && (cj == 1)) {
      pj = 1;
    }
    else if ((ci == "Feuille") && (cj == 2)) {
      pj = 1;
    }
    
    return pj;
  }
  
  
  
  
  public static void main(String[] args) { 
// Initialisation, paramètres de départ : 
    int pot = 0;                // compteur du pot de mise
    int mise = 0;                 // compteur de la mise du joueur
    int pj = 0;                   // compteur de point(s) du joueur
    String result = " ";          // variable contenant le résultat de la manche (gagné ou perdu)
    ArrayList<String> recap = new ArrayList<String>();  // ArrayList contenant les données de la manche pour le récapitulatif en fin de partie
    int manchenum = 0;            // compteur de manche
    String ci = "";
    String cj = "";
    int jj = 0;
    int duel = 0;
    Scanner clavier = new Scanner(System.in);

    
    
    System.out.println("");
    System.out.println("************************************");
    System.out.println("*    PIERRE - FEUILLE - CISEAUX    *");
    System.out.println("************************************");
    System.out.println("");
    
    System.out.println("Combien de jetons possédez-vous (pot de mise) ?");
    int potinit = clavier.nextInt(); // pot initial = valeur de départ du pot
    pot = potinit;
    System.out.println("En combien de tours se jouera la partie ?");
    int tour = clavier.nextInt();   
    
    
// Début de la partie : 
    for (;;){      
      if (manchenum == 0) { 
        System.out.println("");
        System.out.println((manchenum+1) + "e manche !");
        System.out.println("Vous disposez de " + pot + " jetons dans le pot de mise. Combien souhaitez-vous miser ?");
        mise = clavier.nextInt();      
        
        while (mise > pot) {
          System.out.println("La mise est supérieure à vos moyens, retentez :");
          mise = clavier.nextInt();          
        }
        
        ci = choixIA();  // ci pour choix de l'IA
        
        System.out.println("Pierre (tapez 1), Feuille (tapez 2) ou Ciseaux (tapez 3) ?"); 
        jj = clavier.nextInt();
        cj = choixJoueur(jj); // jj pour jeu joueur
        
        System.out.println("");          
        System.out.println("Joueur : " + cj);       
        System.out.println("IA : " + ci);
        
        duel = duel(ci,jj);
        
        if (duel == 0) {
          result = "Perdu !";
          System.out.println(result);
          pot = pot - mise;
        }
        else if (duel == 1) {
          result = "Ex aequo !";
          System.out.println(result + " Vous ne perdez pas votre mise.");
        }
        else if (duel == 2) {
          result = "Gagné !";
          System.out.println(result);
          pot = pot + mise;
        }
        
        pj = pj + (duel/2);
        System.out.println("Vous avez " + pj + " point(s) sur " + (manchenum+1) + " manche(s).");
        System.out.println("");
        
        String manche = (manchenum+1) + "E MANCHE :";
        String coupj = "Coup du joueur : " + cj; 
        String coupia = "Coup de l'IA : " + ci; 
        String tabtourmise =  "Mise : " + mise;
        String tabtourpot = "Pot de mise : " + pot;
        
        recap.add(" ");
        recap.add(manche);
        recap.add(coupj);
        recap.add(coupia);
        recap.add(tabtourmise);
        recap.add("Résultat de la manche : " + result);
        recap.add(tabtourpot);
        
        manchenum = manchenum + 1;
        
        if (pot <= 0) {
          System.out.println("Vous n'avez plus de jetons, désolé.");
          break; 
        }
        
        if (manchenum == tour) {
          break;
        }
      }
      
      
      
      else if (manchenum>=1) {
        double x = Math.random();
        
        
        if (x<=0.2) {
          // si x<=0.2 alors on ne tire plus au sort le jeu de l'IA, mais elle va conserver le même jeu pendant plusieurs tours pour le changer imprévisiblement
          int bouclerepet = 0; 
          if ((x<=0.1333) && (x>0.0666)){
            bouclerepet = 1;
          }
          else if (x>0.1333) {
            bouclerepet = 2;
          }
          
          
          for (int i = 0; i <= bouclerepet; i++){  
            System.out.println("");
            System.out.println((manchenum+1) + "e manche !");
            System.out.println("Vous disposez de " + pot + " jetons dans le pot de mise. Combien souhaitez-vous miser ?");
            mise = clavier.nextInt();      
            
            while (mise > pot) {
              System.out.println("La mise est supérieure à vos moyens, retentez :");
              mise = clavier.nextInt();          
            }
            
            // ici on ne tire plus le jeu de l'IA grâce à la fonction, mais "ci" conserve la valeur du tour précédent
            System.out.println("Pierre (tapez 1), Feuille (tapez 2) ou Ciseaux (tapez 3) ?"); 
            jj = clavier.nextInt();
            cj = choixJoueur(jj);
            
            System.out.println("");          
            System.out.println("Joueur : " + cj);       
            System.out.println("IA : " + ci);
            
            duel = duel(ci,jj);
            
            if (duel == 0) {
              result = "Perdu !";
              System.out.println(result);
              pot = pot - mise;
            }
            else if (duel == 1) {
              result = "Ex aequo !";
              System.out.println(result + " Vous ne perdez pas votre mise.");
            }
            else if (duel == 2) {
              result = "Gagné !";
              System.out.println(result);
              pot = pot + mise;
            }
            
            pj = pj + (duel/2);
            System.out.println("Vous avez " + pj + " point(s) sur " + (manchenum+1) + " manche(s).");
            System.out.println("");
            
            String manche = (manchenum+1) + "E MANCHE :";
            String coupj = "Coup du joueur : " + cj; 
            String coupia = "Coup de l'IA : " + ci; 
            String tabtourmise =  "Mise : " + mise;
            String tabtourpot = "Pot de mise : " + pot;
            
            recap.add(" ");
            recap.add(manche);
            recap.add(coupj);
            recap.add(coupia);
            recap.add(tabtourmise);
            recap.add("Résultat de la manche : " + result);
            recap.add(tabtourpot);
            
            manchenum = manchenum + 1;
            
            if (pot <= 0) {
              System.out.println("Vous n'avez plus de jetons, désolé.");
              break; 
            }
            
            
            if (manchenum == tour) {
              break;
            }
          }
          if (pot <= 0) { // utile pour sortir entièrement de la boucle du tour, car au stade précédent, nous sommes pris dans 2 boucles (manchenum>=1 et x>0,2)
            break; 
          }
        }
        
        
        else if (x>0.2) {
          System.out.println("");
          System.out.println((manchenum+1) + "e manche !");
          System.out.println("Vous disposez de " + pot + " jetons dans le pot de mise. Combien souhaitez-vous miser ?");
          mise = clavier.nextInt();      
          
          while (mise > pot) {
            System.out.println("La mise est supérieure à vos moyens, retentez :");
            mise = clavier.nextInt();          
          }
          
          ci = choixIA();  // reprise de la partie normalement avec choix de l'IA via la fonction aléatoire
          
          System.out.println("Pierre (tapez 1), Feuille (tapez 2) ou Ciseaux (tapez 3) ?"); 
          jj = clavier.nextInt();
          cj = choixJoueur(jj); 
          
          System.out.println("");          
          System.out.println("Joueur : " + cj);       
          System.out.println("IA : " + ci);
          
          duel = duel(ci,jj);
          
          if (duel == 0) {
            result = "Perdu !";
            System.out.println(result);
            pot = pot - mise;
          }
          else if (duel == 1) {
            result = "Ex aequo !";
            System.out.println(result + " Vous ne perdez pas votre mise.");
          }
          else if (duel == 2) {
            result = "Gagné !";
            System.out.println(result);
            pot = pot + mise;
          }
          
          pj = pj + (duel/2);
          System.out.println("Vous avez " + pj + " point(s) sur " + (manchenum+1) + " manche(s).");
          System.out.println("");
          
          String manche = (manchenum+1) + "E MANCHE :";
          String coupj = "Coup du joueur : " + cj; 
          String coupia = "Coup de l'IA : " + ci; 
          String tabtourmise =  "Mise : " + mise;
          String tabtourpot = "Pot de mise : " + pot;
          
          recap.add(" ");
          recap.add(manche);
          recap.add(coupj);
          recap.add(coupia);
          recap.add(tabtourmise);
          recap.add("Résultat de la manche : " + result);
          recap.add(tabtourpot);
          
          manchenum = manchenum + 1;
          
          if (pot <= 0) {
            System.out.println("Vous n'avez plus de jetons, désolé.");
            break; 
          }
          
          
          if (manchenum == tour) {
            break;
          }
        }
        if (pot <= 0) {   // utile pour sortir entièrement de la boucle du tour, car au stade précédent, nous sommes pris dans 2 boucles (manchenum>=1 et x>0,2)
          break; 
        }
      }
      
      
      
    }
    
    System.out.println("Fin de partie !");
    if (pot > potinit) {
      System.out.println("Vous disposez de " + pot + " jetons dans votre pot de mise, vous repartez donc avec " 
                                + (pot - potinit) + " jetons de plus en poche, félicitations.");
    }
    else if (pot < potinit) {
      System.out.println("Vous disposez de " + pot + " jetons dans votre pot de mise, vous avez donc perdu " + (potinit - pot) + " jetons, dommage.");
    }
    
    System.out.println("");
    System.out.println("");
    
    if (manchenum>=1) {  // possible de l'enlever ? 
      System.out.println("Récapitulatif de la partie :");
      
      String[] tabrecap = new String [recap.size()];
      tabrecap = recap.toArray(tabrecap);
      
      for (int k=0; k<tabrecap.length; k++){
        System.out.println(tabrecap[k]);
      }
    }
    
  }  
}

