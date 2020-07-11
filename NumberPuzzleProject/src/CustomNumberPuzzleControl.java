import java.awt.*;
import java.util.Arrays; 

class CustomNumberPuzzleControl extends NumberPuzzleControl {
	public int getWidth() {
		return 200;
	}
	public int getHeight() {
		return 250;
	}
	public int getXPosition() {
		return 200;
	}
	public int getYPosition() {
		return 200;
	}
	public String getTitle(){
		return "Number Puzzle";
	}
	public int getShuffleButtonFontSize() {
		return 12;
	}
	public int getNumbersFontSize() {
		return 12;
	}
	public Color getEmptyButtonColor() {
		return Color.WHITE;
	}
	public String getWinnerMessage() {
		return "Congrats, you have won!";
	}

	// The following three methods have to be written by the participants...

	public int handleButtonClicked(NumberPuzzleGame game){
		
		int emptyCellId = game.getEmptyCellId();
		Button buttonClicked = game.getButtonClicked();
		Button[] buttons = game.getButtons();
		//Now we need the CellId of the button clicked
		int clickCellId = 0;
		for (int i = 0;i <16; i++) { //We traverse the buttons array to find at which index is the clicked button
			if (buttons[i].getLabel()==buttonClicked.getLabel())
				clickCellId = i;
		}
		
		//Now we calculate the row and column for empty and clicked cell
		int clickRow = clickCellId / 4, clickCol = clickCellId % 4;
		int emptyRow = emptyCellId / 4, emptyCol = emptyCellId % 4;
		int rowDiff = Math.abs(clickRow-emptyRow);
		int colDiff = Math.abs(clickCol-emptyCol);
		
		//If the clicked cell is not adjacent or is on diagonal to empty cell then do nothing and return older emptycell value
		if (rowDiff > 1 || colDiff > 1 || (rowDiff > 0 && colDiff > 0)) 
			return emptyCellId;
		
		//Else if
		swapButton(buttons[emptyCellId],buttonClicked);
		System.out.println();
		emptyCellId = clickCellId;
		return clickCellId;

	}
	public int[] getRandomNumbersForGrid() {
		int arr[] = new int[15];
		boolean flag = false;
		
		for(int i = 0; i < 15;) { //We need to generate 15 new numbers
			flag = false;
			int a = getRandomNumber(); //This function generates numbers from 1-100
			a = (14 * a /99) + 1; //This will limit the generated number to 1-15
			for (int index = i;index >=0;index--) { 
				if (arr[index] == a) { //Checking if the generated number is there in the array earlier or not
					flag = true; 
			        break; 
				}
			}
			if (flag) continue; //If number already there proceed again without incrementing
			arr[i++] = a; //Else increment and update array
		}
				
		return arr;
	}
	
	public boolean checkForWinner(Button[] buttons)
	{
		for (int i = 0;i <15;i++)//We traverse the buttons array to find at which index is the clicked button
			if(buttons[i].getLabel()=="  " || Integer.parseInt(buttons[i].getLabel())!=i+1) return false;
		return true;
	}
}