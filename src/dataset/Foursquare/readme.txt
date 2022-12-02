The folder contains the following files:
1. vertex.txt
 Each line represents a vertex. Each line starts with the vertex_id, following by vertex type.

2.edge.txt
 Each line represents an edge. Each line starts with the edge_id, following by edge type.

3. graph.txt
 Each line represents an adjacent array. Each line starts with the vertex_id, following by a list of neighbor_vertex_id and edge_id.

<Venue> 0;
<City> 1;
<Category> 2;
<User> 3;
<Time> 4;

<Venue->City> 0;
<City->Venue> 1;
<Venue->Cate> 2;
<Cate->Venue> 3;
<Venue->User> 4;
<User->Venue> 5;
<Venue->Date> 6;
<Date->Venue> 7;

4 7 0 6 4
3 5 0 2 2 3 0 4 3