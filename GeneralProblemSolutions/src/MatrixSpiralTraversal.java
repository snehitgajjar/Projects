
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class MatrixSpiralTraversal {
	public static void main(String[] args) {

		int matrix[][]={
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}
		};


		printMatrix(matrix);

	}

	public static void printMatrix(int matrix[][]) {

		if(matrix.length==0) {
			return;
		}

		int left=0, right=matrix[0].length-1, up=0, down=matrix.length-1,i;

		while(true) {

			if(left>right) {
				break;
			}
			for(i=left;i<=right;++i){
				System.out.print(matrix[up][i]+" ");
			}
			++up;

			if(up>down) {
				break;
			}
			for(i=up;i<=down;++i){
				System.out.print(matrix[i][right]+" ");
			}
			--right;

			if(left>right) {
				break;
			}
			for(i=right;i>=left;--i){
				System.out.print(matrix[down][i]+" ");
			}
			--down;

			if(up>down) {
				break;
			}
			for(i=down;i>=up;--i){
				System.out.print(matrix[i][left]+" ");
			}
			++left;


		}



	}

}

