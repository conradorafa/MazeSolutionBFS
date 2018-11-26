import java.awt.*;
import java.util.LinkedList;

public class MazeSolution {

    public static void main (String[] args) {

        int[][] maze = new int[][] {
                {1,1,0} ,
                {1,0,1} ,
                {1,9,1}
        };

        MazeSolution mazeSolution = new MazeSolution();

        Point source = new Point(0, 0);
        Point dest = mazeSolution.findDestination( maze, 9);

        int result = mazeSolution.BFS(maze, source, dest);


        System.out.println("Shortest Path is: "+ result);

        result = mazeSolution.BFSbyValue(maze, source, 9);

        System.out.println("Shortest Path is: "+ result);
    }

    Point findDestination(int[][] maze, int value){
        Point result = new Point(0,0);
        for(int i=0; i<maze.length; i++) {
            for(int j=0; j<maze[0].length; j++) {
                if(maze[i][j] == value) {
                    result.x = i;
                    result.y = j;
                }
            }
        }
        return result;
    }

    boolean isValid(int row, int col, int rowSize, int colSize){
        return (row >= 0) && (row < rowSize) && (col >= 0) && (col < colSize);
    }


    private int BFS(int mat[][], Point src, Point dest) {
        int result = 0;
        int rowSize = mat.length;
        int colSize = mat[0].length;

        int rowNum[] = {-1, 0, 0, 1};
        int colNum[] = {0, -1, 1, 0};

        boolean[][] visited = new boolean[mat.length][mat.length];
        visited[src.x][src.y] = true;

        LinkedList<QueueNode> q = new LinkedList<>();

        QueueNode node = new QueueNode();
        node.setPt(src);
        node.setDist(0);

        q.add(node);

        while (!q.isEmpty()) {
            QueueNode curr = q.remove();
            Point pt = curr.getPt();
            if(pt.x == dest.x && pt.y == dest.y) {
                return curr.getDist();
            }

            for(int i=0; i<4; i++){
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];

                if (isValid(row, col, rowSize, colSize) && (mat[row][col] == 1 || mat[row][col] == 9) &&  !visited[row][col]){
                    visited[row][col] = true;
                    QueueNode curNode = new QueueNode();
                    curNode.setPt(new Point(row, col));
                    curNode.setDist(curr.dist+1);

                    q.add(curNode);

                }


            }


        }


        return result;

    }

    private int BFSbyValue(int mat[][], Point src, int value) {
        int result = 0;
        int rowSize = mat.length;
        int colSize = mat[0].length;

        int rowNum[] = {-1, 0, 0, 1};
        int colNum[] = {0, -1, 1, 0};

        boolean[][] visited = new boolean[mat.length][mat.length];
        visited[src.x][src.y] = true;

        LinkedList<QueueNode> q = new LinkedList<>();

        QueueNode node = new QueueNode();
        node.setPt(src);
        node.setDist(0);

        q.add(node);

        while (!q.isEmpty()) {
            QueueNode curr = q.remove();
            Point pt = curr.getPt();
            if(mat[pt.x][pt.y] == value) {
                return curr.getDist();
            }

            for(int i=0; i<4; i++){
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];

                if (isValid(row, col, rowSize, colSize) && (mat[row][col] == 1 || mat[row][col] == 9) &&  !visited[row][col]){
                    visited[row][col] = true;
                    QueueNode curNode = new QueueNode();
                    curNode.setPt(new Point(row, col));
                    curNode.setDist(curr.dist+1);

                    q.add(curNode);

                }

            }

        }

        return result;
    }

    class QueueNode{
        private Point pt;
        private int dist;

        public Point getPt() {
            return pt;
        }

        public void setPt(Point pt) {
            this.pt = pt;
        }

        public int getDist() {
            return dist;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }
    }
}
