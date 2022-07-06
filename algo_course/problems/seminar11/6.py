

def find_max_among_chosen(arr, banned):
    """
    Finds the min element index if that index is not banned
    """
    max_el = -float("inf")
    for i in range(len(arr)):
        if i not in banned and arr[i] > max_el:
            max_idx = i
    
    return max_idx


def min_transactions(transaction_graph, nodes_count):

    transactions = [0] * nodes_count

    transactions_matrix = [[0] * nodes_count for _ in range(nodes_count)]

    for trans_i in range(nodes_count):
        for receiver_i in range(nodes_count):
            # We append the difference between what is paid to you and what you pay
            transactions[trans_i] += transaction_graph[receiver_i][trans_i] - transaction_graph[trans_i][receiver_i]

    max_payed_to = transactions.index(max(transactions))
    max_pays = transactions.index(min(transactions))
    print(transactions)
    while max_payed_to != 0 or max_pays != 0:
        # We choose the max to give and the max to earn
        max_payed_to = transactions.index(max(transactions))
        max_pays = transactions.index(min(transactions))
        # 
        banned_idxs = []
        banned_idxs.append(max_pays)
        # But IF THEY ARE NOT CONNECTED
        while transaction_graph[max_pays][max_payed_to] == 0:
            # We ban the respective indexes and keep finding minimal elements where they are connected
            banned_idxs.append(max_payed_to)
            if len(banned_idxs) == len(transactions)-1:
                break
            max_payed_to = find_max_among_chosen(transactions, banned_idxs)
        # Then find the max amount that is possible to pay from 1 person to another
        # The maximum, because we need the person with the most spent money to give them 
        # all to one person, so that, that person gives the money to other people, adds his, or keeps some if he receives
        min_to_pay = max(-transactions[max_pays], transactions[max_payed_to])
        # break is there is nothing to pay from anyone
        if min_to_pay == 0:
            break
        # remove the debt from who payed, and add money to the receiver
        transactions[max_pays] += min_to_pay
        transactions[max_payed_to] -= min_to_pay
        print(transactions)
        # Set the price between two people on a matrix
        transactions_matrix[max_pays][max_payed_to] = min_to_pay

        print("Person " , max_pays , " pays " , min_to_pay
        , " to " , "Person " , max_payed_to)
    
    return transactions_matrix


if __name__ == "__main__":
    from pprint import pprint
    test =  [[0, 1000, 2000, 0],
            [0, 0, 0, 3000],
            [0, 2000, 0, 0], 
            [0, 0, 1000, 0]]
    
    matrix = min_transactions(test, len(test))

    for i in matrix:
        print(i)

