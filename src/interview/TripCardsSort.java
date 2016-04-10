package interview;

import java.util.HashMap; 

class Card {
	String start, end;
	int color;
	
	public Card(String startCity, String endCity) {
		start = startCity;
		end = endCity;
		color = 0;
	}
	
	@Override
	public String toString(){
		return start +" "+ end + " " + color;
	}
}

public class TripCardsSort {
	
	public static Card[] sortCards(Card[] cards){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Card[] path = new Card[cards.length];
		
		for (int i = 0; i < cards.length; i++) {
			map.put(cards[i].start, i);
		}
				
		Integer endIdx = 0;
		for (int i = 0; i < cards.length; i++) {
			if (!map.containsKey(cards[i].end))  
				endIdx = i;
		}
		
		String nextCity = cards[endIdx].start;
		for (int i = cards.length; i >= 0; i--) {
			Integer nextIdx = map.get(nextCity);
			path[i] = cards[nextIdx];
			nextCity = path[i].start;
			
		}
		
		return null;
		
	}
	
	public static void main(String[] args) {
		
		
		Card[] cards = new Card[]{
				 new Card("Francfurt","London")
				,new Card("Moscow","Francfurt")
				,new Card("Perm","Moscow")
				,new Card("London","NewYork")
		};
		
		Card[] trip = sortCards(cards);
	}

}
