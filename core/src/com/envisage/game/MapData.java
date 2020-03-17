package com.envisage.game;

import com.badlogic.gdx.math.Vector3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class MapData {
    Tile[][] tileGrid;
    Vector3[][] cameraPositionGrid;
    int[] cameraPosition;

    public MapData(String gridFile) {
        this.tileGrid = setTileGrid(gridFile);
        setCameraPositionGrid();
        cameraPosition = new int[]{0, 1};
    }

    public Tile[][] setTileGrid(String file) {
        try {
            File gridFile = new File(file);
            Scanner sc = new Scanner(new BufferedReader(new FileReader(gridFile)));
            int rows = 49, columns = 58;
            Tile[][] gridData = new Tile[rows][columns];
            while (sc.hasNextLine()) {
                for (int i = 0; i < gridData.length; i++) {
                    String line = sc.nextLine().trim();
                    for (int j = 0; j < line.length(); j++) {
                        char tile = line.charAt(j);
                        switch (tile) {
                            case ('P'):
                                gridData[i][j] = new Tile(TileType.path, true, 0);
                                break;
                            case ('W'):
                                gridData[i][j] = new Tile(TileType.water, false, 0);
                                break;
                            case ('B'):
                                gridData[i][j] = new Tile(TileType.bridge, true, 0);
                                break;
                            case ('S'):
                                gridData[i][j] = new Tile(TileType.spawn, false, 0);
                                break;
                            case ('G'):
                                gridData[i][j] = new Tile(TileType.gold, false, 1);
                                break;
                            case ('A'):
                                gridData[i][j] = new Tile(TileType.ardite, false, 2);
                                break;
                            case ('D'):
                                gridData[i][j] = new Tile(TileType.delphite, false, 3);
                                break;
                            case ('K'):
                                gridData[i][j] = new Tile(TileType.keorite, false, 4);
                                break;
                            default:
                                break;
                        }
                    }
                }
                return gridData;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void setCameraPositionGrid() {
        this.cameraPositionGrid = new Vector3[][]{{new Vector3(10, 11, 0), new Vector3(29, 11, 0), new Vector3(47, 11, 0)},
                {new Vector3(10, 24, 0), new Vector3(29, 24, 0), new Vector3(47, 24, 0)},
                {new Vector3(10, 38, 0), new Vector3(29, 38, 0), new Vector3(47, 38, 0)}};
    }

    public Vector3 moveCamera(int[] cameraPosition) {
        return cameraPositionGrid[cameraPosition[0]][cameraPosition[1]];
    }
}
