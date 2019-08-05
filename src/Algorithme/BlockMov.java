package Algorithme;

public class BlockMov {

	private static BlockMov getBlockMov = null;
	private BlockMov() {}
	
	public static BlockMov getInstance() {
		if (getBlockMov == null) {
			getBlockMov = new BlockMov();
		}
		return getBlockMov;
	}
	
	BlockSet getBlockSet = null;
	
	public int[][] tileMove(int[][] tile, int[] idx) {
		int temp = tile[idx[0]][idx[1]];
		
		tile[idx[0]][idx[1]] = tile[idx[2]][idx[3]]; // 첫 셀렉 자리에 두번째 셀렉타일 넣고
		tile[idx[2]][idx[3]] = temp; // 두번째 셀렉 자리에 첫 셀렉타일 넣고
		
		return tile;
	}
	
	public int[][] fallingBLK(int[][] tile){
		for (int i = 4 ; i >= 0; i--) {
			for (int k = 0; k < tile.length-1 ; k++) {
				for (int j = 4; j > 0; j--) {
					if (tile[j][i]==7) {
						int temp = tile[j-1][i];
						tile[j][i]=temp;
						tile[j-1][i] = 7;
					}
				}
			}
		}
		return tile;
	}
}
