package com.ayan.megamania;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MegaMania extends Application implements EventHandler<InputEvent> {

	GraphicsContext gc;
	Image background;
	Group root = new Group();

	ArrayList<BadShip> badShips = new ArrayList<BadShip>();
	ArrayList<Laser> usedLasers = new ArrayList<Laser>();

	MegaManiaShip mmShip;
	Level currentLevel;
	private int badShipMoveCount  = 0;

	Image laser;
	Image badLaser;
	Image livesShip = new Image("com/ayan/megamania/resources/LivesShip.jpg");
	Image levelData;
	Image yellowBars;
	Image redBars;
	
	private int randomShipShooter = (int)(Math.random()*40)+1;

	private Laser mmLaser;
	private int scoreAddition = 20;

	private Text scoreText;
	private Text levelText;

	private boolean goodShipHit = false;
	private int goodShipDelayCount = 150;
	private int goodShipCount = goodShipDelayCount;
	private boolean nextLevel = false;
	private boolean endGame = false;


	AnimateObjects animate;
	Canvas canvas;

	public static void main(String[]args){
		launch();
	}

	public void start(Stage stage){
		//Loading Images
		stage.setTitle("MegaMania");

		canvas =  new Canvas(800,600);
		background = new Image("com/ayan/megamania/resources/background.jpg");



		//Set Up Information

		currentLevel = new Level(new Image("com/ayan/megamania/resources/BadShip.jpg"),1,3,2,10,1);
		mmShip = new MegaManiaShip(new Image ("com/ayan/megamania/resources/ship.jpg"),400,400,50,3,0);


		laser = new Image("com/ayan/megamania/resources/Laser.jpg");
		badLaser = new Image ("com/ayan/megamania/resources/BadLaser.jpg");
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		gc = canvas.getGraphicsContext2D();




		badShips = createBadShips();
		for(BadShip b : badShips){
			gc.drawImage(b.getImage(),b.getXPos(),b.getYPos());
		}


		levelData = new Image("com/ayan/megamania/resources/LevelBackground.jpg");
		yellowBars = new Image("com/ayan/megamania/resources/YellowBar.jpg");
		redBars = new Image("com/ayan/megamania/resources/RedBar.jpg");

		Text text = new Text(85, 468, "Energy");
		Font font = Font.font( "Arial", FontWeight.BOLD, 16 );
		text.setFont(font);
		text.setFill(Color.BLACK);
		root.getChildren().add(text);



		levelText = new Text(700, 468, "Level "+currentLevel.getLevelNumber());
		Font levelFont = Font.font( "Arial", FontWeight.BOLD, 16 );
		levelText.setFont(font);
		levelText.setFill(Color.BLACK);
		root.getChildren().add(levelText);

		Rectangle copyRightData = new Rectangle(0,580,800,25);
		copyRightData.setFill(Color.BLUE);
		copyRightData.setStroke(Color.BLUE);
		root.getChildren().add(copyRightData);


		Text copyRightText = new Text(230, 593.8, "AyanVision Technologies 2018(c)");
		Font copyRightFont = Font.font( "Arial", FontWeight.BOLD, 16 );
		copyRightText.setFont(copyRightFont);
		copyRightText.setFill(Color.WHITE);
		root.getChildren().add(copyRightText);


		addToScore(0);

		TimerTask task = new TimerTask(){
			public void run(){
				mmShip.setEnergyLevel(mmShip.getEnergyLevel()-1);
			}

		};
		Timer timer = new Timer();
		timer.schedule(task,300L,1000L);




		//Moving Images
		animate = new AnimateObjects();
		animate.start();
		stage.show();
		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	}



	public ArrayList<BadShip> createBadShips(){
		badShips = new ArrayList<BadShip>();

		if(currentLevel.getLevelNumber() == 1){
			int spaceBetweenShips = 160;

			BadShip b = null;
			for(int i=0;i<5;i++){
				if(b == null){
					b = new BadShip(currentLevel.getEnemyShip(),0,25,5);
				}else{
					b = new BadShip(currentLevel.getEnemyShip(),b.getXPos()+spaceBetweenShips,25,5);
				}
				badShips.add(b);

			}

			b = null;
			for(int i=0;i<5;i++){
				if(b == null){
					b = new BadShip(currentLevel.getEnemyShip(),80,100,5);
				}else{
					b = new BadShip(currentLevel.getEnemyShip(),b.getXPos()+spaceBetweenShips,100,5);
				}
				badShips.add(b);

			}

			b = null;
			for(int i=0;i<5;i++){
				if(b == null){
					b = new BadShip(currentLevel.getEnemyShip(),0,175,5);
				}else{
					b = new BadShip(currentLevel.getEnemyShip(),b.getXPos()+spaceBetweenShips,175,5);
				}
				badShips.add(b);

			}
		}

		if(currentLevel.getLevelNumber() == 2){
			int spaceBetweenShips = 160;

			BadShip b = null;
			for(int i=0;i<3;i++){
				if(b == null){
					b = new BadShip(currentLevel.getEnemyShip(),-100,-75,10);
				}else{
					b = new BadShip(currentLevel.getEnemyShip(),b.getXPos()+spaceBetweenShips,-75,10);
				}
				badShips.add(b);
			}

			b = null;
			for(int i=0;i<3;i++){
				if(b == null){
					b = new BadShip(currentLevel.getEnemyShip(),-100,0,10);
				}else{
					b = new BadShip(currentLevel.getEnemyShip(),b.getXPos()+spaceBetweenShips,0,10);
				}
				badShips.add(b);

			}

			b = null;
			for(int i=0;i<3;i++){
				if(b == null){
					b = new BadShip(currentLevel.getEnemyShip(),-100,75,10);
				}else{
					b = new BadShip(currentLevel.getEnemyShip(),b.getXPos()+spaceBetweenShips,75,10);
				}
				badShips.add(b);

			}
			b = null;
			for(int i=0;i<3;i++){
				if(b == null){
					b = new BadShip(currentLevel.getEnemyShip(),-100,150,10);
				}else{
					b = new BadShip(currentLevel.getEnemyShip(),b.getXPos()+spaceBetweenShips,150,10);
				}
				badShips.add(b);

			}

			b = null;
			for(int i=0;i<3;i++){
				if(b == null){
					b = new BadShip(currentLevel.getEnemyShip(),-100,225,10);
				}else{
					b = new BadShip(currentLevel.getEnemyShip(),b.getXPos()+spaceBetweenShips,225,10);
				}
				badShips.add(b);

			}
		}
		return badShips;
	}

	public void addToScore(int value){

		mmShip.setScore(mmShip.getScore() + value);
		if(scoreText!= null){
			root.getChildren().remove(scoreText);
		}
		scoreText = new Text(400, 550,""+ mmShip.getScore());
		Font scoreFont  = Font.font( "Arial", FontWeight.BOLD, 30 );
		scoreText.setFont(scoreFont);
		scoreText.setFill(Color.BLACK);
		root.getChildren().add(scoreText);
	}


	public void handle(final InputEvent event){
		if(event instanceof KeyEvent){
			handleLeftOrRight((KeyEvent) event);
			handleSpaceEvent((KeyEvent) event);
		}

		if(mmShip.getLives() < 0){
			if (((KeyEvent)event).getCode() == KeyCode.Y){
				currentLevel = new Level(new Image("com/ayan/megamania/resourcesBadShip.jpg"),1,3,2,10,1);
				mmShip = new MegaManiaShip(new Image("com/ayan/megamania/resources/ship.jpg"),400,400,50,3,0);
				addToScore(0);
				//lasers.clear();
				scoreAddition = 20;
				badShips = createBadShips();
				animate.start();
			}else if (((KeyEvent)event).getCode() == KeyCode.N ){
				System.exit(0);
			}
		}

		if(endGame){
			if (((KeyEvent)event).getCode() == KeyCode.ESCAPE ){
				System.exit(0);
			}
			if(((KeyEvent)event).getCode() == KeyCode.R){
				currentLevel = new Level(new Image("com/ayan/megamania/resources/BadShip.jpg"),1,3,2,10,1);
				mmShip = new MegaManiaShip(new Image ("com/ayan/megamania/resources/ship.jpg"),400,400,50,3,0);
				addToScore(0);
				scoreAddition = 20;
				badShips = createBadShips();
				animate.start();
				endGame = false;
			}
		}
	}

	public void handleLeftOrRight(final KeyEvent event){

		if(!goodShipHit){
			if (((KeyEvent)event).getCode() == KeyCode.LEFT){

				mmShip.setXPos(mmShip.getXPos()-25);
				if(mmShip.getXPos() < 0){
					mmShip.setXPos(0);
				}

			}
			if(((KeyEvent)event).getCode() == KeyCode.RIGHT){

				if(mmShip.getXPos()+25>=800){
				}else{
					mmShip.setXPos(mmShip.getXPos()+25);
				}
			}
		}


	}

	public void handleSpaceEvent(final KeyEvent event){
		if(((KeyEvent)event).getCode() == KeyCode.SPACE){
			if(mmLaser == null){
				URL resource = getClass().getResource("/com/ayan/megamania/resources/Sound.wav");
				AudioClip clip = new AudioClip(resource.toString());
				clip.play();
				mmLaser = new Laser (laser,mmShip.getXPos(),mmShip.getYPos());
			}
		}
	}

	public void drawEnergyBar(){
		int pos = 150;
		for(int i=0; i<50 ; i++){
			if(i<mmShip.getEnergyLevel()){
				gc.drawImage(yellowBars,pos,450);
			}else{
				gc.drawImage(redBars,pos,450);
			}
			pos+=10;

			if(mmShip.getEnergyLevel() <0){
				goodShipHit = true;
				goodShipCount = goodShipDelayCount;
				resetBadShips();				
			}
		}
	}

	public void resetOrEndGame(){

		animate.stop();

		gc.setFill( Color.YELLOW);
		gc.setStroke( Color.BLACK );
		gc.setLineWidth(1);
		Font font = Font.font( "Arial", FontWeight.BOLD, 30 );
		gc.setFont( font );
		gc.fillText( "Game Over", 350, 200 );
		gc.strokeText( "Game Over", 350, 200 );



		gc.setFill( Color.YELLOW);
		gc.setStroke( Color.BLACK );
		gc.setLineWidth(1);
		Font restartGame = Font.font( "Arial", FontWeight.BOLD, 24 );
		gc.setFont( restartGame );
		gc.fillText( "Do you want to restart the game? Y/N?", 200, 250 );
		gc.strokeText( "Do you want to restart the game? Y/N", 200, 250 );
	}

	public void resetBadShips(){
	 
		for(BadShip b:badShips){
			b.resetPosition();
			b.setRotationCount(0);
			usedLasers.remove(b.getLaser());
			b.setLaser(null);
		}

	}

	public void nextLevel(){
		addToScore(900 + scoreAddition);
		mmShip.resetEnergyLevel();
		currentLevel.setEnemyShip(new Image("com/ayan/megamania/resources/LevelTwoBadShip.jpg"));
		currentLevel.setLevelNumber(currentLevel.getLevelNumber()+1);
		currentLevel.setRotationCount(5);
		scoreAddition = 30;

		if(levelText!=null){
			root.getChildren().remove(levelText);
		}

		levelText = new Text(700, 468, "Level "+currentLevel.getLevelNumber());
		Font levelFont = Font.font( "Arial", FontWeight.BOLD, 16 );
		levelText.setFont(levelFont);
		levelText.setFill(Color.BLACK);
		root.getChildren().add(levelText);
		createBadShips();
		usedLasers.clear();
	}

	public void endGame(){
		gc.setFill( Color.YELLOW);
		gc.setStroke( Color.BLACK );
		gc.setLineWidth(1);
		Font endGame = Font.font( "Arial", FontWeight.BOLD, 24 );
		gc.setFont( endGame );
		gc.fillText( "Congradulations, you have won the game!", 200, 250 );
		gc.strokeText( "Congradulations, you have won the game!", 200, 250 );

		gc.setFill( Color.YELLOW);
		gc.setStroke( Color.BLACK );
		gc.setLineWidth(1);
		Font exitGame = Font.font( "Arial", FontWeight.BOLD, 24 );
		gc.setFont( exitGame );
		gc.fillText( "Press ESC to exit the game or R to restart", 200, 300 );
		gc.strokeText( "Press ESC to exit the game or R to restart", 200, 300 );
		animate.stop();
	}


	public class AnimateObjects extends AnimationTimer{

		public void handle(long now){

			if(goodShipHit && goodShipCount >  0){
				if(goodShipCount == goodShipDelayCount){
					URL resource = getClass().getResource("/com/ayan/megamania/resources/GoodShipGettingHit.wav");
					AudioClip laserHitSound = new AudioClip(resource.toString());
					laserHitSound.play();
				}
				if(goodShipCount % 2 == 0){
					Image deadShip = new Image ("com/ayan/megamania/resources/ShipDead.jpg");
					gc.drawImage(deadShip,mmShip.getXPos(),mmShip.getYPos()); 
				}else{
					gc.drawImage(mmShip.getImage(),mmShip.getXPos(),mmShip.getYPos()); 
				}
				goodShipCount--;

				//finish getting hit;				
				if(goodShipCount == 0){
					mmShip.setLives(mmShip.getLives()-1);
					if(mmShip.getLives() < 0){
						resetOrEndGame();
					}else{
						mmShip.resetEnergyLevel();	
					}					
					goodShipHit = false;
				}				
			}else{
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.drawImage(background,0,0);
				gc.drawImage(levelData,0,400+mmShip.getImage().getHeight());
				shipMovement(now);
				badShipMovement(now);
				laserMovement(now);
				badLaserMovement(now);
				setLives();
				drawEnergyBar();

				if(badShips.size() == 0){
					if( currentLevel.getLevelNumber() < 2){
						nextLevel();
						System.out.println("Level is "+currentLevel.getLevelNumber());
					}else if(currentLevel.getLevelNumber() == 2){
						System.out.println("You have won");
						endGame = true;
						endGame();
					}
				}

			}
		}

		public void shipMovement(long now){
			gc.drawImage(mmShip.getImage(),mmShip.getXPos(),mmShip.getYPos());
		}

		public void badShipMovement(long now){

			Rectangle2D goodShip = new Rectangle2D(mmShip.getXPos(),mmShip.getYPos(),mmShip.getImage().getWidth(),mmShip.getImage().getHeight());

			boolean hit = false;
			
			if(currentLevel.getLevelNumber() == 1){
				for(BadShip b : badShips){

					int x = b.getXPos() + b.getBadShipMovement()*b.getDirection();
					int y = b.getYPos();
					int rc = b.getRotationCount();

					
					if(badShips.size() > 2){
						randomShipShooter = (int)(Math.random()*badShips.size()*2)+1;	
					}else{
						randomShipShooter = (int)(Math.random()*badShips.size())+1;
					}
					
					
					int maxLaser = currentLevel.getMaxLaserCount();
					if(maxLaser > badShips.size()){
						maxLaser = badShips.size();
					}
					if(usedLasers.size() < maxLaser && b.getLaser() == null){							
						b.setLaser(new Laser(badLaser, b.getXPos()+10,b.getYPos()));
						usedLasers.add(b.getLaser());
					}

					if(x>canvas.getWidth()){
						x = 0;
						rc++;
						if( rc>=currentLevel.getRotationCount()){
							y += 25;
							rc=0;
						}
					}


					b.setXPos(x);
					b.setYPos(y);
					b.setRotationCount(rc);

					if(b.getRectangle2D().intersects(goodShip)){
						hit = true;

						goodShipHit = true;
						goodShipCount = goodShipDelayCount;
						animate.stop();
					}

					if(mmShip.getYPos()< b.getYPos()){
						resetBadShips();						
					}

					if((hit && mmShip.getLives()>=0) ){
						resetBadShips();

						animate.start();
					}
					gc.drawImage(b.getImage(),x,y);
				}
			}
			if(currentLevel.getLevelNumber() ==2 ){
				badShipMoveCount++;
				boolean hitBoundary = false;
				for(BadShip b : badShips){
					int x = b.getXPos() + b.getBadShipMovement()*b.getDirection();
					int y = b.getYPos();

					
					int maxLaser = currentLevel.getMaxLaserCount();
					if(maxLaser > badShips.size()){
						maxLaser = badShips.size();
					}
					
					if(badShips.size() > 2){
						randomShipShooter = (int)(Math.random()*badShips.size()*2)+1;	
					}else{
						randomShipShooter = (int)(Math.random()*badShips.size())+1;
					}
					
					if(usedLasers.size() < maxLaser && b.getLaser() == null){							
						b.setLaser(new Laser(badLaser, b.getXPos()+10,b.getYPos()));
						usedLasers.add(b.getLaser());
					}


					b.setXPos(x);

					if(b.getRectangle2D().intersects(goodShip)){
						hit = true;

						goodShipHit = true;
						goodShipCount = 200;
						//animate.stop();
					}

					if((hit && mmShip.getLives()>=0)){
						b.resetPosition();
						resetBadShips();

						//animate.start();
					}
					gc.drawImage(b.getImage(),x,y);
				}

				for(BadShip b : badShips){
					int x = b.getXPos() + b.getBadShipMovement()*b.getDirection();
					if(x >= 800 || x <-200){
						hitBoundary = true;
						break;
					}
					if(mmShip.getYPos()< b.getYPos()){
						resetBadShips();
					}
				}


				if(hitBoundary){
					for(BadShip b1 : badShips){
						b1.setDirection(b1.getDirection() * -1);
						b1.setXPos(b1.getXPos()+ b1.getBadShipMovement()*b1.getDirection());
						b1.setYPos(b1.getYPos()+25);
					}
				}


			}




		}

		public void setLives(){
			int lives = mmShip.getLives();

			int spaceBetween = 10;
			int currentPos = 350;

			for(int i =0;i<lives;i++){

				gc.drawImage(livesShip,currentPos,480);
				currentPos+=(spaceBetween+livesShip.getWidth());
			}


		}


		public void laserMovement(long now){
			if(mmLaser!=null){


				int x = mmLaser.getXPos();
				int y = mmLaser.getYPos()-10;
				mmLaser.setYPos(y);


				gc.drawImage(mmLaser.getImage(),x+mmShip.getImage().getWidth()/2,y);

				Rectangle2D laserRect = new Rectangle2D( x+15,y,mmLaser.getImage().getWidth(),mmLaser.getImage().getHeight() );

				boolean laserHit = false;
				for(BadShip b : badShips){
					if(b.getRectangle2D().intersects(laserRect)){
						URL resource = getClass().getResource("/com/ayan/megamania/resources/LaserHitSound.wav");
						AudioClip laserHitSound = new AudioClip(resource.toString());
						laserHitSound.play(); 
						usedLasers.remove(b.getLaser());
						b.setLaser(null);						
						badShips.remove(b);
						
						laserHit = true;
						addToScore(scoreAddition);
						break;

					}
				}

				if(y<=0  || laserHit){
					mmLaser = null;
				}

			}

		}


		public void badLaserMovement(long now){
 
			boolean hit = false;
			for(BadShip b : badShips){

				Laser las = b.getLaser();
				if(las != null){
					int y = las.getYPos()+5;
					las.setYPos(y);
					gc.drawImage(las.getImage(),las.getXPos(),las.getYPos());

					Rectangle2D laserRect = las.getRectangle2D();
					Rectangle2D goodShip = new Rectangle2D(mmShip.getXPos(),mmShip.getYPos(),mmShip.getImage().getWidth(),mmShip.getImage().getHeight() );


					if(laserRect.intersects(goodShip)){ 
						b.setLaser(null); 
						usedLasers.remove(las);
						hit = true;		
						goodShipHit = true;
						goodShipCount = goodShipDelayCount;
					}else if(las.getYPos()>=mmShip.getYPos()){
						usedLasers.remove(las);
						b.setLaser(null);						
					}
				}

			}			
			if(hit && mmShip.getLives()>=0){
				resetBadShips();  
			}


		}}

}