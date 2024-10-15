package graphs;

// Pepcoding
// Eulerian Path:
// Starting from any node, can you visit all EDGES in the graph exactly one time?
// you can visit same vertex more than once also -> no constraint on vertices

// Eulerian Circuit:
// you should reach the starting vertex at the end
// start and end vertex should be same

// ==> eulerian circuit => eulerian path -> present

// FOR UNDIRECTED GRAPH:
//  EC: degree of all nodes should be even
//  EP: (n-2) nodes degree should be even => 2 nodes can be odd

// FOR DIRECTED GRAPH:
// EC: indegree == outdegree
// EP: (n-2) nodes should have indegree == outdegree || 
//      other two nodes two cases:
//        1. indegree = outdegree + 1
//        2. outdegree = indegree + 1