package Game.functionality;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Board {
    int size;
    int unit;
    Pane root;
    Shape [][] map;
    Vector2d chosenPose;
    public static int chosenX;
    public static int chosenY;
    public Board(int size,Pane root,int attempts)
    {
       this.size = size;
       this.root = root;
       this.map = new Shape[size][size];
       this.unit = (int) this.root.getWidth()/size;
    }
    public void initialize(int numberOfShapes)
    {

        this.root.getChildren().clear();
        int counter = 0;
        boolean [] usedColors = new boolean[10];
        Random generator = new Random();
        boolean shape = generator.nextBoolean(); // true = kwadrat false = koło
        while (counter < numberOfShapes/2)
        {
            shape = generator.nextBoolean();
            Shape newShape;
            int x1 = generator.nextInt(this.size);
            int y1 = generator.nextInt(this.size);
            int x2 = generator.nextInt(this.size);
            int y2 = generator.nextInt(this.size);
            if (!isOccupied(new Vector2d(x1, y1)) && !isOccupied(new Vector2d(x2, y2)))
            {
                if (shape)
                {
                    Circle newCircle1 = new Circle();
                    newCircle1.setRadius((int) this.unit/2);
                    newCircle1.setCenterX(x1*unit+0.5*unit);
                    newCircle1.setCenterY(y1*unit+0.5*unit);
                    newCircle1.setStroke(Color.BLACK);
                    Circle newCircle2 = new Circle();
                    newCircle2.setRadius((int) this.unit/2);
                    newCircle2.setCenterX(x2*unit+0.5*unit);
                    newCircle2.setCenterY(y2*unit+0.5*unit);
                    newCircle2.setStroke(Color.BLACK);
                    int pickedColor = generator.nextInt(7);
                    newCircle1.setFill(RandomColorGiver.GiveColor(pickedColor));
                    newCircle2.setFill(RandomColorGiver.GiveColor(pickedColor));
                    usedColors[pickedColor] = true;
                    this.map[x1][y1] = newCircle1;
                    this.map[x2][y2] = newCircle2;

                }
                else
                {
                    Rectangle newRectangle1 = new Rectangle();
                    newRectangle1.setHeight(this.unit);
                    newRectangle1.setWidth(this.unit);
                    newRectangle1.setX(x1*unit);
                    newRectangle1.setY(y1*unit);
                    Rectangle newRectangle2 = new Rectangle();
                    newRectangle2.setHeight(this.unit);
                    newRectangle2.setWidth(this.unit);
                    newRectangle2.setX(x2*unit);
                    newRectangle2.setY(y2*unit);
                    int pickedColor;
                    pickedColor = generator.nextInt(7);
                    newRectangle1.setFill(RandomColorGiver.GiveColor(pickedColor));
                    newRectangle2.setFill(RandomColorGiver.GiveColor(pickedColor));
                    newRectangle1.setStroke(Color.BLACK);
                    newRectangle2.setStroke(Color.BLACK);
                    usedColors[pickedColor] = true;
                    this.map[x1][y1] = newRectangle1;
                    this.map[x2][y2] = newRectangle2;
                }
                counter++;
            }
        }
        int id = 0;
        while (usedColors[id])
        {
            id++;
        }

        while (true)
        {
            chosenX = generator.nextInt(size);
            chosenY = generator.nextInt(size);
            Vector2d position = new Vector2d(chosenX,chosenY);
            if(!isOccupied(position))
            {
                this.chosenPose = new Vector2d(chosenX,chosenY);
                if (shape)
                {
                    Circle newCircle1 = new Circle();
                    newCircle1.setRadius((int) this.unit/2);
                    newCircle1.setCenterX(chosenX*unit+0.5*unit);
                    newCircle1.setCenterY(chosenY*unit+0.5*unit);
                    newCircle1.setFill(RandomColorGiver.GiveColor(id));
                    newCircle1.setStroke(Color.BLACK);
                    this.map[chosenX][chosenY] = newCircle1;
                }
                else
                {
                    Rectangle newRectangle1 = new Rectangle();
                    newRectangle1.setHeight(this.unit);
                    newRectangle1.setWidth(this.unit);
                    newRectangle1.setX(chosenX*unit);
                    newRectangle1.setY(chosenY*unit);
                    newRectangle1.setFill(RandomColorGiver.GiveColor(id));
                    newRectangle1.setStroke(Color.BLACK);
                    this.map[chosenX][chosenY] = newRectangle1;
                }
                break;
            }
        }
        printBoard();
    }
    public Shape getShapeAtPosse(Vector2d position)
    {
        if (position.x > this.size || position.x < 0)
        {
            return null;
        }
        if (position.y > this.size || position.y <0)
        {
            return null;
        }
         return this.map[position.x][position.y];
    }
    public boolean isOccupied(Vector2d position)
    {
        return getShapeAtPosse(position) != null;
    }
    public void printBoard()
    {
        for (int i =0 ; i< size;i++)
        {
            for (int j =0;j<size;j++)
            {
                if(this.map[i][j] != null)
                {
                    this.root.getChildren().add(this.map[i][j]);
                }
            }
        }
    }
}
