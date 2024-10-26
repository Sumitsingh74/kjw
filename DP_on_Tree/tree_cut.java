import java.io.*;
import java.util.*;

class Codechef {
    static List<List<Integer>> adj;
    static int degree[];
    public static void main (String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine().trim());
        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            adj = new ArrayList<>();
            degree = new int[n+1];
            for(int i = 0; i <= n; i++) adj.add(new ArrayList<>());
            for(int i = 0; i < n-1; i++) {
                String[] edge = br.readLine().trim().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
                degree[u]++;
                degree[v]++;
            }
            if (n == 2) {
                out.println(1);
                out.println(1 + " " + 2 + " " + 1);
                continue;
            }
            if (n == 1) {
                out.println(0);
                continue;
            }
            out.println(0);
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (degree[i] == 1) q.offer(i);
            }
            int rn = n;
            while (rn > 2) {
                int node = q.poll();
                for (int k : adj.get(node)) {
                    degree[k]--;
                    if (degree[k] >= 1) {
                        if (n % 2 == 0 && rn <= 4) {
                            out.println(node + " " + k + " " + k);
                        } else {
                            out.println(node + " " + k + " " + node);
                        }
                    }
                    if (degree[k] == 1) {
                        q.offer(k);
                    }
                }
                rn--;
            }
            int first = q.poll();
            int second = q.peek();
            out.println(first + " " + second + " " + first);
        }
        out.flush();
    }
}
