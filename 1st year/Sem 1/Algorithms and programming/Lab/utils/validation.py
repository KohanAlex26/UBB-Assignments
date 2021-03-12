def int_input(prompt, error_message="ERROR: Input must be an integer.\n"):
    """
    Read input from the user until a valid integer is given
    """
    while True:
        try:
            return int(input(prompt))
        except ValueError:
            print(error_message)
