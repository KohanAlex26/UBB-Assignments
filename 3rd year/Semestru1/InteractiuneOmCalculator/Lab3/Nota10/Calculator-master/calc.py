import tkinter as tk
from playsound import playsound
import pygame
from tkinter.ttk import *
from tkinter import *
import tkinter.font as font

LARGE_FONT_STYLE = ("Arial", 40, "bold")
SMALL_FONT_STYLE = ("Arial", 16)
DIGITS_FONT_STYLE = ("Arial", 24, "bold")
DEFAULT_FONT_STYLE = ("Arial", 20)

OFF_WHITE = "#F8FAFF"
WHITE = "#FFFFFF"
LIGHT_BLUE = "#CCEDFF"
LIGHT_GRAY = "#F5F5F5"
LABEL_COLOR = "#25265E"
PURPLE = "#800080"
BLUE = "#0491FF"
ORANGE= "#ef894b"
BLACK= '#000000'
RED='#FF0000'

class Calculator:
    def __init__(self):
        self.window = tk.Tk()
        self.window.geometry("375x667")
        self.window.resizable(0, 0)
        self.window.title("Calculator")
        self.size=0
        self.color=0


        btn = tk.Button(self.window, text="Change size", bg=WHITE, fg=BLACK, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.change_size,height=2,width=5)
        btn.pack(expand=True, fill="both")
        btn2 = tk.Button(self.window, text="Change color", bg=WHITE, fg=BLACK, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.create_digit_buttons2,height=2,width=5)
        btn2.pack(expand=True, fill="both")
        btn3 = tk.Button(self.window, text="Tutorial", bg=WHITE, fg=BLACK, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.play,height=2,width=5)
        btn3.pack(expand=True, fill="both")
        #####
        # btn = tk.Button(self.window, text="A", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.change_size,height=2,width=5)
        # btn.pack(expand=True, fill="both")
        # btn2 = tk.Button(self.window, text="B", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.create_digit_buttons2,height=2,width=5)
        # btn2.pack(expand=True, fill="both")
        # btn3 = tk.Button(self.window, text="C", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.play,height=2,width=5)
        # btn3.pack(expand=True, fill="both")

        ####
        # self.buttons_frame2 = self.create_buttons_frame()
        # self.buttons_frame2.rowconfigure(0, weight=1)
        # for x in range(1, 5):
        #     self.buttons_frame2.rowconfigure(x, weight=1)
        #     self.buttons_frame2.columnconfigure(x, weight=1)

        ####

        
        # btn = tk.Button(self.buttons_frame, text="A", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.change_size,height=2,width=5)
        # btn.grid(row=1, column=1, sticky=tk.NSEW)
        # btn2 = tk.Button(self.buttons_frame, text="B", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.create_digit_buttons2,height=2,width=5)
        # btn2.grid(row=1, column=2, sticky=tk.NSEW)
        # btn3 = tk.Button(self.buttons_frame, text="C", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.play,height=2,width=5)
        # btn3.grid(row=1, column=3, sticky=tk.NSEW)

        ####
        # style = Style()
        # style.configure('W.TButton', font = ('calibri', 10, 'bold', 'underline'),foreground = 'red')
        ###
        # btn = tk.Button(self.window, text='Change size', command = self.change_size,bg='#f0f0f0',fg='#040404',bd=0,height=2,width=17,borderwidth=2)
        # # btn.config(image=img)
        # # btn.pack()  
        # # btn.grid(row=5, column=1, sticky=tk.NSEW)
        # # btn.pack(side=LEFT,anchor=NW)
        # ####
        # btn2 = tk.Button(self.window, text='Change color', command = self.create_digit_buttons2,bg='#f0f0f0',fg='#040404',bd=0,height=2,width=17,borderwidth=2)
        # # btn2.pack()
        # # btn2.pack(side=LEFT,anchor=NW)
        # ####
        # btn3 = tk.Button(self.window, text='Tutorial', command = self.play,bg='#f0f0f0',fg='#040404',bd=0,height=2,width=17,borderwidth=2)
        # # btn3.pack()
        ###
        pygame.mixer.init()
        ####

        self.total_expression = ""
        self.current_expression = ""
        self.display_frame = self.create_display_frame()
        

        self.total_label, self.label = self.create_display_labels()

        self.digits = {
            7: (1, 1), 8: (1, 2), 9: (1, 3),
            4: (2, 1), 5: (2, 2), 6: (2, 3),
            1: (3, 1), 2: (3, 2), 3: (3, 3),
            0: (4, 2), '.': (4, 1)
        }
        self.operations = {"/": "\u00F7", "*": "\u00D7", "-": "-", "+": "+"}
        self.buttons_frame = self.create_buttons_frame()
        

        self.buttons_frame.rowconfigure(0, weight=1)
        for x in range(1, 5):
            self.buttons_frame.rowconfigure(x, weight=1)
            self.buttons_frame.columnconfigure(x, weight=1)
        self.create_digit_buttons()
        self.create_operator_buttons()
        self.create_special_buttons()
        self.bind_keys()


    ####
    def play(self):
        pygame.mixer.music.load("1.mp3")
        pygame.mixer.music.play(loops=0)
    ###
    def change_size(self):
        if(self.size==0):
            self.window.geometry("428x800")
            self.size=1
        elif(self.size==1):
            self.window.geometry("562x1000")
            self.size=2
        else:
            self.window.geometry("375x667")
            self.size=0
    ####
    def create_digit_buttons2(self):
        if(self.color==0):
            for digit, grid_value in self.digits.items():
                button = tk.Button(self.buttons_frame, text=str(digit), bg=WHITE, fg=PURPLE, font=DIGITS_FONT_STYLE,
                                borderwidth=0, command=lambda x=digit: self.add_to_expression(x))
                button.grid(row=grid_value[0], column=grid_value[1], sticky=tk.NSEW)
            self.color=1
        elif(self.color==1):
            for digit, grid_value in self.digits.items():
                button = tk.Button(self.buttons_frame, text=str(digit), bg=WHITE, fg=BLUE, font=DIGITS_FONT_STYLE,
                                borderwidth=0, command=lambda x=digit: self.add_to_expression(x))
                button.grid(row=grid_value[0], column=grid_value[1], sticky=tk.NSEW)
            self.color=2
        elif(self.color==2):
            for digit, grid_value in self.digits.items():
                button = tk.Button(self.buttons_frame, text=str(digit), bg=WHITE, fg=RED, font=DIGITS_FONT_STYLE,
                                borderwidth=0, command=lambda x=digit: self.add_to_expression(x))
                button.grid(row=grid_value[0], column=grid_value[1], sticky=tk.NSEW)
            self.color=3
        else:
            for digit, grid_value in self.digits.items():
                button = tk.Button(self.buttons_frame, text=str(digit), bg=WHITE, fg=LABEL_COLOR, font=DIGITS_FONT_STYLE,
                                borderwidth=0, command=lambda x=digit: self.add_to_expression(x))
                button.grid(row=grid_value[0], column=grid_value[1], sticky=tk.NSEW)
            self.color=0
    ####

    def bind_keys(self):
        self.window.bind("<Return>", lambda event: self.evaluate())
        for key in self.digits:
            self.window.bind(str(key), lambda event, digit=key: self.add_to_expression(digit))

        for key in self.operations:
            self.window.bind(key, lambda event, operator=key: self.append_operator(operator))

    def create_special_buttons(self):
        self.create_clear_button()
        self.create_equals_button()
        self.create_square_button()
        self.create_sqrt_button()

    def create_display_labels(self):
        total_label = tk.Label(self.display_frame, text=self.total_expression, anchor=tk.E, bg=LIGHT_GRAY,
                               fg=LABEL_COLOR, padx=24, font=SMALL_FONT_STYLE)
        total_label.pack(expand=True, fill='both')

        label = tk.Label(self.display_frame, text=self.current_expression, anchor=tk.E, bg=LIGHT_GRAY,
                         fg=LABEL_COLOR, padx=24, font=LARGE_FONT_STYLE)
        label.pack(expand=True, fill='both')

        return total_label, label

    def create_display_frame(self):
        frame = tk.Frame(self.window, height=221, bg=LIGHT_GRAY)
        frame.pack(expand=True, fill="both")
        return frame

    def add_to_expression(self, value):
        self.current_expression += str(value)
        self.update_label()

    def create_digit_buttons(self):
        ####
        # btn = tk.Button(self.buttons_frame, text="A", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.change_size,height=2,width=5)
        # btn.grid(row=1, column=1, sticky=tk.NSEW)
        # btn2 = tk.Button(self.buttons_frame, text="B", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.create_digit_buttons2,height=2,width=5)
        # btn2.grid(row=1, column=2, sticky=tk.NSEW)
        # btn3 = tk.Button(self.buttons_frame, text="C", bg=WHITE, fg=ORANGE, font=DIGITS_FONT_STYLE,borderwidth=0,command=self.play,height=2,width=5)
        # btn3.grid(row=1, column=3, sticky=tk.NSEW)
        ####

        for digit, grid_value in self.digits.items():
            button = tk.Button(self.buttons_frame, text=str(digit), bg=WHITE, fg=LABEL_COLOR, font=DIGITS_FONT_STYLE,
                               borderwidth=0, command=lambda x=digit: self.add_to_expression(x))
            button.grid(row=grid_value[0], column=grid_value[1], sticky=tk.NSEW)

    def append_operator(self, operator):
        self.current_expression += operator
        self.total_expression += self.current_expression
        self.current_expression = ""
        self.update_total_label()
        self.update_label()

    def create_operator_buttons(self):
        i = 0
        for operator, symbol in self.operations.items():
            button = tk.Button(self.buttons_frame, text=symbol, bg=OFF_WHITE, fg=LABEL_COLOR, font=DEFAULT_FONT_STYLE,
                               borderwidth=0, command=lambda x=operator: self.append_operator(x))
            button.grid(row=i, column=4, sticky=tk.NSEW)
            i += 1

    def clear(self):
        self.current_expression = ""
        self.total_expression = ""
        self.update_label()
        self.update_total_label()

    def create_clear_button(self):
        button = tk.Button(self.buttons_frame, text="C", bg=OFF_WHITE, fg=LABEL_COLOR, font=DEFAULT_FONT_STYLE,
                           borderwidth=0, command=self.clear)
        button.grid(row=0, column=1, sticky=tk.NSEW)

    def square(self):
        self.current_expression = str(eval(f"{self.current_expression}**2"))
        self.update_label()

    def create_square_button(self):
        button = tk.Button(self.buttons_frame, text="x\u00b2", bg=OFF_WHITE, fg=LABEL_COLOR, font=DEFAULT_FONT_STYLE,
                           borderwidth=0, command=self.square)
        button.grid(row=0, column=2, sticky=tk.NSEW)

    def sqrt(self):
        self.current_expression = str(eval(f"{self.current_expression}**0.5"))
        self.update_label()

    def create_sqrt_button(self):
        button = tk.Button(self.buttons_frame, text="\u221ax", bg=OFF_WHITE, fg=LABEL_COLOR, font=DEFAULT_FONT_STYLE,
                           borderwidth=0, command=self.sqrt)
        button.grid(row=0, column=3, sticky=tk.NSEW)

    def evaluate(self):
        self.total_expression += self.current_expression
        self.update_total_label()
        try:
            self.current_expression = str(eval(self.total_expression))

            self.total_expression = ""
        except Exception as e:
            self.current_expression = "Error"
        finally:
            self.update_label()

    def create_equals_button(self):
        button = tk.Button(self.buttons_frame, text="=", bg=LIGHT_BLUE, fg=LABEL_COLOR, font=DEFAULT_FONT_STYLE,
                           borderwidth=0, command=self.evaluate)
        button.grid(row=4, column=3, columnspan=2, sticky=tk.NSEW)

    def create_buttons_frame(self):
        frame = tk.Frame(self.window)
        frame.pack(expand=True, fill="both")
        return frame

    def update_total_label(self):
        expression = self.total_expression
        for operator, symbol in self.operations.items():
            expression = expression.replace(operator, f' {symbol} ')
        self.total_label.config(text=expression)

    def update_label(self):
        self.label.config(text=self.current_expression[:11])

    def run(self):
        self.window.mainloop()


if __name__ == "__main__":
    calc = Calculator()
    calc.run()
