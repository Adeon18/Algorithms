
class PriorityQueue(object):
    def __init__(self):
        self.queue = []
  
    def __str__(self):
        return ' '.join([str(i) for i in self.queue])
  
    def is_empty(self):
        return len(self.queue) == 0
  
    def insert(self, data):
        self.queue.append(data)
  
    def pop(self):
        try:
            min_el = 0
            for i in range(len(self.queue)):
                if self.queue[i].cost < self.queue[min_el].cost:
                    min_el = i
            item = self.queue[min_el]
            del self.queue[min_el]
            return item
        except IndexError:
            print()
            exit()
    
    def top(self):
        try:
            min_el = 0
            for i in range(len(self.queue)):
                if self.queue[i].cost < self.queue[min_el].cost:
                    min_el = i
            item = self.queue[min_el]
            return item
        except IndexError:
            print()
            exit()


class Node:
    def __init__(self, to, cost, line):
        self.to = to
        self.cost = cost
        self.line = line

    def __str__(self) -> str:
        return "Node({}, {}, {})".format(self.to, self.cost, self.line)
    
    def __repr__(self) -> str:
        return "Node({}, {}, {})".format(self.to, self.cost, self.line)



if __name__ == "__main__":
    from pprint import pprint
    inp1 = input().split()
    stations, lines = int(inp1[0]), int(inp1[1])
    adj_list = [[] for _ in range(stations+1)]
    # The closest distance from the start to some node n on line l
    dist = [[float('inf')]*(lines+1) for _ in range(stations+1)]
    # Input management
    for i in range(1, lines+1):
        inp = input().split()
        inp = [int(q) for q in inp]
        for j in range(len(inp)-1):
            adj_list[inp[j]].append(Node(inp[j+1], 2, i))
            adj_list[inp[j+1]].append(Node(inp[j], 2, i))
    
    inp3 = input().split()
    start, strt_line = int(inp3[0]), int(inp3[1])
    end, end_line = int(inp3[2]), int(inp3[3])

    # pprint(adj_list)
    # pprint(dist)
    # print(start)
    # dist[i][j] The smallest len from start to node i on line j
    dist[start][strt_line] = 0

    pq = PriorityQueue()
    
    pq.insert(Node(start, 0, strt_line))
    # Since we pop on each iteration and insert only when the new smallest path was found => 
    # We will eventually empty our queue
    while not pq.is_empty():
        # top() here return the lowest cost.
        top_cost = pq.top().cost
        top_to = pq.top().to
        top_line = pq.top().line
        pq.pop()
        # We check each node accesible from top and write the respective total costs
        for i in range(len(adj_list[top_to])):
            i_to = adj_list[top_to][i].to
            i_cost = adj_list[top_to][i].cost
            i_line = adj_list[top_to][i].line

            total_cost = top_cost + i_cost
            # +1 if we move lines
            if i_line != top_line:
                total_cost += 1
            # if the path i the most optimal - we update it. And continue counting
            # the smallest path knowing this cost
            if total_cost < dist[i_to][i_line]:
                dist[i_to][i_line] = total_cost
                pq.insert(Node(i_to, total_cost, i_line))
    
    print(dist[end][end_line]);
    pprint(adj_list)
    pprint(dist)