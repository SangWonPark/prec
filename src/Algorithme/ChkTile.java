package Algorithme;

import java.util.ArrayList;

public class ChkTile {

	private static ChkTile getChkTile = null;

	private ChkTile() {
	}

	public static ChkTile getInstance() {
		if (getChkTile == null) {
			getChkTile = new ChkTile();
		}
		return getChkTile;
	}

	private BlockSet getBlockSet = null;

	public int[][] checkTile(int[][] tile, int[] selIdx) {
		getBlockSet = BlockSet.getInstance();
		ArrayList<int[]> temp1 = checkRow(tile, selIdx);
		ArrayList<int[]> temp2 = checkCol(tile, selIdx);

		if (temp1.size() != 0) {
			tile = getBlockSet.removeTileRow(temp1, tile);
		}
		if (temp2.size() != 0) {
			tile = getBlockSet.removeTileCol(temp2, tile);
		}
		tile = chkZero(tile);
		return tile;
	}

	private int[][] chkZero(int[][] tile) {
		for (int i = 0; i < tile.length; i++) {
			int cntChk = 0;
			for (int j = 0; j < tile.length; j++) {
				if (tile[i][j] == 0) {
					cntChk++;
					if (cntChk > 3) {
						tile[i][j] = 6;
					}
				}
			}
		}
		for (int i = 0; i < tile.length; i++) {
			int cntChk = 0;
			for (int j = 0; j < tile.length; j++) {
				if (tile[j][i] == 0) {
					cntChk++;
					if (cntChk > 3) {
						tile[j][i] = 6;
					}
				}
			}
		}
		return tile;
	}

	public ArrayList<int[]> checkRow(int[][] tile, int[] selIdx) {
		int rs = 0;
		int re = tile.length - 1;
		int cs = 0;
		int ce = tile[0].length - 1;
		if (selIdx != null) {
			if (selIdx[1] != -1) {

				if (selIdx[0] > selIdx[2]) {

					rs = selIdx[2];
					re = selIdx[0];
				} else {

					rs = selIdx[0];
					re = selIdx[2];
				}
			}
		}
		ArrayList<int[]> Idx = new ArrayList<>();

		for (int i = rs; i <= re; i++) {// row

			int cnt = 0;
			int continued = -1;
			int[] rowIdx = new int[4];

			for (int j = cs; j <= ce; j++) {// col

				if (continued == -1) {

					rowIdx[0] = i;
					rowIdx[1] = j;
					continued = tile[i][j];

				} else if (continued == tile[i][j]) {

					cnt++;
					if (cnt >= 2) {
						rowIdx[2] = i;
						rowIdx[3] = j;
					}

				} else if (cnt < 2 | continued == tile[i][j]) {

					rowIdx[0] = i;
					rowIdx[1] = j;
					continued = tile[i][j];
					cnt = 0;
				}
			}
			if (rowIdx[3] != 0) {
				Idx.add(rowIdx);
			}

		}

		return Idx;

	}

	// 0,1번째 좌표 하나, 2,3번째 좌표 둘
	public ArrayList<int[]> checkCol(int[][] tile, int[] selIdx) {
		int rs = 0;
		int re = tile.length - 1;
		int cs = 0;
		int ce = tile[0].length - 1;
		if (selIdx != null) {
			if (selIdx[3] != -1) {

				if (selIdx[1] > selIdx[3]) {

					cs = selIdx[3];
					ce = selIdx[1];
				} else {

					cs = selIdx[1];
					ce = selIdx[3];
				}
			}
		}
		ArrayList<int[]> Idx = new ArrayList<>();

		for (int i = cs; i <= ce; i++) {// col

			int cnt = 0;
			int continued = -1;
			int[] colIdx = new int[4];

			for (int j = rs; j <= re; j++) {// row

				if (continued == -1) {

					colIdx[0] = j;
					colIdx[1] = i;
					continued = tile[j][i];

				} else if (continued == tile[j][i]) {

					cnt++;
					if (cnt >= 2) {
						colIdx[2] = j;
						colIdx[3] = i;
					}

				} else if (cnt < 2 | continued == tile[j][i]) {

					colIdx[0] = j;
					colIdx[1] = i;
					continued = tile[j][i];
					cnt = 0;
				}
			}

			if (colIdx[2] != 0) {
				Idx.add(colIdx);
			}

		}

		return Idx;

	}

	public boolean chkTile(int[][] ChktailInfo) {

		boolean chk = false;

		for (int i = 0; i < ChktailInfo.length; i++) {
			if (ChktailInfo[i][2] == 0 | ChktailInfo[2][i] == 0) {
				chk = true;
				return chk;
			}
		}
		return chk;
	}

	public int checkPoint(int[][] tile) {
		int point = 0;
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile.length; j++) {
				if (tile[i][j] == 0) {
					point += 100;
				}
			}
		}
		return point;
	}

	public int[][] finalchk(int[][] tile, int[] selIdx) {
		tile = checkTile(tile, selIdx);
		return tile;
	}

}
