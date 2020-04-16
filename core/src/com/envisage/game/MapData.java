package com.envisage.game;

import com.badlogic.gdx.math.Vector3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

import static com.envisage.game.TileType.*;

public class MapData {

    Tile[][] tileGrid;
    Vector3[][] cameraPositionGrid;
    int[] cameraPosition;
    HashSet<Resource> resources;

    public MapData(String gridFile) {
        resources = new HashSet<>();
        setTileGrid(gridFile);
        setCameraPositionGrid();
        cameraPosition = new int[]{0, 1};
    }

    public void setTileGrid(String file) {
        try {
            File gridFile = new File(file);
            Scanner sc = new Scanner(new BufferedReader(new FileReader(gridFile)));
            int rows = 49, columns = 58;
            Tile[][] gridData = new Tile[columns][rows];
            while (sc.hasNextLine()) {
                for (int i = 0; i < gridData[0].length; i++) {
                    String line = sc.nextLine().trim();
                    int y = 48 - i;
                    for (int j = 0; j < line.length(); j++) {
                        char tile = line.charAt(j);
                        switch (tile) {
                            case ('P'):
                                gridData[j][y] = new Tile(j, y, path, true);
                                break;
                            case ('W'):
                                gridData[j][y] = new Tile(j, y, water, false);
                                break;
                            case ('B'):
                                gridData[j][y] = new Tile(j, y, bridge, true);
                                break;
                            case ('S'):
                                gridData[j][y] = new Tile(j, y, spawn, false);
                                break;
                            case ('G'):
                                gridData[j][y] = new Resource(j, y, gold, false, 1);
                                resources.add((Resource) gridData[j][y]);
                                break;
                            case ('A'):
                                gridData[j][y] = new Resource(j, y, ardite, false, 2);
                                resources.add((Resource) gridData[j][y]);
                                break;
                            case ('D'):
                                gridData[j][y] = new Resource(j, y, delphite, false, 3);
                                resources.add((Resource) gridData[j][y]);
                                break;
                            case ('K'):
                                gridData[j][y] = new Resource(j, y, keorite, false, 4);
                                resources.add((Resource) gridData[j][y]);
                                break;
                            default:
                                break;
                        }
                    }
                }
                this.tileGrid = gridData;
            }
        } catch (Exception ignored) {
        }
    }

    public void setCameraPositionGrid() {
        this.cameraPositionGrid = new Vector3[][]{{new Vector3(11, 11, 0), new Vector3(29, 11, 0), new Vector3(47, 11, 0)},
                {new Vector3(11, 24, 0), new Vector3(29, 24, 0), new Vector3(47, 24, 0)},
                {new Vector3(11, 38, 0), new Vector3(29, 38, 0), new Vector3(47, 38, 0)}};
    }

    public Tile getTileFromGrid(int x, int y) {
        return this.tileGrid[x][y];
    }

    public Vector3 moveCamera(int[] cameraPosition) {
        return cameraPositionGrid[cameraPosition[0]][cameraPosition[1]];
    }

    public HashSet<Resource> getResources() {
        return this.resources;
    }

}
