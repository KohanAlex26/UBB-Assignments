from domain.logic import Passanger
from domain.planes import Plane
from infrastructure.repoPassengers import PassengerRepo
from infrastructure.repoPlanes import PlaneRepo
from utils.utility import *
from UI.console import UI
from Controller.Controll import Controller
def run():
    repoPlane=PlaneRepo([])
    controller=Controller(repoPlane)
    ui=UI(controller)
    ui.handleMenu()
run()

def b():
    a="r"
    if type(a)!=int:
        raise ValueError("what is this")
try:
    b()
except ValueError as e:
    print(e)
