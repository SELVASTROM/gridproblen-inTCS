import java.util.*;

class gridgame {

    static class Cell {
        int x, y, dist;

        public Cell(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static int minMoves(int[][] grid, int M, int N, int[] source, int[] destination, int[] moveRule) {
        int[] dx = {moveRule[0], moveRule[1], -moveRule[0], -moveRule[1]};
        int[] dy = {moveRule[1], -moveRule[0], -moveRule[1], moveRule[0]};
        boolean[][] visited = new boolean[M][N];
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(source[0], source[1], 0));  
        visited[source[0]][source[1]] = true;           
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current.x == destination[0] && current.y == destination[1]) {
                return current.dist;
            }

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];
                if (flag(newX, newY, M, N, grid, visited)) {
                    visited[newX][newY] = true;
                    queue.offer(new Cell(newX, newY, current.dist + 1));
                }
            }
        }

        return -1;  
    }
    static boolean flag(int x, int y, int M, int N, int[][] grid, boolean[][] visited) {
        return x >= 0 && x < M && y >= 0 && y < N && grid[x][y] == 0 && !visited[x][y];
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int row = s.nextInt();
        int col = s.nextInt();
        int[][] grid = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = s.nextInt();
            }
        }
        int[] source = {s.nextInt(), s.nextInt()};
        int[] destination = {s.nextInt(), s.nextInt()};
        int[] moveRule = {s.nextInt(), s.nextInt()};
        int result = minMoves(grid, row, col, source, destination, moveRule);
        System.out.print(result);
    }
}
