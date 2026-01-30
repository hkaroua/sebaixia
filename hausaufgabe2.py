from copy import deepcopy


def show(B):
    for row in B:
        print(" ".join(row))
    print()


def initialize_board(n):
    return [["0" for _ in range(n)] for _ in range(n)]


def put_queen(B, pos):
    i, j = pos
    n = len(B)
    B[i][j] = "Q"
    for r in range(n):
        for c in range(n):
            if r == i or c == j or abs(r - i) == abs(c - j):
                if B[r][c] == "0":
                    B[r][c] = "X"


def free_fields(B, row):
    free = []
    for j, value in enumerate(B[row]):
        if value == "0":
            free.append(j)
    return free


def n_queens(n):
    def backtrack(row, B):
        if row == n:
            show(B)
            return
        for col in free_fields(B, row):
            B_next = deepcopy(B)
            put_queen(B_next, (row, col))
            backtrack(row + 1, B_next)

    B_start = initialize_board(n)
    backtrack(0, B_start)
