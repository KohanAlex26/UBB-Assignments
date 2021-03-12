#!/usr/bin/env python3

'''

@author: 
'''
from utils.validation import is_valid_integer, int_input
from domain.logic import MyPoint
from infrastructure.repo import PointRepository

class Menu:
    '''
    the menu class consists of a static list that contains all the menu options
    '''
    menu_options = ["Exit", "Add a point to the repository", "Get all points", "Get a point at a given index", "Get all points of a given colour", "Get all points that are inside a given square (up-left corner and length given)", "Get all points that are inside a given rectangle (up-left corner, length and width given)", "Get all points that are inside a given circle (centre of circle, radius given)", "Get the minimum distance between two points", "Get the maximum distance between two points", "Update a point at a given index", "Update the colour of a point given its coordinates", "Shift all point on the x axis", "Shift all points on the y axis", "Delete a point by index", "Delete a point by coordinates", "Delete all points that are inside a given square", "Plot all points in a chart (using library matplotlib)"]

def read_point(skip_colour=False):
    '''
    reads a point of type MyPoint and returns it
    '''
    x = int_input("x = ")
    y = int_input("y = ")
    c = input("colour = ") if not skip_colour else "red"

    return MyPoint(x, y, c)

def print_points(points):
    '''
    prints all the points of type MyPoint in a given list
    '''
    if len(points) == 0:
        print("INFO: There are no points.")
    else:
        for e in points:
            print(str(e))

def display_menu():
    '''
    clears the console window and displays the user interface
    '''
    for i in range(100):
        print()

    for i in range(len(Menu.menu_options)):
        print((" " if i < 10 else "") + str(i) + ". " + Menu.menu_options[i])
    print()

def handle_menu_option(option, repo):
    '''
    handles user input
    '''
    if option == 0:
        print("INFO: Quitting.")
    elif option == 1:
        repo.add_point(read_point())
        print("INFO: The point was added to the repository.")
    elif option == 2:
        print_points(repo.get_all_points())
    elif option == 3:
        print(str(repo.get_point_at_index(int_input("index = "))))
    elif option == 4:
        print_points(repo.get_points_of_colour(input("colour = ")))
    elif option == 5:
        print("Reading data for the top-left of the square:")
        print_points(repo.get_points_inside_square(read_point(skip_colour=True), int_input("length = ")))
    elif option == 6:
        print("Reading data for the top-left of the rectangle:")
        print_points(repo.get_points_inside_rectangle(read_point(skip_colour=True), int_input("width = "), int_input("height = ")))
    elif option == 7:
        print("Reading data for the circle:")
        print_points(repo.get_points_inside_circle(int_input("centre = "), int_input("radius = ")))
    elif option == 8:
        print("INFO: The minimum distance between two points is " + str(repo.get_minimum()) + ".")
    elif option == 9:
        print("INFO: The maximum distance between two points is " + str(repo.get_maximum()) + ".")
    elif option == 10:
        index = int_input("index = ")
        print(str(repo.get_point_at_index(index)))
        repo.update_point(index, read_point())
        print("INFO: The point at the given index was updated.")
    elif option == 11:
        repo.update_colour(int_input("x = "), int_input("y = "), int_input("new colour = "))
        print("INFO: The colour of the point with the given coordinates has been updated.")
    elif option == 12:
        repo.shift_on_x(int_input("shift value = "))
        print("INFO: All points were shifted on the x axis by the given value.")
    elif option == 13:
        repo.shift_on_y(int_input("shift value = "))
        print("INFO: All points were shifted on the y axis by the given value.")
    elif option == 14:
        repo.delete_by_index(int_input("index = "))
        print("INFO: The point at the given index was deleted.")
    elif option == 15:
        repo.delete_by_coordinates(int_input("x = "), int_input("y = "))
        print("INFO: The point that has the given coordinates has been deleted.")
    elif option == 16:
        print("Reading data for the top-left of the square:")
        repo.delete_inside_square(read_point(skip_colour=True), int_input("length = "))
        print("INFO: All the points that were inside the given square have been deleted.")
    elif option == 17:
        print("The points were plotted on a chart.")
        repo.plot_points()

    print()
    input("Press <ENTER> to continue.")

def run():
    '''
    implement the user interface
    '''
    repo = PointRepository()
    option = -1

    while option != 0:
        display_menu()
        option = int_input("Option: ")
        handle_menu_option(option, repo)

