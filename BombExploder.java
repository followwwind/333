import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BombExploder {

    public static void main(String[] args)  {

        //棋盘布局，默认第一行是
        boolean isBoard = true;
        //棋盘数组下标
        int index = -1;
        int row = -1;
        //当前棋盘行数
        int rowIndex = -1;
        List<int[][]> boardList = new ArrayList<>();
        //炸弹2队列
        Queue<int[]> queue = new Queue<>();
        //待引爆1队列
        Queue<int[]> queue2 = new Queue<>();
        //读取文件
        try (BufferedReader br = new BufferedReader(new InputStreamReader(BombExploder.class.getResourceAsStream(args[0])))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] arr = line.split(" ");
                //若为棋盘布局行，创建棋盘二维数组
                if(isBoard){
                    row = Integer.parseInt(arr[0]);
                    boardList.add(new int[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])]);
                    index++;
                    isBoard = false;
                }else{
                    //非棋盘布局行，往二维数组里面填充数据
                    rowIndex++;
                    for (int i = 0; i < arr.length; i++){
                        int num = Integer.parseInt(arr[i]);
                        if(num == 2){
                            // 数字为2时入队列，记录棋盘下标，行，列，迭代次数
                            queue.enqueue(new int[]{index, rowIndex, i, 0});
                        }
                        if(num == 1){
                            // 数字为1时入队列，记录棋盘下标，行，列
                            queue2.enqueue(new int[]{index, rowIndex, i});
                        }
                        boardList.get(index)[rowIndex][i] = num;
                    }
                    //若当前行为棋盘最后一行时,重置遍历参数，下一行为棋盘布局行
                    if(rowIndex == row - 1){
                        isBoard = true;
                        row = -1;
                        rowIndex = -1;
                    }
                }
            }

            //打印棋盘
            for(int i = 0; i < boardList.size(); i++){
                print(boardList.get(i));
            }
            //统计迭代次数
            int[] countArr = new int[boardList.size()];
            //炸弹遍历
            while(!queue.isEmpty()){
                int[] arr = queue.dequeue();
                int i = arr[0];
                int r = arr[1];
                int c = arr[2];
                //迭代次数
                int count = arr[3];
                int[][] bombArr = boardList.get(i);

                //若当前2炸弹位置的前后左右存在1，则值设置为2，放入队列中
                //上一行
                if(r - 1 >= 0 && bombArr[r -1][c] == 1){
                    bombArr[r -1][c] = 2;
                    queue.enqueue(new int[]{i, r -1, c, count + 1});
                    countArr[i] = count + 1;
                }
                //下一行
                if(r + 1 < bombArr.length && bombArr[r + 1][c] == 1){
                    bombArr[r + 1][c] = 2;
                    queue.enqueue(new int[]{i, r + 1, c, count + 1});
                    countArr[i] = count + 1;
                }
                //前一列
                if(c - 1 >= 0 && bombArr[r][c - 1] == 1){
                    bombArr[r][c - 1] = 2;
                    queue.enqueue(new int[]{i, r, c - 1, count + 1});
                    countArr[i] = count + 1;
                }
                //后一列
                if(c + 1 < bombArr[0].length && bombArr[r][c + 1] == 1){
                    bombArr[r][c + 1] = 2;
                    queue.enqueue(new int[]{i, r, c + 1, count + 1});
                    countArr[i] = count + 1;
                }
            }
            //待引爆队列遍历
            while(!queue2.isEmpty()){
                int[] arr = queue2.dequeue();
                int i = arr[0];
                int r = arr[1];
                int c = arr[2];
                int[][] bombArr = boardList.get(i);
                //若棋盘仍然存在待引爆炸弹1
                if(bombArr[r][c] == 1){
                    countArr[i] = -1;
                }
            }
            //打印棋盘
            for(int i = 0; i < boardList.size(); i++){
                print(boardList.get(i));
            }
            //打印每个Bomb游戏迭代次数
            System.out.println(Arrays.toString(countArr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(int[][] arr){
        for(int j = 0; j < arr.length; j++){
            System.out.println(Arrays.toString(arr[j]));
        }
        System.out.println();
    }
}
