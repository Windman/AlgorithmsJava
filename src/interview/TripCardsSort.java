package interview;

import java.util.HashMap; 

class Card {
	String start, end;
		
	public Card(String startCity, String endCity) {
		start = startCity;
		end = endCity;
	}
	
	@Override
	public String toString(){
		return start +" "+ end;
	}
}

public class TripCardsSort {
	
	public static Card[] sortCards(Card[] cards){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Card[] path = new Card[cards.length];
		
		for (int i = 0; i < cards.length; i++) {
			map.put(cards[i].end, i);
		}
				
		Integer startIdx = 0;
		for (int i = 0; i < cards.length; i++) {
			if (!map.containsKey(cards[i].start)) {
				startIdx = i;
				break;
			}
		}
		
		map.clear();
		for (int i = 0; i < cards.length; i++) {
			map.put(cards[i].start, i);
		}
		
		String nextCity = cards[startIdx].end;
		path[0] = cards[startIdx];
		for (int i = 1; i < cards.length; i++) {
			Integer nextIdx = map.get(nextCity);
			path[i] = cards[nextIdx];
			nextCity = path[i].end;
			
		}
		
		return path;
		
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
