#!/usr/bin/env python3

'''

@author: 
'''
from math import sqrt
from domain.logic import MyPoint
from utils.validation import distance
import matplotlib.pyplot as plt

class PointRepository:
    '''
    A point repository is a structure that manages multiple points of class MyPoint
    '''
    def __init__(self):
        '''
        creates a new instance of PointRepository
        '''
        self.__data = []

    def add_point(self, point):
        '''
        adds a point to the point list
        '''
        self.__data.append(point)

    def get_all_points(self):
        '''
        returns a list containing all points
        '''
        return self.__data
    
    def get_point_at_index(self, index):
        '''
        returns the point located at the given index
        '''
        if index < 0 or index > len(self.__data) - 1:
            raise IndexError("There is no point corresponding to the given index.")

        return self.__data[index]

    def get_points_of_colour(self, colour):
        '''
        returns a list of points of a given colour
        '''
        if not colour in ["red", "green", "blue", "yellow", "magenta"]:
            raise ValueError("The provided colour is not valid.")

        matching = []
        for e in self.__data:
            if e.get_colour() == colour:
                matching.append(e)

        return matching
    
    def get_points_inside_square(self, top_left, length):
        '''
        returns a list of points that are inside a given square
        '''
        return self.get_points_inside_rectangle(top_left, length, length)

    def get_points_inside_rectangle(self, top_left, width, height):
        '''
        returns a list of points that are inside a given rectangle
        '''
        matching = []

        for e in self.__data:
            if e.get_coord_x() > top_left.get_coord_x() and e.get_coord_x() < top_left.get_coord_x() + width and e.get_coord_y() < top_left.get_coord_y() and e.get_coord_y() > top_left.get_coord_y() - height:
                matching.append(e)

        return matching

    def get_points_inside_circle(self, centre, radius):
        '''
        returns a list of points that are inside a given circle
        '''
        matching = []

        for e in self.__data:
            if e.get_coord_x() < centre.get_coord_x() + radius and e.get_coord_x() > centre.get_coord_x() - radius and e.get_coord_y() > centre.get_coord_y() - radius and e.get_coord_y() < centre.get_coord_y() + radius:
                matching.append(e)

        return matching

    def get_minimum_distance(self):
        '''
        returns the minimum distance between two points
        '''
        minimum = None

        for i in range(len(self.__data) - 1):
            for j in range(i + 1, len(self.__data)):
                distance = get_distance(self.__data[i].get_coord_x(), self.__data[j].get_coord_x(), self.__data[i].get_coord_y(), self.__data[j].get_coord_y())

                if minimum is None or distance < minimum:
                    minimum = distance

        return minimum

    def get_maximum_distance(self):
        '''
        returns the maximum distance between two points
        '''
        maximum = None

        for i in range(len(self.__data) - 1):
            for j in range(i + 1, len(self.__data)):
                distance = get_distance(self.__data[i].get_coord_x(), self.__data[j].get_coord_x(), self.__data[i].get_coord_y(), self.__data[j].get_coord_y())

                if maximum is None or distance > maximum:
                    maximum = distance

        return maximum

    def update_point(self, index, point):
        '''
        updates a point at a given index
        '''
        if index < 0 or index > len(self.__data) - 1:
            raise IndexError("There is no point corresponding to the given index.")

        self.__data[index] = point

    def update_colour(self, x, y, c):
        '''
        updates the colour of a point determined by the given coordinates
        '''
        for e in self.__data:
            if e.get_coord_x == x and e.get_coord_y == y:
                e.set_colour(c)

    def shift_on_x(self, value):
        '''
        shifts all points on the x axis by a given value
        '''
        for e in self.__data:
            e.set_coord_x(e.get_coord_x + value)

    def shift_on_y(self, value):
        '''
        shifts all points on the y axis by a given value
        '''
        for e in self.__data:
            e.set_coord_y(e.get_coord_y + value)

    def delete_by_index(self, index):
        '''
        deletes the point at a given index
        '''
        if index < 0 or index > len(self.__data) - 1:
            raise IndexError("There is no point corresponding to the given index.")

        del self.__data[index]

    def delete_by_coordinates(self, x, y):
        '''
        deletes a point which has the given coordinates
        '''
        for i in range(len(self.__data) - 1, -1, -1):
            if self.__data[i].get_coord_x() == x and self.__data[i].get_coord_y() == y:
                del self.__data[i]
    
    def delete_inside_square(self, top_left, length):
        '''
        deletes all points inside a given square
        '''
        points = self.get_points_inside_square(top_left, length)

        for e in points:
            self.delete_by_coordinates(e.get_coord_x(), e.get_coord_y())

    def plot_points(self):
        '''
        plots all points in a chart
        '''
        x = []
        y = []
        colours = []
        
        for e in self.__data:
            x.append(e.get_coord_x())
            y.append(e.get_coord_y())
            colours.append(e.get_colour())

        plt.scatter(x, y, c=colours)
        plt.show()

def test_create():
    '''
    test the PointRepository class
    '''
    repo = PointRepository()
    assert repo.get_all_points() == []
    
    point1 = MyPoint(1, 2, "red")
    repo.add_point(point1)
    assert repo.get_all_points() == [point1]
    assert repo.get_point_at_index(0) == point1
    assert repo.get_points_of_colour("red") == [point1]
    assert repo.get_points_inside_square(MyPoint(0, 3, "red"), 3) == [point1]
    assert repo.get_points_inside_rectangle(MyPoint(0, 3, "red"), 2, 3) == [point1]
    assert repo.get_points_inside_circle(MyPoint(1, 2, "red"), 1) == [point1]
    
    point2 = MyPoint(1, 3, "blue")
    point3 = MyPoint(4, 6, "magenta")
    repo.add_point(point2)
    repo.add_point(point3)
    
    assert repo.get_minimum_distance() == 1.0
    assert repo.get_maximum_distance() == 5.0

# Run the tests if this file
# is not imported as a module
if __name__ == "__main__":
    test_create()

