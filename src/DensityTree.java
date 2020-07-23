import javafx.scene.shape.Polygon;

import java.util.ArrayList;






public class DensityTree {
    public static final int segmentationSize = 3;

    public static final int COLOR_NONE = 0;
    public static final int COLOR_YELLOW = 1;
    public static final int COLOR_ORANGE = 2;
    public static final int COLOR_RED = 3;
    public static final int COLOR_BLACK = 4;

    Square square;
    int color;
    boolean isLeaf = false;
    int numberOfCrimes;
    DensityTree[][] children;
    ArrayList<Crime> crimes;

    DensityTree(Square square, int level){
        this.square = square;
        this.color = COLOR_NONE;
        this.numberOfCrimes = 0;
        this.isLeaf = level == 0;
        if (!this.isLeaf){
            children = new DensityTree[segmentationSize][segmentationSize];
            for (int i = 0; i < segmentationSize; i++) {
                for (int j = 0; j < segmentationSize; j++) {
                    this.children[i][j] = new DensityTree(square.split(i, j), level - 1);
                }
            }
        }else {
            crimes = new ArrayList<>();
        }
    }

    public void addCrime(Crime crime){
        if (isLeaf){
            this.crimes.add(crime);
        }else {
            this.numberOfCrimes ++;
            calculateColor();
            findExactChild(crime.getCoordinates()).addCrime(crime);

        }
    }

    private DensityTree findExactChild(Point point){
        int[] exactChild = this.square.findExactSquare(point);
        return this.children[exactChild[0]][exactChild[1]];
    }

    private void calculateColor(){
        /** should be calculate with a good function **/
    }


}
