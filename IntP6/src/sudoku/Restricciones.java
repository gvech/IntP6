package sudoku;

public class Restricciones {
	private int[][] board;
	
	public Restricciones(int [][]tablero){
		board=tablero;
	}
	
	public boolean restriccion(int i1, int j1, int i2, int j2){
		if(i1==i2 || j1==j2){
			if(board[i1][j1]==board[i2][j2]){
				return false;
			}
		}if(i1<3 && i2<3 && j1<3 && j2<3){
			if(board[i1][j1]==board[i2][j2]){
				return false;
			}
		}
		if(celda(i1,j1)==celda(i2,j2)){
			if(board[i1][j1]==board[i2][j2]){
				return false;
			}
		}
		return true;
	}
	
	public int celda(int i, int j){
		if(i<3 && j<3){
			return 0;
		}else if(i>2 &&  i<5 && j<3){
			return 1;
		}else if(i>5 && j<3){
			return 2;
		}
		if(i<3 && j>2 && j<5){
			return 3;
		}else if(i>2 &&  i<5 && j>2 && j<5){
			return 4;
		}else if(i>5 && j>2 && j<5){
			return 5;
		}
		if(i<3 && j>5){
			return 6;
		}else if(i>2 &&  i<5 && j>5){
			return 7;
		}else if(i>5 && j>5){
			return 8;
		}
		return -1;
		
	}
}
