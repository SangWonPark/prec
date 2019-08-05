package Algorithme;

import java.util.ArrayList;
import java.util.Random;

public class BlockSet {

	private static BlockSet getBlockSet = null;

	private BlockSet() {
	}

	public static BlockSet getInstance() {
		if (getBlockSet == null) {
			getBlockSet = new BlockSet();
		}
		return getBlockSet;
	}

	private BlockMov getBlockMov = null;
	private ChkTile getChkTile = null;

	private Random r = new Random();

	public int[][] InitialtileSet(int[][] tile, int[] selIdx) {
		getChkTile = ChkTile.getInstance();
		boolean TF = true;
		while (TF) {
			for (int i = 0; i < tile.length; i++) {
				for (int j = 0; j < tile[i].length; j++) {
					if (tile[i][j] == 0) {
						tile[i][j] = r.nextInt(5) + 1;
					}
				}
			}
			getChkTile.checkTile(tile, selIdx);
			TF = getChkTile.chkTile(tile);
		}
		return tile;
	}

	public int[][] removeTileRow(ArrayList<int[]> idxList, int[][] tile) {
		
		for (int j = 0; j < idxList.size(); j++) {
			int[] idxinfo = idxList.get(j);
			for (int j2 = idxinfo[1]; j2 <= idxinfo[3]; j2++) {
				tile[idxinfo[0]][j2] = 0;
			}
		}

		return tile;
	}

	public int[][] removeTileCol(ArrayList<int[]> idxList, int[][] tile) {

		for (int j = 0; j < idxList.size(); j++) {
			int[] idxinfo = idxList.get(j);
			for (int j2 = idxinfo[0]; j2 <= idxinfo[2]; j2++) {
				tile[j2][idxinfo[1]] = 0;
			}
		}

		return tile;
	}

	public int[][] tileSet(int[][] tile) {
		getBlockMov = BlockMov.getInstance();

		tile = getBlockMov.fallingBLK(tile);

		for (int i = 0; i < tile.length; i++) {
			int cntChk = 0;
			for (int j = 0; j < tile[i].length; j++) {
				if (tile[i][j] == 7) {
					cntChk++;
					if (cntChk > 3) {
						tile[i][j] = 6;
					} else {
						tile[i][j] = r.nextInt(5) + 1;
					}
				}
			}
		}

		return tile;
	}

	public int[][] emptyTileSet(int[][] tile) {
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				if (tile[i][j] == 0) {
					tile[i][j] = 7;
				}
			}
		}
		return tile;
	}

	public int[][] selectBomb(int[][] tile, int[] selIdx) {
		int xS = selIdx[0] - 1;
		int xE = selIdx[0] + 1;
		int yS = selIdx[1] - 1;
		int yE = selIdx[1] + 1;
		if (selIdx[0] == 0 || selIdx[0] == 4) {
			if (selIdx[0] == 0) {
				xS = 0;
			}else {
				xE = 4;
			}
		}
		if (selIdx[1] == 0 || selIdx[1] == 4) {
			if (selIdx[1] == 0) {
				yS = 0;
			}else {
				yE = 4;
			}
		}
		for (int i = xS; i <= xE; i++) {
			for (int j = yS; j <= yE; j++) {
				tile[i][j] = 0;
			}
		}

		return tile;
	}

}
