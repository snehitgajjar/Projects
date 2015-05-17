import java.util.Stack;


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class BlackCardPlacement {
	public static void main(String[] args) {

		int[] temp=placeCards(7,8,5);

		Stack s=new Stack();


		for (int element : temp) {

			System.out.print(element);
		}


	}


	public static int[] placeCards(int black_cards,int red_cards,int k) {

		int[] deckOfCards = new int[black_cards + red_cards];

		int index=-1;

		for(int i=0;i<red_cards;i++) {

			for(int j=0;j<k;) {

				index++;

				if(index==deckOfCards.length) {

					index=0;

				}

				if(deckOfCards[index]!=1) {

					j++;

				}


			}
			deckOfCards[index]=1;

		}


		return deckOfCards;
	}


}