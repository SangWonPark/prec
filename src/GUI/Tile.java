package GUI;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile {
	private int num = 0;
	private int idxX = 0;
	private int idxY = 0;
	private int demensionX = 0;
	private int demensionY = 0;
	private ImageIcon ownImg = null;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getIdxX() {
		return idxX;
	}
	public void setIdxX(int idxX) {
		this.idxX = idxX;
	}
	public int getIdxY() {
		return idxY;
	}
	public void setIdxY(int idxY) {
		this.idxY = idxY;
	}
	public int getDemensionX() {
		return demensionX;
	}
	public void setDemensionX(int demensionX) {
		this.demensionX = demensionX;
	}
	public int getDemensionY() {
		return demensionY;
	}
	public void setDemensionY(int demensionY) {
		this.demensionY = demensionY;
	}
	public ImageIcon getOwnImg() {
		return ownImg;
	}
	public void setOwnImg(ImageIcon ownImg) {
		this.ownImg = ownImg;
	}
	
}
