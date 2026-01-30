from copy import deepcopy


def det(matrix):
    top_row, middle_row, bottom_row = matrix
    top_left, top_middle, top_right = top_row
    middle_left, middle_middle, middle_right = middle_row
    bottom_left, bottom_middle, bottom_right = bottom_row
    positive_sum = (
        top_left * middle_middle * bottom_right
        + top_middle * middle_right * bottom_left
        + top_right * middle_left * bottom_middle
    )
    negative_sum = (
        bottom_left * middle_middle * top_right
        + bottom_middle * middle_right * top_left
        + bottom_right * middle_left * top_middle
    )
    return positive_sum - negative_sum


def show(matrix):
    if not matrix:
        print()
        return
    column_count = len(matrix[0])
    column_widths = [0] * column_count
    for row in matrix:
        for column_index, value in enumerate(row):
            width = len(str(value))
            if width > column_widths[column_index]:
                column_widths[column_index] = width
    for row in matrix:
        formatted_cells = []
        for column_index, value in enumerate(row):
            cell_text = str(value)
            if column_index < column_count - 1:
                formatted_cells.append(cell_text.ljust(column_widths[column_index]))
            else:
                formatted_cells.append(cell_text)
        print(" ".join(formatted_cells))
    print()


def zero_determinant(entries):
    matrix = [[0 for _ in range(3)] for _ in range(3)]

    def backtrack(cell_index):
        if cell_index == 9:
            if det(matrix) == 0:
                show(matrix)
            return
        row_index = cell_index // 3
        column_index = cell_index % 3
        for value in entries:
            matrix[row_index][column_index] = value
            backtrack(cell_index + 1)

    backtrack(0)


def initialize_board(board_size):
    return [["0" for _ in range(board_size)] for _ in range(board_size)]


def put_queen(board, position):
    row_index, column_index = position
    board_size = len(board)
    board[row_index][column_index] = "Q"
    for index in range(board_size):
        if board[row_index][index] != "Q":
            board[row_index][index] = "x"
        if board[index][column_index] != "Q":
            board[index][column_index] = "x"
    for offset in range(1, board_size):
        diagonals = (
            (row_index - offset, column_index - offset),
            (row_index - offset, column_index + offset),
            (row_index + offset, column_index - offset),
            (row_index + offset, column_index + offset),
        )
        for diag_row, diag_column in diagonals:
            if 0 <= diag_row < board_size and 0 <= diag_column < board_size:
                if board[diag_row][diag_column] != "Q":
                    board[diag_row][diag_column] = "x"


def free_fields(board, row_index):
    free_columns = []
    for column_index, value in enumerate(board[row_index]):
        if value == "0":
            free_columns.append(column_index)
    return free_columns


def n_queens(board_size):
    if board_size <= 0:
        return

    def backtrack(row_index, board_state):
        if row_index == board_size:
            show(board_state)
            return
        for column_index in free_fields(board_state, row_index):
            next_board = deepcopy(board_state)
            put_queen(next_board, (row_index, column_index))
            backtrack(row_index + 1, next_board)

    start_board = initialize_board(board_size)
    backtrack(0, start_board)
