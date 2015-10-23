import javax.swing.JApplet;

import java.util.Random;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Driver extends JApplet {
    Image board;
    Graphics boardGraphics;
    ChessBoard chessBoard;
    int key, x, y, saveX, saveY;
    boolean spaceKey, saved;
    ArrayList<Location> possibleMoves;
    ArrayList<Location> possibleAmbush;
    public void init() {
        setSize(400, 400);
        board = createImage(400, 400);
        chessBoard = new ChessBoard();
        boardGraphics = board.getGraphics();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                key = evt.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_UP:
                        if (y == 0)
                            y = 7;
                        else
                            y --;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (y == 7)
                            y = 0;
                        else
                            y ++;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (x == 0)
                            x = 7;
                        else
                            x --;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (x == 7)
                            x = 0;
                        else
                            x ++;
                        break;
                    case KeyEvent.VK_SPACE:
                        spaceKey = true;
                        break;
                }
            }
            public void keyReleased(KeyEvent evt) {
                key = evt.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_SPACE:
                        if (spaceKey) {
                            saved = !saved;
                            if (saved) {
                                saveX = x;
                                saveY = y;
                                Piece piece = chessBoard.getPiece(x, y);
                                if (piece == null)
                                    saved = false;
                                else {
                                    possibleMoves = chessBoard.getPiece(x, y).getPossibleMoves();
                                    possibleAmbush = chessBoard.getPiece(x, y).getPossibleAmbush(possibleMoves);
                                }
                            } else {
                                boolean test = false;
                                Location loc = new Location(x, y);
                                for(int i = 0; i < possibleMoves.size(); i++)
                                    if (possibleMoves.get(i).equals(loc)) {
                                        test = true;
                                        continue;
                                    }
                                if (!test) {
                                    for(int i = 0; i < possibleAmbush.size(); i++)
                                        if (possibleAmbush.get(i).equals(loc)) {
                                        test = true;
                                        continue;
                                    }
                                }
                                if (test)
                                    chessBoard.move(new Location(saveX, saveY), loc);
                            }
                            spaceKey = false;
                        }
                        break;
                }
            }
        });
    }
    public void paint(Graphics g) {
        drawBoard(boardGraphics);
        drawPieces(boardGraphics);
        if (saved) {
            drawSelector(boardGraphics, Color.yellow, saveX * 50, saveY * 50);
            for(int i = 0; i < possibleMoves.size(); i++)
                drawSelector(boardGraphics, Color.blue, possibleMoves.get(i));
            for(int i = 0; i < possibleAmbush.size(); i++)
                drawSelector(boardGraphics, Color.red, possibleAmbush.get(i));
        }
        drawSelector(boardGraphics, Color.green, x * 50, y * 50);
        g.drawImage(board, 0, 0, null);
        repaint();
    }
    public void drawPieces(Graphics g) {
        g.setColor(Color.red);
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) {
                Piece piece = chessBoard.getPiece(i, j);
                if (piece != null)
                    g.drawString(piece.toString(), i * 50, j * 50 + 10);
            }
    }
    public void drawBoard(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 400, 400);
        g.setColor(Color.black);
        for(int i = 0; i < 8; i += 2)
            for(int j = 0; j < 8; j += 2) {
                g.fillRect(i * 50, j * 50, 50, 50);
                g.fillRect(i * 50 + 50, j * 50 + 50, 50, 50);
            }
    }
    public void drawSelector(Graphics g, Color color, Location loc) {
        drawSelector(g, color, loc.getX() * 50, loc.getY() * 50);
    }
    public void drawSelector(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        Random rand = new Random();
        int[] polyX = new int[8];
        int[] polyY = new int[8];
        for(int i = 0; i < 4; i++) {
            polyX[0] = rand.nextInt(5) + x;
            polyY[0] = rand.nextInt(5) + y;
            polyX[1] = rand.nextInt(5) - 2 + x + 25;
            polyY[1] = rand.nextInt(5) + y;
            polyX[2] = rand.nextInt(5) - 5 + x + 50;
            polyY[2] = rand.nextInt(5) + y;
            polyX[3] = rand.nextInt(5) - 5 + x + 50;
            polyY[3] = rand.nextInt(5) - 2 + y + 25;
            polyX[4] = rand.nextInt(5) - 5 + x + 50;
            polyY[4] = rand.nextInt(5) - 5 + y + 50;
            polyX[5] = rand.nextInt(5) - 2 + x + 25;
            polyY[5] = rand.nextInt(5) - 5 + y + 50;
            polyX[6] = rand.nextInt(5) + x;
            polyY[6] = rand.nextInt(5) - 5 + y + 50;
            polyX[7] = rand.nextInt(5) + x;
            polyY[7] = rand.nextInt(5) - 2 + y + 25;
            g.drawPolygon(polyX, polyY, 8);
        }
    }
}