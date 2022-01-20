import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class Experiment {
    public static class Edge {
        int toIndex;
        int nextIndex;

        public Edge(int toIndex, int nextIndex) {
            this.toIndex = toIndex;
            this.nextIndex = nextIndex;
        }

        public Edge() {

        }

        public int getToIndex() {
            return toIndex;
        }

        public void setToIndex(int toIndex) {
            this.toIndex = toIndex;
        }

        public int getNextIndex() {
            return nextIndex;
        }

        public void setNextIndex(int nextIndex) {
            this.nextIndex = nextIndex;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Edge a = new Edge(1, 0);
        System.out.println(a.getNextIndex());
        System.out.println(a.getToIndex());
        Edge b = a;
        b.setNextIndex(9);
        System.out.println(a.getNextIndex());
        System.out.println(b.getNextIndex());
        System.out.println(b.getToIndex());

    }

}

