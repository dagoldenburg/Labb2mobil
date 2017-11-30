package com.example.micke.labb2mobil.Model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Micke on 11/26/2017.
 */

/*
 * The game board positions
 * 16           17           18
 *     08       09       10
 *         00   01   02
 * 23  15  07        03  11  19
 *         06   05   04
 *     14       13       12
 * 22           21           20
 */
public class GameState implements Serializable{

    private int[] gameBoard;
    private boolean whitePlayersTurn;
    private int whiteMarker,blackMarker,whiteMarkersOnBoard,blackMarkersOnBoard;
    private String gameName;

    public static final int EMPTY_SPACE = 0;
    public static final int WHITE_MARKER = 1;
    public static final int BLACK_MARKER = 2;

    private static GameState gameState;

    private GameState(int gameSize) {
        whitePlayersTurn = true;
        whiteMarker = gameSize;
        blackMarker = gameSize;
        if(gameSize==3){
            gameBoard = new int[8];
        }else if(gameSize==6){
            gameBoard = new int[16];
        }else gameBoard = new int[24];
        whiteMarkersOnBoard=0;
        blackMarkersOnBoard=0;
    }
    public static void setGameState(GameState game){
        gameState=game;
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void startNewGame(int gameSize){
        gameState = new GameState(gameSize);
    }

    public int checkIfWin(){
        if(blackMarker==0&&blackMarkersOnBoard<3)
            return 1;
        if(whiteMarker==0&&whiteMarkersOnBoard<3)
            return -1;
        return 0;
    }

    public boolean areThreeOnRow() {
        //if where gameboard has white marker
        //a marker has a neibour in either horiontal
        //or veritcal plane (horiz +1 -1, vertical +8-8)
        //return true else return false;

        //TODO: Gör så den inte låter samma rad vara giltig flera gånger
        for (int i = 0; i < gameBoard.length; i++) {
            if (isWhitePlayersTurn()) {
                if (gameBoard[i] == WHITE_MARKER) {
                    if (markerCheck(i,WHITE_MARKER)) {
                        Log.i("asd", "marker "+i+" ");
                        return true;
                    }
                }
            } else {
                if (gameBoard[i] == BLACK_MARKER) {
                    if (markerCheck(i,BLACK_MARKER)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean markerCheck(int i,int MARKER){
        if (isPossibleMove(i, i + 1,true) && gameBoard[i+1] == MARKER) {
            if (isPossibleMove(i, i - 1,true) && gameBoard[i-1] == MARKER) {
                return true;
            }
        }
        if (isPossibleMove(i, i + 8,true) && gameBoard[i+8] == MARKER) {
            if (isPossibleMove(i, i - 8,true) && gameBoard[i-8] == MARKER) {
                return true;
            }
        }
        if (isPossibleMove(i, i - 7,true) && gameBoard[i-7] == MARKER) {
            if (isPossibleMove(i, i - 1,true) && gameBoard[i-1] == MARKER) {
                return true;
            }
        }
        return false;
    }

    public boolean isPossibleMove(int from, int to,boolean ignoreIfMarker){
        try {
            if ((gameBoard[to] != EMPTY_SPACE) && (!ignoreIfMarker))
                return false;
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
        switch(from) {
            case 0:
                if (to == 1 || to == 7) return true;break;
            case 1:
                if (to == 0 || to == 2 || to == 9) return true;break;
            case 2:
                if (to == 1 || to == 3) return true;break;
            case 3:
                if (to == 2 || to == 4 || to == 11) return true;break;
            case 4:
                if (to == 3 || to == 5) return true;break;
            case 5:
                if (to == 4 || to == 6 || to == 13) return true;break;
            case 6:
                if (to == 5 || to == 7) return true;break;
            case 7:
                if (to == 6 || to == 0 || to == 15) return true;break;
            case 8:
                if (to == 15 || to == 9) return true;break;
            case 9:
                if (to == 8 || to == 10 || to == 17 || to == 1) return true;break;
            case 10:
                if (to == 9 || to == 11) return true;break;
            case 11:
                if (to == 3 || to == 10 || to == 19 || to == 12) return true;break;
            case 12:
                if (to == 11 || to == 13) return true;break;
            case 13:
                if (to == 5 || to == 21 || to == 12 || to == 14) return true;break;
            case 14:
                if (to == 13 || to == 15) return true;break;
            case 15:
                if (to == 23 || to == 7 || to == 8 || to == 14) return true;break;
            case 16:
                if (to == 17 || to == 23) return true;break;
            case 17:
                if (to == 16 || to == 9 || to == 18) return true;break;
            case 18:
                if (to == 17 || to == 19) return true;break;
            case 19:
                if (to == 18 || to == 20 || to == 11) return true;break;
            case 20:
                if (to == 19 || to == 21) return true;break;
            case 21:
                if (to == 13 || to == 20 || to == 22) return true;break;
            case 22:
                if (to == 21 || to == 23) return true;break;
            case 23:
                if (to == 22 || to == 15 || to == 16) return true;break;
    }

    return false;
}
    public boolean move(int from, int to){
        if(isWhitePlayersTurn()){
            boolean bajs = isPossibleMove(from,to,false);
            Log.i("bajs",bajs+"");
            if(bajs) {
                if (gameBoard[to] == EMPTY_SPACE &&
                        gameBoard[from] == WHITE_MARKER) {
                    gameBoard[to] = WHITE_MARKER;
                    gameBoard[from] = EMPTY_SPACE;
                    setWhitePlayersTurn(false);
                    return true;
                }
            }
        }else {
            if (isPossibleMove(from, to, false)) {
                if (gameBoard[to] == EMPTY_SPACE &&
                        gameBoard[from] == BLACK_MARKER) {
                    gameBoard[to] = BLACK_MARKER;
                    gameBoard[from] = EMPTY_SPACE;
                    setWhitePlayersTurn(true);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(int position){
        if(!areThreeOnRow()){
            return false;
        }
        if(isWhitePlayersTurn() && gameBoard[position]==BLACK_MARKER) {
            gameBoard[position] = EMPTY_SPACE;
            blackMarkersOnBoard -= 1;
            whitePlayersTurn=false;
            return true;
        }else if(!isWhitePlayersTurn() && gameBoard[position]==WHITE_MARKER){
            gameBoard[position]=EMPTY_SPACE;
            whitePlayersTurn=true;
            whiteMarkersOnBoard-=1;
            return true;
        }
        return false;
    }

    public boolean set(int position) {
        if (whitePlayersTurn) {
            //Kollar att det är setting phase
            if(whiteMarker<1){
                return false;
            }
            if (gameBoard[position] == EMPTY_SPACE) {
                gameBoard[position] = WHITE_MARKER;
                whitePlayersTurn = false;
                whiteMarker -= 1;
                whiteMarkersOnBoard+=1;
                return true;
            }
        } else {
                if(blackMarker<1){
                    return false;
                }
                if (gameBoard[position] == EMPTY_SPACE) {
                    gameBoard[position] = BLACK_MARKER;
                    whitePlayersTurn = true;
                    blackMarker -= 1;
                    blackMarkersOnBoard+=1;
                    return true;
                }
            }
            return false;
    }


    public boolean isWhitePlayersTurn() {
        return whitePlayersTurn;
    }

    public void setWhitePlayersTurn(boolean whitePlayersTurn) {
        this.whitePlayersTurn = whitePlayersTurn;
    }

    public int getWhiteMarker() {
        return whiteMarker;
    }

    public void setWhiteMarker(int whiteMarker) {
        this.whiteMarker = whiteMarker;
    }

    public int getBlackMarker() {
        return blackMarker;
    }

    public void setBlackMarker(int blackMarker) {
        this.blackMarker = blackMarker;
    }

    public int[] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[] gameBoard) {
        this.gameBoard = gameBoard;
    }

}
