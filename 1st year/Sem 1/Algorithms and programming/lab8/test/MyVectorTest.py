import unittest
from domain.logic import MyVector
class MyVectorTest(unittest.TestCase):
    def setUp(self):
        self.MyV=MyVector("Name1","r",1,[1,2,3])

    def test_str(self):
        self.assertEqual(str(self.MyV),"Name: Name1 of colour r, of type 1 is [1, 2, 3]")

    def test_setname(self):
        self.MyV.setname("Name2")
        self.assertEqual(self.MyV.getname(),"Name2")

    def test_setcolour(self):
        self.MyV.setcolour("b")
        self.assertEqual(self.MyV.getcolour(),"b")

    def test_settype(self):
        self.MyV.settype(3)
        self.assertEqual(self.MyV.gettype(),3)

    def test_setvalues(self):
        self.MyV.setvalues([2,4,5])
        self.assertEqual(self.MyV.getvalues(),[2,4,5])

    def test_addscalar(self):
        self.MyV.addscalar(2)
        self.assertEqual(self.MyV.getvalues(),[3,4,5])

    def test_addtwovectors(self):
        self.assertEqual(self.MyV.addtwovectors([1,2,3]),[2,4,6])

    def test_substracttwovectors(self):
        self.assertEqual(self.MyV.substracttwovectors([1,2,3]),[0,0,0])

    def test_multiplytwovectors(self):
        self.assertEqual(self.MyV.multiplytwovectors([1,2,3]),[1,4,9])

    def test_suminvector(self):
        self.assertEqual(self.MyV.suminvector(),6)
    #
    def productinvector(self):
        self.assertEqual(self.MyV.productinvector(),6)
    #
    def test_minimum(self):
        self.assertEqual(self.MyV.minimum(),1)
    #
    def test_maximum(self):
        self.assertEqual(self.MyV.maximum(),3)

# if __name__=="main":
#     unittest.main()