package GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import Algorithme.BlockMov;
import Algorithme.BlockSet;
import Algorithme.ChkTile;

public class InGame extends JFrame implements MouseMotionListener, MouseListener {

	private BlockSet anipung = BlockSet.getInstance();
	private BlockMov getBlockMov = BlockMov.getInstance();
	private ChkTile getChkTile = ChkTile.getInstance();

	private int[][] tailInfo = new int[5][5];

	int selIdx[] = new int[4];

	ImageIcon[] img = new ImageIcon[8];

	public static ArrayList<Tile> tileList = new ArrayList<>();

	TileP TilePanel = null;
	JPanel scoreP = new JPanel();

	Label scorel = null;
	JTextField scoreTF = new JTextField(10);

	String name;
	int point = 0;
	int[][] temptile;

	public InGame(String names) {

		super("anipung");
		name = names;
		this.setSize(265, 320);
		this.setLayout(new BorderLayout());

		setInitialVal();
		loadIcon();
		setImgTile();
		inti();

		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

	}

	private void setInitialVal() {

		selIdx[0] = -1;
		selIdx[1] = -1;
		selIdx[2] = -1;
		selIdx[3] = -1;

	}

	private void loadIcon() {
		for (int i = 0; i < img.length; i++) {

			img[i] = new ImageIcon("imgsource/" + i + ".png");

		}
	}

	private void setImgTile() {

		tailInfo = anipung.InitialtileSet(tailInfo, selIdx);

		for (int i = 0; i < tailInfo.length; i++) {
			for (int j = 0; j < tailInfo[i].length; j++) {

				Tile tile = new Tile();
				tile.setNum(tailInfo[i][j]);
				tile.setIdxX(j * 50);
				tile.setIdxY(i * 50);
				tile.setDemensionX(50);
				tile.setDemensionY(50);
				tile.setOwnImg(img[tailInfo[i][j]]);
				tileList.add(tile);

			}
		}
	}

	private void movtile() {
		int countIdx = 0;
		for (int i = 0; i < tailInfo.length; i++) {
			for (int j = 0; j < tailInfo[i].length; j++) {

				tileList.get(countIdx).setOwnImg(img[tailInfo[i][j]]);
				countIdx++;
			}
		}
		TilePanel.repaint();
	}

	private void inti() {// 4
		scoreTF.setEditable(false);
		scorel = new Label(name + "님의 점수");
		scoreP.add(scorel);
		scoreP.add(scoreTF);
		this.add(scoreP, "North");
		TilePanel = new TileP();
		TilePanel.addMouseListener(this);
		TilePanel.addMouseMotionListener(this);
		this.add(TilePanel, "Center");

	}

	private int[][] clon(int[][] tile) {
		int[][] tempTile = new int[5][5];
		for (int i = 0; i < tempTile.length; i++) {
			for (int j = 0; j < tempTile[i].length; j++) {
				tempTile[i][j] = tile[i][j];
			}
		}
		return tempTile;
	}

	private boolean idxSet(int xPoint, int yPoint) {

		for (int i = 0; i < tailInfo.length; i++) {
			if (xPoint < 50 + i * 50) {

				if (selIdx[1] == -1)
					selIdx[1] = i;
				else
					selIdx[3] = i;

				for (int j = 0; j < tailInfo[i].length; j++) {
					if (yPoint < 50 + j * 50) {

						if (selIdx[0] == -1)
							selIdx[0] = j;
						else {
							selIdx[2] = j;
							return true;
						}
						break;
					}
				}
				break;
			}
		}
		return false;
	}

	public void drawWait() {

//		chkTilePosition();
		movtile();
		this.paint(this.getGraphics());
		/*
		 * paint 강제 실행 repaint는 repaint간에 인터벌이 있어야 하는데 그게 없으면 마지막 repaint만 사용
		 * this.paint(this.getGraphics())는 paint를 강제로 실행
		 */
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void chkTilePosition() {
		for (int i = 0; i < tailInfo.length; i++) {
			for (int j = 0; j < tailInfo[i].length; j++) {
				System.out.print(tailInfo[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void tilemov() {

		point += getChkTile.checkPoint(tailInfo);
		scoreTF.setText(point + "");

		tailInfo = anipung.emptyTileSet(tailInfo);
		drawWait();

		tailInfo = getBlockMov.fallingBLK(tailInfo);
		drawWait();

		tailInfo = anipung.tileSet(tailInfo);
		drawWait();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int xPoint = e.getX();
		int yPoint = e.getY();
		boolean TF = idxSet(xPoint, yPoint);
		if (TF & Math.abs(selIdx[0] + selIdx[1] - (selIdx[2] + selIdx[3])) == 1
				& (selIdx[0] == selIdx[2] | selIdx[1] == selIdx[3])) {

			int[][] temptile = clon(tailInfo);
			int cnt = 0;
			tailInfo = getBlockMov.tileMove(tailInfo, selIdx);
			drawWait();
			tileChkLogic(temptile);
			
		} else if (TF) {
			
			setInitialVal();

		} else if (!TF & tailInfo[selIdx[0]][selIdx[1]] == 6) {
			
			tailInfo = anipung.selectBomb(tailInfo, selIdx);

			drawWait();
			tilemov();
			setInitialVal();
			tileChkLogic(tailInfo);
		}
	}
	public void tileChkLogic(int[][] temptile) {

		int cnt = 0;
		while (true) {
			tailInfo = getChkTile.checkTile(tailInfo, selIdx);
			drawWait();
			if (!getChkTile.chkTile(tailInfo)) {
				if (cnt == 0) {
					tailInfo = temptile;
				}
				drawWait();
				break;
			} else {
				cnt++;
				tilemov();
			}
			setInitialVal();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

}

class TileP extends JPanel {
	public void paint(Graphics g) {
		this.setSize(270, 290);
		for (int i = 0; i < InGame.tileList.size(); i++) {
			Tile tile = InGame.tileList.get(i);
			g.drawImage(tile.getOwnImg().getImage(), tile.getIdxX(), tile.getIdxY(), tile.getDemensionX(),
					tile.getDemensionY(), this);
		}
	}
}
