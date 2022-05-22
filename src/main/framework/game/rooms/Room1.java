package main.framework.game.rooms;

import javafx.animation.Animation;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Rectangle2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.framework.animation.SpriteAnimator;
import main.framework.entities.Character;
import main.framework.entities.Item;
import main.framework.game.Game;
import main.framework.game.PlayerProperties;
import main.framework.object2D.Character2D;
import main.framework.controller.Controller;
import main.framework.controller.Mover;
import main.framework.object2D.GameObject2D;
import main.framework.object2D.Hotspot;
import main.framework.state.IState;
import main.framework.state.StateStack;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

import java.util.ArrayList;
import java.util.TimerTask;

import static main.framework.game.Game.root;

public class Room1 implements IState {

    private String gokulvl = "normale";

    private final Scene scene;
    private final GraphicsContext graphicsContext;
    private PerspectiveCamera perspectiveCamera;
    private Stage dialog;
    private VBox dialogVbox = new VBox(20);
    private Text playerHP = new Text();
    private Text scoreAndTime = new Text();
    private Scene dialogScene;
    private GridPane inventaire = new GridPane();

    private Timer timer = new Timer();
    private int minute = 0;
    private int seconde = 0;
    int invIndex = 0;

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (getSecondecounter()==59){
                setSecondecounter(0);
                setMinutecounter(getMinutecounter()+1);
            }
            setSecondecounter(getSecondecounter()+1);

