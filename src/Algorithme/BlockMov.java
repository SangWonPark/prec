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
		
		tile[idx[0]][idx[1]] = tile[idx[2]][idx[3]]; // ù ���� �ڸ��� �ι�° ����Ÿ�� �ְ�
		tile[idx[2]][idx[3]] = temp; // �ι�° ���� �ڸ��� ù ����Ÿ�� �ְ�
		
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
