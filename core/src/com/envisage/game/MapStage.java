package com.envisage.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.envisage.game.Side.blue;
import static com.envisage.game.Side.red;

public class MapStage extends Stage {

    final Envisage game;

    InputMultiplexer inputMultiplexer;

    MapData mapData;
    int[] cameraPosition;
    Vector3 cameraVector;

    Queue<Player> turnQueue = new Queue<>();

    Player currentPlayer;
    Player opponent;
    Player player1;
    Player player2;

    public MapStage(final Envisage game, Viewport viewport) {
        super(viewport);

        this.game = game;

        inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        inputMultiplexer.addProcessor(this);
        mapData = new MapData("map_data.txt");
        cameraPosition = new int[]{1, 0};

        player1 = new Player("Player 1", red);
        turnQueue.addLast(player1);
        player1.setSelectedUnit(new Unit(this, player1));
        spawnKnight(player1);
        
        player2 = new Player("Player 2", blue);
        turnQueue.addLast(player2);
        player2.setSelectedUnit(new Unit(this, player2));
        spawnKnight(player2);
        player2.setPrevCameraPosition(new int[]{1, 2});

        currentPlayer = player1;
        opponent = player2;
    }

    public void update() {
        handleInput();
        cameraVector = mapData.moveCamera(cameraPosition);
    }

    public void spawnKnight(Player player) {
        if (player == player1 && player.getBank() >= 10) {
            RedKnight redKnight = new RedKnight(this, player1);
            player1.addUnit(redKnight);
            this.addActor(redKnight);
        } else if (player.getBank() >= 10) {
            BlueKnight blueKnight = new BlueKnight(this, player2);
            player2.addUnit(blueKnight);
            this.addActor(blueKnight);
        }
    }

    public void assignResourceOwner() {
        for (Resource resource : mapData.getResources()) {
            int redUnitCount = 0;
            int blueUnitCount = 0;

            for (Vector2 tile : player1.getUnitPositions()) {
                if (resource.getControlTiles().contains(tile)) {
                    redUnitCount++;
                }
            }

            for (Vector2 tile : player2.getUnitPositions()) {
                if (resource.getControlTiles().contains(tile)) {
                    blueUnitCount++;
                }
            }

            if (redUnitCount > blueUnitCount) {
                player1.addResource(resource);
                player2.removeResource(resource);
            } else if (blueUnitCount > redUnitCount) {
                player2.addResource(resource);
                player1.removeResource(resource);
            }
        }
    }

    public void endTurn() {
        String name = turnQueue.removeFirst().getName();
        assignResourceOwner();
        if (name.equals("Player 1")) {
            player1.resetUnitMovement();
            player1.updateUnitPositions();
            currentPlayer = player2;
            opponent = player1;
            turnQueue.addLast(player1);
            player1.setPrevCameraPosition(cameraPosition);
            cameraPosition = player2.getPrevCameraPosition();
            player2.harvestResources();
        } else {
            player2.resetUnitMovement();
            player2.updateUnitPositions();
            currentPlayer = player1;
            opponent = player2;
            turnQueue.addLast(player2);
            player2.setPrevCameraPosition(cameraPosition);
            cameraPosition = player1.getPrevCameraPosition();
            player1.harvestResources();
        }
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && cameraPosition[0] != 2) {
            cameraPosition[0] += 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && cameraPosition[0] != 0) {
            cameraPosition[0] -= 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && cameraPosition[1] != 2) {
            cameraPosition[1] += 1;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && cameraPosition[1] != 0) {
            cameraPosition[1] -= 1;
        }

        Unit currentUnit = currentPlayer.getSelectedUnit();
        Vector2 pos = new Vector2(currentUnit.getX(), currentUnit.getY());

        if (currentUnit.getType() != Unit.UnitType.none) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                for (Unit unit : opponent.ownedUnits) {
                    if (pos.equals(new Vector2(unit.getX(), unit.getY() - 1)) && currentUnit.getMovesLeft() != 0) {
                        attack(currentUnit, unit);
                        break;
                    }
                }
                if (mapData.getTileFromGrid((int) pos.x, (int) pos.y + 1).getWalkable()) {
                    currentUnit.moveUp();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
                for (Unit unit : opponent.ownedUnits) {
                    if (pos.equals(new Vector2(unit.getX() + 1, unit.getY())) && currentUnit.getMovesLeft() != 0) {
                        attack(currentUnit, unit);
                        break;
                    }
                }
                if (mapData.getTileFromGrid((int) pos.x - 1, (int) pos.y).getWalkable()) {
                    currentUnit.moveLeft();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                for (Unit unit : opponent.ownedUnits) {
                    if (pos.equals(new Vector2(unit.getX(), unit.getY() + 1)) && currentUnit.getMovesLeft() != 0) {
                        attack(currentUnit, unit);
                        break;
                    }
                }
                if (mapData.getTileFromGrid((int) pos.x, (int) pos.y - 1).getWalkable()) {
                    currentUnit.moveDown();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                for (Unit unit : opponent.ownedUnits) {
                    if (pos.equals(new Vector2(unit.getX() - 1, unit.getY())) && currentUnit.getMovesLeft() != 0) {
                        attack(currentUnit, unit);
                        break;
                    }
                }
                if (mapData.getTileFromGrid((int) pos.x + 1, (int) pos.y).getWalkable()) {
                    currentUnit.moveRight();
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.close();
        }
    }

    public void attack(Unit one, Unit two) {
        two.loseHealth(50);
        one.loseHealth(25);
        one.setMovesLeft(0);
        if (one.getHealth() <= 0) {
            one.remove();
            one.getOwner().removeUnit(one);
        }
        if (two.getHealth() <= 0) {
            two.remove();
            two.getOwner().removeUnit(two);
        }
    }

    public Vector3 getCameraVector() {
        return this.cameraVector;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
