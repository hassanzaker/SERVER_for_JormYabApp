import java.util.ArrayList;
import java.util.Arrays;


public class DensityTree {
    public static final int segmentationSize = 4;

    public static final int COLOR_NONE = 0;
    public static final int COLOR_YELLOW = 1;
    public static final int COLOR_ORANGE = 2;
    public static final int COLOR_RED = 3;
    public static final int COLOR_BLACK = 4;

    Square square;
    int color;
    boolean isLeaf = false;
    int level;
    int numberOfCrimes;
    int totalCrimesWithWeight;
    DensityTree[][] children;
    ArrayList<Crime> crimes;

    DensityTree(Square square, int level) {
        this.square = square;
        this.color = COLOR_NONE;
        this.numberOfCrimes = 0;
        this.level = level;
        this.isLeaf = level == 0;
        if (!this.isLeaf) {
            children = new DensityTree[segmentationSize][segmentationSize];
            for (int i = 0; i < segmentationSize; i++) {
                for (int j = 0; j < segmentationSize; j++) {
                    this.children[i][j] = new DensityTree(square.split(i, j), level - 1);
                }
            }
        } else {
            crimes = new ArrayList<>();
        }
    }

    public void addCrime(Crime crime) {
        if (isLeaf) {
            this.crimes.add(crime);
        } else {
            if (findExactChild(crime.getCoordinates()) == null)
                return;
            this.numberOfCrimes++;
            this.totalCrimesWithWeight += crime.getCrimeType() == Crime.CRIME_TYPE_MURDER ? 2 : 1;
            findExactChild(crime.getCoordinates()).addCrime(crime);
            calculateColor();
        }
    }

    public DensityTree findExactChild(Point point) {
        int[] exactChild = this.square.findExactSquare(point);
        if (exactChild != null)
            return this.children[exactChild[0]][exactChild[1]];
        return null;
    }

    private void calculateColor() {
        int temp = this.totalCrimesWithWeight / (int) Math.pow(segmentationSize, level - 1);
        this.color = Math.min(temp, 4);
    }

    @Override
    public String toString() {
        return "DensityTree{" +
                "square=" + square +
                ", color=" + color +
                ", isLeaf=" + isLeaf +
                ", level=" + level +
                ", numberOfCrimes=" + numberOfCrimes +
                ", totalCrimesWithWeight=" + totalCrimesWithWeight +
                ", children=" + Arrays.toString(children) +
                ", crimes=" + crimes +
                '}';
    }

    public String toClientStringOuter() {
        return square.toClientString() + "c" + String.valueOf(this.color) ;
    }

    public String toClientStringInner() {
        String s = "";
        if (level > 0) {
            for (int i = 0; i < segmentationSize; i++) {
                for (int j = 0; j < segmentationSize; j++) {
                    s += children[i][j].toClientStringOuter() + ",";
                }
            }
        } else {
            for (Crime crime : this.crimes) {
                s += crime.toClientString() + ",";
            }
        }
        s += "\n";
        return s;
    }
}