            scoreAndTime.setText("Score : "+getScore()+" time : "+getMinutecounter()+" : "+getSecondecounter());
        }
    };

    private Character2D player;
    private ArrayList<GameObject2D> sideWalls;
    private ArrayList<GameObject2D> sideDoor;
    private ArrayList<GameObject2D> enemyObjectArray;
    private GameObject2D talker2D;
    private GameObject2D potion2D;
    private GameObject2D key2D;
    private GameObject2D weapon2D;
    private GameObject2D heart2D;

    private Hotspot talker;
    private Hotspot potion;
    private Hotspot key;

    private ArrayList<Hotspot> enemyHotspotArray;

    private int score = 0;

    private Hotspot weapon;
    private Hotspot heart;

    private Controller playerController;
    private Mover playerMover;
    private ImageView imageView;

    private Character enemyCharacter;
    private Character talkerCharacter;
    private Character playerCharacter;

    Image tileset;
    Image playerSprite;
    Image otherSprites;
    Image heartImage;

    Image enemyCombat;
    Image shieldImage;
    Image keyImage;
    Image weaponImage;

    Image gokuNormale;
    Image gokuSwordAndShield;
    Image gokuShield;
    Image gokuSword;

    SpriteAnimator animator;

    Item keyItem;
    Item weaponItem;
    Item potionItem;
    Item heartItem;

    LocalDateTime oldDate = LocalDateTime.now();
    int oldEnemyHitSeconds = oldDate.toLocalTime().toSecondOfDay();

    public Room1(Scene scene, GraphicsContext graphicsContext) {
        this.scene = scene;
        this.graphicsContext = graphicsContext;

    }

    @Override
    public void init(){

        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, 2048, 2048);

        perspectiveCamera = new PerspectiveCamera(true);
        perspectiveCamera.setTranslateZ(-1000);
        perspectiveCamera.setNearClip(0.1);
        perspectiveCamera.setFarClip(2000.0);
        perspectiveCamera.setFieldOfView(25);
        scene.setCamera(perspectiveCamera);

        graphicsContext.getCanvas().setHeight(1344);
        graphicsContext.getCanvas().setWidth(2101);


        player = new Character2D("player", 20, 20, 560, 560, 2);
        playerController = new Controller(scene);
        playerMover = new Mover(playerController, player);

        playerCharacter = new Character("player", 100, 10,1);

        sideWalls = new ArrayList<>();
        sideDoor = new ArrayList<>();
        enemyObjectArray = new ArrayList<>();
        enemyHotspotArray = new ArrayList<>();

        player.setName("goku");

        for(int x = 512; x <= 2080; x+= 32) {
            sideWalls.add(new GameObject2D("wall", 32, 32, x, 512));
        }
        for(int x = 512; x <= 2080; x+= 32) {
            sideWalls.add(new GameObject2D("wall", 32, 32, x, 1312));
        }
        for(int y = 512; y <= 1312; y+= 32) {
            sideWalls.add(new GameObject2D("wall", 32, 32, 512, y));
        }
        for(int y = 512; y <= 1312; y+= 32) {
            sideWalls.add(new GameObject2D("wall", 32, 32, 2080, y));
        }

        this.setMazeWalls();

        dialog = new Stage(StageStyle.UTILITY);
        dialog.initModality(Modality.NONE);

        dialogVbox.getChildren().add(scoreAndTime);
        dialogVbox.getChildren().add(playerHP);
        dialogVbox.getChildren().add(inventaire);

        dialogScene = new Scene(dialogVbox, 200, 110);
        dialog.setScene(dialogScene);
        dialog.setAlwaysOnTop(true);
        dialog.show();

        timer.schedule(timerTask, 1000,1000);

        dialog.setX(0);
        dialog.setY(0);

        try {
            playerSprite = new Image( new FileInputStream("src/main/framework/game/resources/sprites.png"));
            otherSprites = new Image(new FileInputStream("src/main/framework/game/resources/EntitySet.png"));
            tileset = new Image(new FileInputStream("src/main/framework/game/resources/tileset.png"));
            shieldImage = new Image(new FileInputStream("src/main/framework/game/resources/shield_plat.png"));
            weaponImage = new Image(new FileInputStream("src/main/framework/game/resources/sword_plat.png"));

            gokuNormale = new Image(new FileInputStream("src/main/framework/game/resources/goku.png"));
            gokuShield = new Image(new FileInputStream("src/main/framework/game/resources/goku_shield.png"));
            gokuSword = new Image(new FileInputStream("src/main/framework/game/resources/goku_sword.png"));
            gokuSwordAndShield = new Image(new FileInputStream("src/main/framework/game/resources/goku_sword_shield.png"));
            enemyCombat = new Image(new FileInputStream("src/main/framework/game/resources/enemygif.gif"));
            keyImage = new Image(new FileInputStream("src/main/framework/game/resources/key.png"));
            heartImage = new Image(new FileInputStream("src/main/framework/game/resources/heart.png"));

        }catch (Exception e){
            System.out.println(e.getCause());
        }

        if (gokulvl.equals("normale")){
            imageView = new ImageView(otherSprites);
            imageView.setViewport(new Rectangle2D(0, 32, 32, 32));
            root.getChildren().add(imageView);

            animator = new SpriteAnimator(imageView, Duration.millis(300), 3, 3, 0, 0, 32, 32);
            animator.setCycleCount(Animation.INDEFINITE);
        }
    }

    @Override
    public void onEnter() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, 2048, 2048);
        player = PlayerProperties.Player1.getCharacter2D();
        playerController = new Controller(scene);
        playerMover = new Mover(playerController, player);

        animator.play();
    }

    @Override
    public void update(long currentTime) {
        animator.update(this.playerMover, 0, 32, 64, 96);
        animator.updateView(playerMover);
        playerMover.update();
        perspectiveCamera.setTranslateY(player.getY());
        perspectiveCamera.setTranslateX(player.getX());

        if (potion!=null){
            if (potion.isCharacterOnHotspot() ){
                StateStack.push("potion");
                potion =null;

                if (player.getName().equals("gokuSword")){
                    gokuTransformation(gokuSwordAndShield);
                }else{
                    gokuTransformation(gokuShield);
                    gokulvl = "gokuShield";
                    player.setName("gokuShield");
                }
                score+=100;

                for (Hotspot hotspotEnemy: enemyHotspotArray) {
                    hotspotEnemy.getCharacter().setDamagePoints(5);
                }

                inventaire.add(new ImageView(shieldImage),invIndex,0);
                invIndex+=1;
                onExit();
            }
        }

        if (key!=null){
            if(key.isCharacterOnHotspot()){
                playerCharacter.addToInventory(keyItem);
                player.deleteCollision(sideDoor);
                StateStack.push("key");
                key=null;
                score+=100;
                inventaire.add(new ImageView(keyImage),invIndex,0);
                invIndex+=1;
                onExit();
            }
        }

        if (weapon!=null){
            if(weapon.isCharacterOnHotspot()){
                playerCharacter.addToInventory(weaponItem);
                StateStack.push("weapon");
                weapon=null;
                if (player.getName().equals("gokuShield")){
                    gokuTransformation(gokuSwordAndShield);
                }else{
                    gokuTransformation(gokuSword);
                    player.setName("gokuSword");
                }

                score+=100;
                inventaire.add(new ImageView(weaponImage),invIndex,0);
                invIndex+=1;
                onExit();
            }
        }


        if (talker.isCharacterOnHotspot()){
            scene.getCamera().setTranslateY(256);
            scene.getCamera().setTranslateX(256);
            timerTask.cancel();
            graphicsContext.setFill(Color.WHITE);
            graphicsContext.fillText("You won !",256,256,512);
            graphicsContext.fillText("Your score :  "+getScore(),56,356,512);
            graphicsContext.fillText("Your time :  "+getMinutecounter()+"."+getSecondecounter(),56,156,512);
            playerController.setDisabled(true);
        }

        if(playerController.getInputs().contains("ESCAPE")) {
            onExit();
            StateStack.push("gameMenu");
        }

        scoreAndTime.setText("score : "+this.score+"       time : "+getMinutecounter()+"."+getSecondecounter());
        playerHP.setText("HP : "+playerCharacter.getHealthPoints());
    }

    @Override
    public void draw() {
        for (int y = 512; y <= 1536; y += 32) {
            for (int x = 512; x <= 2101; x += 32) {
                graphicsContext.drawImage(tileset, 0, 0, 32, 32, x, y, 32, 32);
            }
        }

        for (GameObject2D wall : sideWalls) {
            graphicsContext.drawImage(tileset, 32, 0, 32, 32, wall.getX(), wall.getY(), wall.getHeight(), wall.getWidth());
        }

        for (GameObject2D door : sideDoor){
            graphicsContext.drawImage(tileset, 64, 0, 32, 32, door.getX(), door.getY(), door.getHeight(), door.getWidth());
        }

        graphicsContext.drawImage(playerSprite, 192, 224, 32, 32, talker2D.getX(), talker2D.getY(), talker2D.getWidth(), talker2D.getHeight());

        graphicsContext.setFill((talker.isCharacterOnHotspot()? new Color(1, 0, 0, 0.3) : new Color(1, 1, 0, 0.3)));
        graphicsContext.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());


        if (potion!=null){
            graphicsContext.drawImage(shieldImage, 0, 0, 32, 32, potion2D.getX(), potion2D.getY(), potion2D.getWidth(), potion2D.getHeight());
        }


        if (key!=null){
            graphicsContext.drawImage(keyImage, 0, 0, 32, 32, key2D.getX(), key2D.getY(), key2D.getWidth(), key2D.getHeight());
        }


        if (weapon!=null){
            graphicsContext.drawImage(weaponImage, 0, 0, 32, 32, weapon2D.getX(), weapon2D.getY(), weapon2D.getWidth(), weapon2D.getHeight());
        }


        if (heart!=null){
            graphicsContext.drawImage(heartImage, 0, 0, 32, 32, heart2D.getX(), heart2D.getY(), heart2D.getWidth(), heart2D.getHeight());
            if(heart.isCharacterOnHotspot()){
                playerCharacter.setHealthPoints(100);
                heart = null;
            }
        }

            for (int i = 0; i < enemyHotspotArray.size(); i++) {
                if (enemyHotspotArray.get(i) != null) {
                graphicsContext.drawImage(otherSprites, 128, 0, 32, 32, enemyHotspotArray.get(i).getX(), enemyHotspotArray.get(i).getY(), enemyHotspotArray.get(i).getWidth(), enemyHotspotArray.get(i).getHeight());
                if ( enemyHotspotArray.get(i).isCharacterOnHotspot()) {
                    graphicsContext.drawImage(enemyCombat, 0, 0, 32, 32, enemyHotspotArray.get(i).getX(), enemyHotspotArray.get(i).getY(), enemyHotspotArray.get(i).getWidth(), enemyHotspotArray.get(i).getHeight());

                    LocalDateTime date = LocalDateTime.now();
                    int secondsNow = date.toLocalTime().toSecondOfDay();

                    if (secondsNow >= oldEnemyHitSeconds + enemyHotspotArray.get(i).getCharacter().getAttackPeriod()) {
                        enemyHotspotArray.get(i).getCharacter().attack(playerCharacter);
                        oldEnemyHitSeconds = secondsNow;
                    }

                    if (playerCharacter.getInventory().contains(weaponItem)) {
                        if (playerController.getInputs().size() >= 1) {
                            if (playerController.getInputs().get(playerController.getInputs().size() - 1).equals("SPACE")) {
                                playerController.getInputs().clear();
                                playerCharacter.attack(enemyHotspotArray.get(i).getCharacter());
                                oldEnemyHitSeconds = secondsNow;
                            }
                        }
                    }

                    if (!enemyHotspotArray.get(i).getCharacter().isAlive()) {
                        player.deleteCollision(enemyObjectArray.get(i));
                        enemyHotspotArray.set(i,null);
                        score+=100;
                    }

                    if (!playerCharacter.isAlive()) {
                        StateStack.push("combat");
                        onExit();
                    }
                }
            }
        }
    }


    @Override
    public void onExit() {
        scene.setOnKeyPressed(null);
        scene.setOnKeyReleased(null);
        animator.stop();

        PlayerProperties.Player1.setCharacter2D(player);
    }

    @Override
    public void onClose() {
        imageView.setImage(null);
        animator = null;
        dialog.hide();
    }

    public void setMazeWalls(){
        File file = new File("src/main/framework/game/rooms/mazeWall");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            String[] tab;
            int x = 544;
            int y = 544;
            int indexArray = 0;
            while ((st = br.readLine()) != null){
                tab = st.split("");
                for (int i = 0; i < tab.length; i++) {
                    if (tab[i].equals("1")){
                        sideWalls.add(new GameObject2D("wall", 32, 32, x, y));
                    }

                    switch(tab[i]){
                        case "1":
                            sideWalls.add(new GameObject2D("wall", 32, 32, x, y));
                            break;
                        case "2":
                            enemyCharacter = new Character("enemy", 32, 32 , x, y, 100, 10, 1);

                            enemyHotspotArray.add(enemyCharacter.createHotSpot());
                            enemyObjectArray.add(enemyCharacter.createGameObject2D());

                            enemyHotspotArray.get(indexArray).addTriggerCharacter(player);
                            enemyHotspotArray.get(indexArray).setCharacter(enemyCharacter);
                            player.addCollision(enemyObjectArray.get(indexArray));

                            indexArray +=1;

                            break;
                        case "3":
                            talkerCharacter = new Character("enemy", 32, 32 , x, y);
                            talker = talkerCharacter.createHotSpot();
                            talker2D = talkerCharacter.createGameObject2D();

                            talker.addTriggerCharacter(player);
                            player.addCollision(talker2D);

                            break;
                        case"4":
                            potionItem = new Item("potion", 32, 32, x, y);
                            potion2D = potionItem.createGameObject2D();
                            potion = potionItem.createHotSpot();

                            potion.addTriggerCharacter(player);

                            break;
                        case"5":
                            keyItem = new Item("key", 32, 32, x, y);
                            key2D = keyItem.createGameObject2D();
                            key = keyItem.createHotSpot();

                            key.addTriggerCharacter(player);

                            break;
                        case"6":
                            sideDoor.add(new GameObject2D("door", 32, 32, x, y));
                            break;
                        case"7":
                            weaponItem = new Item("weapon", 32, 32, x, y);
                            weapon2D = weaponItem.createGameObject2D();
                            weapon = weaponItem.createHotSpot();

                            weapon.addTriggerCharacter(player);

                            break;
                        case"8":
                            heartItem = new Item("heart", 32, 32, x, y);
                            heart2D = heartItem.createGameObject2D();
                            heart = heartItem.createHotSpot();

                            heart.addTriggerCharacter(player);

                            break;
                    }
                    x+=32;
                }

                x=544;
                y+=32;
            }
            player.addCollision(sideWalls);
            player.addCollision(sideDoor);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void gokuTransformation(Image GokuImage){

        imageView.setImage(null);

        imageView = new ImageView(GokuImage);
        imageView.setViewport(new Rectangle2D(32, 32, 32, 32));
        root.getChildren().add(imageView);

        animator = new SpriteAnimator(imageView, Duration.millis(300), 3, 3, 0, 0, 32, 32);
    }



    public int getSecondecounter() {
        return seconde;
    }

    public void setSecondecounter(int seconde) {
        this.seconde = seconde;
    }

    public int getMinutecounter() {
        return minute;
    }

    public void setMinutecounter(int minute) {
        this.minute = minute;
    }

    public int getScore() {
        return score;
    }
}