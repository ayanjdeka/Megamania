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

public class AtariGame extends Application implements EventHandler<InputEvent> {

	GraphicsContext gc;
	Image trooper;
	private int x;
	AnimateObjects animate;
	Canvas canvas;

	public static void main(String[]args){
		launch();
	}

	public void start(Stage stage){
		//Loading images
		stage.setTitle("Final Project Title");
		Group root = new Group();
		canvas =  new Canvas(800,400);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		gc = canvas.getGraphicsContext2D();
		trooper = new Image( "trooper.jpg" );
		gc.drawImage( trooper,180,100 );

		//Moving Images (and handle method
		animate = new AnimateObjects();
		animate.start();
		stage.show();
		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

		//Audio
		URL resource = getClass().getResource("com/ayan/megamania/resources/test.wav");
		AudioClip clip = new AudioClip(resource.toString());
		clip.play();
	}

	//Moving images and input from user
	public void handle(final InputEvent event){
		if(event instanceof KeyEvent){
			if (((KeyEvent)event).getCode() == KeyCode.LEFT){
				x-=1;
			}else if(((KeyEvent)event).getCode() == KeyCode.RIGHT){
				x+=1;
			}
		}
		if(event instanceof MouseEvent){
			System.out.println(((MouseEvent)event).getX());
			System.out.println(((MouseEvent)event).getY());
		}
	}



	public class AnimateObjects extends AnimationTimer {
		public void handle(long now){
			//x+=1;

			//hit detection
			if(x>50){
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.drawImage(trooper,180+x,100);
				Rectangle2D rect1 = new Rectangle2D( 400,100,100,100 );
				gc.fillRect(400,100,100,100);
				Rectangle2D rect2 = new Rectangle2D( 180+x,100,trooper.getWidth(),trooper.getHeight() );
				if (rect1.intersects(rect2))
					System.out.println("HIT");
			}else{
				//Winning and restarting the game
				gc.setFill( Color.YELLOW); //Fills the text in yellow
				gc.setStroke( Color.BLACK ); //Changes the outline the black
				gc.setLineWidth(1); //How big the black lines will be
				Font font = Font.font( "Arial", FontWeight.NORMAL, 48 );
				gc.setFont( font );
				gc.fillText( "Game Over", 100, 50 ); //draws the yellow part of the text
				gc.strokeText( "Game Over", 100, 50 );
			}

		}


	}
}