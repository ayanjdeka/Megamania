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

public class Level{

	private Image enemyShip;
	private int levelNumber;
	private int rotationDirection;
	private int rotationCount;
	private int maxLaserCount;
	private int energyReductionRate;
	private int scoreAddition;


	public Level(Image enemyShip, int rotationDirection, int rotationCount, int maxLaserCount, int energyReductionRate,int levelNumber){
		this.enemyShip = enemyShip;
		this.rotationDirection = rotationDirection;
		this.rotationCount = rotationCount;
		this.maxLaserCount = maxLaserCount;
		this.energyReductionRate = energyReductionRate;
		this.levelNumber = levelNumber;
		this.scoreAddition = scoreAddition;
	}

	public Image getEnemyShip(){
		return enemyShip;
	}

	public void setEnemyShip(Image image){
		enemyShip = image;
	}

	public int getRotationDirection(){
		return rotationDirection;
		}

	public void setRotationDirection(int direction){
		rotationDirection = direction;
	}

	public int getRotationCount(){
		return rotationCount;
	}

	public void setRotationCount(int count){
		rotationCount = count;
	}

	public int getMaxLaserCount(){
		return maxLaserCount;
		}

	public void setMaxLaserCount(int maxLaser){
		maxLaserCount = maxLaser;
	}

	public int getEnergyReductionRate(){
		return energyReductionRate;
	}

	public void setEnergyReductionRate(int reductionRate){
		energyReductionRate = reductionRate;
	}

	public int getLevelNumber(){
		return levelNumber;
	}

	public void setLevelNumber(int level){
		levelNumber = level;
	}



}



