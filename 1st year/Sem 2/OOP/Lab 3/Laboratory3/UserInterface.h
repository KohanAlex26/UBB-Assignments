#pragma once
#include "Controller.h"

void displayUI(Controller* ctrl, ProductRepo* repo, Stack* undo, Stack* redo);

void uiUndo(Controller* ctrl, Stack* undo, Stack* redo);

void uiRedo(Controller* ctrl, Stack* undo, Stack* redo);