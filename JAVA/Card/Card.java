/* THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - DONG SUN YOON */

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;



public class Card {
    private int suit;
    private int value;
    
    public Card (int suits, int values) {
        suit = suits;
        value = values;     
    }
    
    public static void reset(ArrayList<Card> deck){
        int size = 1;
        while (size <= deck.size()){
            deck.remove(size-1);
        }
        buildDeck(deck);
    }
    
    public static void resetGame(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
        int size = 1;
        while (size <= playerHand.size()){
            playerHand.remove(size-1);
        }
        while (size <= dealerHand.size()){
            dealerHand.remove(size-1);
        }
        
    }
    public static void buildDeck(ArrayList<Card> deck) {
        
        for (int i = 1; i<=4; i++)
            for (int j = 1; j<=13; j++)
                deck.add(new Card(i,j));
        
        }

    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
        for(int i = 0; i<=1; i++){
            Random random = new Random();
            int pick = random.nextInt(deck.size());
            Card cardPlayer = deck.get(pick);
            playerHand.add(cardPlayer);
            deck.remove(pick);
            
            int pick1 = random.nextInt(deck.size());
            Card cardDealer = deck.get(pick1);
            dealerHand.add(cardDealer);
            deck.remove(pick1);
        } 
    }

    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand){
        Random random = new Random();
        int deal = random.nextInt(deck.size());
        Card card = deck.get(deal);
        hand.add(card);
        deck.remove(random);
    }
    public static int aceCard(ArrayList<Card> hand){
        int count = 0;
        for (int i = 0 ; i<hand.size(); i++){
            if (hand.get(i).value == 1)
                count ++;
        }
        return count;
    }
    public static int score(ArrayList<Card> hand){
        int sum = 0;
        Card card;
        int ace = aceCard(hand);
        for (int i = 0; i<hand.size(); i++){
            card = hand.get(i);
            if (card.value == 1)
                sum += 11;
            else if (card.value >10)
                sum += 10;
            else
                sum += card.value;    
        }
        if (ace >0 && sum >21){
            while (ace >0 && sum >21){
                sum -= 10;
                ace--;
            }
        }

        
        return sum;
    }
    public static boolean checkBust(ArrayList<Card> hand){
        int total = score(hand);
        if (total >=22)
            return true;
        else
            return false;
    }

    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
        
        int score = score(hand);
        int ace = aceCard(hand);
        if (ace >0 && score >= 17)
            return false;
        if (score>=17)
            return false;
        
        while (score < 17){
            dealOne(deck, hand);
            score = score(hand);
        }
        if (checkBust(hand))
            return true;
        else
            return false;
    }

    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
        int player = score(playerHand);
        int dealer = score(dealerHand);
        if (player > dealer)
            return 1;
        else
            return 2;
    }

    public static String displayCard(ArrayList<Card> hand){
        String cardType = "", cardValue = "", cardHand = "";
        int type = hand.get(0).suit;
        switch (type){
            case 1 : cardType = (char)'\u2660' +"";break; //spade
            case 2 : cardType = (char)'\u2764' +"";break; //heart
            case 3 : cardType = (char)'\u2663' +"";break; //club
            case 4 : cardType = (char)'\u2666' +"";break; //diamond
        }  
    
        
        int value = hand.get(0).value;
  
        switch (value){
            case 1 : cardValue = "Ace";break;
            case 11 : cardValue = "Jack";break;
            case 12 : cardValue = "Queen";break;
            case 13 : cardValue = "King"; break;
            default : cardValue = ""+ value; break;
            }
        

        cardHand += cardValue + cardType;
        
        return cardHand;
        
    }

    public static String displayHand(ArrayList<Card> hand){
        String cardType = "", cardValue = "", cardHand = "";
        for (int i = 0 ; i <hand.size(); i++){
            
            int type = hand.get(i).suit;
            int value = hand.get(i).value;
            switch (value){
                case 1 : cardValue = "Ace";break;
                case 11 : cardValue = "Jack";break;
                case 12 : cardValue = "Queen";break;
                case 13 : cardValue = "King"; break;
                default : cardValue = ""+value;break;
                }
        
            switch (type){
                case 1 : cardType = (char)'\u2660' +"";break; //spade
                case 2 : cardType = (char)'\u2764' +"";break; //heart
                case 3 : cardType = (char)'\u2663' +"";break; //club
                case 4 : cardType = (char)'\u2666' +"";break; //diamond
                }  
            
            
                
            cardHand += cardValue + cardType; 
        }
            
        
        return cardHand;
    }

    public static void main(String[] args) {
        
    int playerChoice, winner;
    ArrayList<Card> deck = new ArrayList<Card> ();
    buildDeck(deck);
    
    playerChoice = JOptionPane.showConfirmDialog(null, "Ready to Play Blackjack?", "Blackjack", JOptionPane.OK_CANCEL_OPTION);
    
    if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
        System.exit(0);

    
    Object[] options = {"Hit","Stand"};
    boolean isBusted, dealerBusted;
    boolean isPlayerTurn;
    ArrayList<Card> playerHand = new ArrayList<>();
    ArrayList<Card> dealerHand = new ArrayList<>();
    
    do{ // Game loop
        reset(deck);
        resetGame(playerHand, dealerHand);
        initialDeal(deck, playerHand, dealerHand);
        isPlayerTurn=true;
        isBusted=false;
        dealerBusted=false;
        
        while(isPlayerTurn){

        // Shows the hand and prompts player to hit or stand
        playerChoice = JOptionPane.showOptionDialog(null,"Dealer shows " + displayCard(dealerHand) + "\n Your hand is: " + displayHand(playerHand) + "\n What do you want to do?","Hit or Stand",
                               JOptionPane.YES_NO_OPTION,
                               JOptionPane.QUESTION_MESSAGE,
                               null,
                               options,
                               options[0]);

        if(playerChoice == JOptionPane.CLOSED_OPTION)
            System.exit(0);
        
        else if(playerChoice == JOptionPane.YES_OPTION){
            dealOne(deck, playerHand);
            isBusted = checkBust(playerHand);
            if(isBusted){
            // Case: Player Busts
            playerChoice = JOptionPane.showConfirmDialog(null,"Player has busted!", "You lose", JOptionPane.OK_CANCEL_OPTION);

            if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
                System.exit(0);
            
            isPlayerTurn=false;
            }
        }
        
        else{
            isPlayerTurn=false;
        }
        }
        if(!isBusted){ // Continues if player hasn't busted
        dealerBusted = dealerTurn(deck, dealerHand);
        if(dealerBusted){ // Case: Dealer Busts
            playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "The dealer busted.\n Congrautions!", "You Win!!!", JOptionPane.OK_CANCEL_OPTION);          

            if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
            System.exit(0);
        }
        
        
        else{ //The Dealer did not bust.  The winner must be determined
            winner = whoWins(playerHand, dealerHand);

            if(winner == 1){ //Player Wins
            playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "\n Congrautions!", "You Win!!!", JOptionPane.OK_CANCEL_OPTION);

            if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
                System.exit(0);
            }

            else{ //Player Loses
            playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "\n Better luck next time!", "You lose!!!", JOptionPane.OK_CANCEL_OPTION); 
            
            if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
                System.exit(0);
            }
        }
        }
    }while(true);
    }

}
