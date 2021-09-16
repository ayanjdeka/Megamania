package com.ayan.megamania;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;


public class BadShip{

	private Image image;
	private int xPosition;
	private int yPosition;
	private int initialXPosition;
	private int initialYPosition;
	private int rotationCount;
	private int badShipDirection = 1;
	private int badShipMovement;
	
	private Laser laser;


	public BadShip (Image image,int xPosition, int yPosition, int badShipMovement){
		this.image  = image;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.initialYPosition = yPosition;
		this.initialXPosition = xPosition;
		this.badShipMovement = badShipMovement;
	}

	public int getXPos(){
		return xPosition;
	}

	public void setXPos(int x){
		xPosition = x;
	}

	public int getYPos(){
		return yPosition;
	}

	public void setYPos(int y){
		yPosition = y;
	}

	public Rectangle2D getRectangle2D(){
		Rectangle2D rectInfo = new Rectangle2D (xPosition,yPosition, image.getWidth(),image.getHeight());
		return rectInfo;
	}

	public Image getImage(){
		return image;
	}

	public void setImage(Image i){
		image = i;
	}

	public int getRotationCount(){
		return rotationCount;
	}

	public void setRotationCount(int rotation){
		rotationCount = rotation;
	}

	public void resetPosition (){
		xPosition = initialXPosition;
		yPosition = initialYPosition;
	}

	public int getBadShipMovement(){
		return badShipMovement;
	}

	public int getDirection(){
		return badShipDirection;
	}

	public void setDirection(int direction){
		badShipDirection = direction;
	}

	public int getIntitialXPosition(){
		return initialXPosition;
	}

	public Laser getLaser() {
		return laser;
	}

	public void setLaser(Laser laser) {
		this.laser = laser;
	}


 


}

