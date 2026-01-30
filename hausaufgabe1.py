from copy import deepcopy


def det(M):
    a00, a01, a02 = M[0]
    a10, a11, a12 = M[1]
    a20, a21, a22 = M[2]
    return (
        a00 * a11 * a22
        + a01 * a12 * a20
        + a02 * a10 * a21
        - a20 * a11 * a02
        - a21 * a12 * a00
        - a22 * a10 * a01
    )


def show(M):
    column_widths = []
    for j in range(3):
        column_widths.append(max(len(str(M[i][j])) for i in range(3)))
    for i in range(3):
        line_parts = []
        for j in range(3):
            line_parts.append(str(M[i][j]).rjust(column_widths[j]))
        print(" ".join(line_parts))
    print()


def zero_determinant(entries):
    def backtrack(k, M):
        if k == 9:
            if det(M) == 0:
                show(M)
            return
        i = k // 3
        j = k % 3
        for value in entries:
            M_next = deepcopy(M)
            M_next[i][j] = value
            backtrack(k + 1, M_next)

    M_start = [[0 for _ in range(3)] for _ in range(3)]
    backtrack(0, M_start)
