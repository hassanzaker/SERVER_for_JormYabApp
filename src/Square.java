public class Square {
    Point leftTop;
    Point rightTop;
    Point leftDown;
    Point rightDown;
    double left;
    double right;
    double top ;
    double down ;
    double length ;
    double width ;

    public Square(Point leftTop, Point rightTop, Point leftDown, Point rightDown) {
        this.leftTop = leftTop;
        this.rightTop = rightTop;
        this.leftDown = leftDown;
        this.rightDown = rightDown;

        this.left = this.leftTop.getLongitude();
        this.right = this.rightDown.getLongitude();
        this.top = this.leftTop.getLatitude();
        this.down = this.rightDown.getLatitude();
        this.length = (right - left) / DensityTree.segmentationSize;
        this.width = (down - top) / DensityTree.segmentationSize;


    }



    public Square split(int i, int j) {

        Point newLeftTop = new Point(left + length * i, top + width * j);
        Point newRightTop = new Point(left + length * (i+1), top + width * j);
        Point newLeftDown = new Point(left + length * i, top + width * (j+1));
        Point newRightDown = new Point(left + length * (i+1), top + width * (j+1));
        return new Square(newLeftTop, newRightTop, newLeftDown, newRightDown);
    }

    public int[] findExactSquare(Point point){
        if (point.getLongitude() >= this.left && point.getLatitude() >= this.down && point.getLongitude() <= this.right && point.getLatitude() <= this.top) {
            int[] temp = new int[2];
            temp[0] = (int) ((point.getLongitude() - left) / this.length);
            temp[1] = (int) ((point.getLatitude() - this.top) / this.width);
            return temp;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Square{" +
                "leftTop=" + leftTop +
                ", rightTop=" + rightTop +
                ", leftDown=" + leftDown +
                ", rightDown=" + rightDown +
                ", left=" + left +
                ", right=" + right +
                ", top=" + top +
                ", down=" + down +
                ", length=" + length +
                ", width=" + width +
                '}';
    }


    public String toClientString(){
        return leftTop.toClientString() + "-" + rightTop.toClientString() + "-" + rightDown.toClientString() + "-" + leftDown.toClientString();
    }
}
