#!/usr/bin/env python3

'''

@author: 
'''
from utils.validation import is_valid_integer

class MyPoint:
    '''
    A point is a structure of three elements: coord_x (an integer), coord_y (an integer) and colour (a string)
    '''

    def __init__(self, x, y, c):
        '''
        creates a new instance of MyPoint
        '''
        if not is_valid_integer(x) or not is_valid_integer(y):
            raise ValueError("One (or more) of the provided coordinates are invalid.")

        self.__coord_x = x
        self.__coord_y = y
        self.set_colour(c)
    
    def get_coord_x(self):
        '''
        getter method
        return the x coordinate of a point
        '''
        return self.__coord_x

    def get_coord_y(self):
        '''
        getter method
        return the y coordinate of a point
        '''
        return self.__coord_y

    def get_colour(self):
        '''
        getter method
        return the colour of a point
        '''
        return self.__colour

    def set_coord_x(self, x):
        '''
        setter method
        set the x coordinate of a point
        '''
        if not is_valid_integer(x):
            raise ValueError("The provided coordinate is invalid.")

        self.__coord_x = x

    def set_coord_y(self, y):
        '''
        setter method
        set the y coordinate of a point
        '''
        if not is_valid_integer(y):
            raise ValueError("The provided coordinate is invalid.")

        self.__coord_y = y

    def set_colour(self, c):
        '''
        setter method
        set the colour of a point
        '''
        self.__colour = "red"

        if c in ["red", "green", "blue", "yellow", "magenta"]:
            self.__colour = c
    
    def __str__(self):
        '''
        provides a string representation of a point
        returns a string
        '''
        return "Point (" + str(self.__coord_x) + ", " + str(self.__coord_y) + ") of colour " + str(self.__colour) + "."

def test_create():
    '''
    test the MyPoint class
    '''
    p1 = MyPoint(1, 2, "red")
    assert p1.get_coord_x() == 1 and p1.get_coord_y() == 2 and p1.get_colour() == "red"
    assert str(p1) == "Point (1, 2) of colour red."

    p2 = MyPoint(0, 0, "blue")
    assert p2.get_coord_x() == 0 and p2.get_coord_y() == 0 and p2.get_colour() == "blue"
    assert str(p2) == "Point (0, 0) of colour blue."

    p3 = MyPoint(-2, -4, "magenta")
    assert p3.get_coord_x() == -2 and p3.get_coord_y() == -4 and p3.get_colour() == "magenta"
    assert str(p3) == "Point (-2, -4) of colour magenta."

    p4 = MyPoint(-1, 6, "green")
    assert p4.get_coord_x() == -1 and p4.get_coord_y() == 6 and p4.get_colour() == "green"
    assert str(p4) == "Point (-1, -6) of colour green."

    p5 = MyPoint(4, -3, "yellow")
    assert p5.get_coord_x() == 4 and p5.get_coord_y() == -3 and p5.get_colour() == "yellow"
    assert str(p5) == "Point (4, -3) of colour yellow."

# Run the tests if this file
# is not imported as a module
if __name__ == "__main__":
    test_create()

