"""CSC108H1: Functions for the tic-tac-toe game.

Instructions (READ THIS FIRST!)
===============================

Make sure that the files tictactoe_game.py and a1_checker.py are in the same
folder as this file (tictactoe_functions.py).

Copyright and Usage Information
===============================

This code is provided solely for the personal and private use of students
taking the course CSC108 at the University of Toronto. Copying for purposes
other than this use is expressly prohibited. All forms of distribution of
this code, whether as given or with any changes, are expressly prohibited.

All of the files in this folder are:
Copyright (c) 2022 the University of Toronto CSC108 Teaching Team.
"""
import math

EMPTY_CELL = '-'


def is_between(value: int, min_value: int, max_value: int) -> bool:
    """Return True if and only if value is between min_value and max_value.

    Precondition: min_value < max_value

    >>> is_between(1, 0, 2)
    True
    >>> is_between(0, 2, 3)
    False
    >>> is_between(1, 1, 3)
    False
    """
    if min_value < value < max_value:
        return True
    return False


def game_board_full(s: str) -> bool:
    if EMPTY_CELL in s:
        return False
    return True


def get_board_size(s: str) -> int:
    return int(math.sqrt(len(s)))


def make_new_board(i: int) -> str:
    if 1 <= i <= 9:
        s = ""
        for k in range(0, i * i):
            s += EMPTY_CELL
        return s
    else:
        return ""


def get_string_index(row: int, col: int, size: int) -> int:
    return (row - 1) * size + col - 1


def change_cell(c: str, row: int, col: int, s: str) -> str:
    i = get_string_index(row, col, get_board_size(s))
    return s[0: i] + c + s[i + 1:]


def get_line(s: str, direction: str, num: int) -> str:
    size = get_board_size(s)
    ss = ""
    if direction == "down":
        for i in range(1, size + 1):
            index = get_string_index(i, num, size)
            ss += s[index]
    elif direction == "across":
        for i in range(1, size + 1):
            index = get_string_index(num, i, size)
            ss += s[index]
    elif direction == "down_diagonal":
        for i in range(1, size + 1):
            index = get_string_index(i, i, size)
            ss += s[index]
    elif direction == "up_diagonal":
        for i in range(1, size + 1):
            index = get_string_index(size - i, size - i, size)
            ss += s[index]
    return ss
