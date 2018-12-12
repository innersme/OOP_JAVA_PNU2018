package Programmers;

import java.util.ArrayList;

public class Solution1 {

    ArrayList<ArrayList<Integer>> f= new ArrayList<>();
    ArrayList<Integer> fn= new ArrayList<>();

    ArrayList<Boolean> isInput = new ArrayList<>();

    public static void main(String[] agrs){

        Solution1 solution1 = new Solution1();
        int n = 3;
        int [][] computer = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution1.solution(3, computer));
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        for (int i = 0; i < n ; i++)
            isInput.add(false);

        for (int i =0 ; i < n ; i++){
            for (int j =0; j < n; j++){
                fn.add(computers[i][j]);
            }
            ArrayList<Integer> toInput= new ArrayList<>(fn);
            f.add(toInput);
        }

        return answer;
    }

    public void BFS(int input){
        isInput.set(input, true);
        int nextInput;
        if (! f.get(input).isEmpty()){
            nextInput = f.get(input).get(0);
        } else {

        }
    }

}
