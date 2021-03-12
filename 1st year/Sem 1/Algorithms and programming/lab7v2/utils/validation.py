#!/usr/bin/env python3

'''

@author: 
'''
def test_is_valid_integer():
    '''
    test the is_valid_integer function
    '''
    assert not is_valid_integer("")
    assert is_valid_integer(0)
    assert is_valid_integer(-5)
    assert is_valid_integer(12)
    assert not is_valid_integer("12a")

def is_valid_integer(x):
    '''
    checks if a given parameter (x) is an integer
    returns True, if x is an integer
            False, otherwise
    '''
    try:
        temp = int(x)
    except ValueError:
        return False

    return True

def int_input(prompt, error_message="ERROR: Invalid input (not an integer)"):
    '''
    continuously reads input from the user until a valid integer is given
    '''
    while True:
        try:
            return int(input(prompt))
        except ValueError:
            print(error_message)
            print()

def distance(x1, x2, y1, y2):
    '''
    computes the distance between two points and returns it
    '''
    return sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)

# Run the tests if this file
# is not imported as a module
if __name__ == "__main__":
    test_is_valid_integer()

