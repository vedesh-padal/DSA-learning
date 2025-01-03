// HARD
// dfs, graphs, eulerian-path

// You are given a list of airline tickets where tickets[i] = [fromi, toi]
// represent the departure and the arrival airports of one flight. Reconstruct
// the itinerary in order and return it.

// All of the tickets belong to a man who departs from "JFK", thus, the
// itinerary must begin with "JFK". If there are multiple valid itineraries, you
// should return the itinerary that has the smallest lexical order when read as
// a single string.

// For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
// ["JFK", "LGB"].
// You may assume all tickets form at least one valid itinerary. You must use
// all the tickets once and only once.

// Examples:
// Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
// Output: ["JFK","MUC","LHR","SFO","SJC"]

// Input: tickets =
// [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
// Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
// Explanation: Another possible reconstruction is
// ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.

// Constraints:
// 1 <= tickets.length <= 300
// tickets[i].length == 2
// fromi.length == 3
// toi.length == 3
// fromi and toi consist of uppercase English letters.
// fromi != toi

import java.util.*;

class Solution {

    private void dfs(String u, Map<String, PriorityQueue<String>> adj, List<String> path) {
        PriorityQueue<String> neighbors = adj.get(u);

        while (neighbors != null && !neighbors.isEmpty()) {
            dfs(neighbors.poll(), adj, path);
        }
        path.add(u);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> adj = new HashMap<>();

        for (List<String> ticket: tickets) {
            String u = ticket.get(0);
            String v = ticket.get(1);
            adj.computeIfAbsent(u, k -> new PriorityQueue<>()).offer(v);    // so that we have the lexiocgraphical order
        }

        String src = "JFK";
        List<String> path = new ArrayList<>();

        dfs(src, adj, path);

        // UNNECESSAY COMPLICATION - in first try, coming from 2079 LC hard

        // // find the destinationNode
        // String dest = tickets.get(0).get(0);
        // for (List<String> ticket: tickets) {
        //     String v = ticket.get(1);
        //     if (in.getOrDefault(v, 0) - out.getOrDefault(v, 0) == 1) {
        //         dest = v;
        //         break;
        //     }
        // }

        // // now that we have got the src, and dest nodes, we do a dfs from src
        // // till destination, and store the path
        // // and doing that with stack for better understanding
        // List<String> path = new ArrayList<>();
        // Stack<String> stk = new Stack<>();
        // stk.push(src);

        // while (!stk.isEmpty()) {
        //     String city = stk.peek();

        //     if (adj.containsKey(city) && adj.get(city).size() != 0) {
        //         String neighbor = adj.get(city).poll();
        //         stk.push(neighbor);
        //     }
        //     else {
        //         path.add(city);
        //         stk.pop();
        //     }
        // }


        Collections.reverse(path);

        return path;
    }
}