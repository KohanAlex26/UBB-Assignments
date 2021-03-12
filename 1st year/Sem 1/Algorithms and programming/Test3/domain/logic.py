class House():
    def __init__(self, area, price):
        self.area = 100
        self.price = 10000
        if area < 0:
            raise ValueError("You should add positive values")
        else:
            self.area = area
        if price < 0:
            raise ValueError("Add positive price")
        else:
            self.price = price

    def getArea(self):
        return self.area

    def getPrice(self):
        return self.price

    def setArea(self, area):
        self.area = area

    def setPrice(self, price):
        self.price = price

    def __str__(self):
        return "The House of area: " + str(self.area) + " costs: " + str(self.price)

    def getPricePerSquareMeter(self):
        return self.price / self.area

# o1=House(25,100)
# print(o1)