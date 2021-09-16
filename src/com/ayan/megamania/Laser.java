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


public class Laser{

	private Image image;
	private int xPosition;
	private int yPosition;
	private boolean shootingLaser  = false;

	public Laser (Image image,int xPosition, int yPosition){
		this.image  = image;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
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















}

