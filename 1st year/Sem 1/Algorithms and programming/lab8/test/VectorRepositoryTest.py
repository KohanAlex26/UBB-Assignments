import unittest
from infrastructure.repo import VectorRepository
from domain.logic import MyVector
class VectorRepositoryTest(unittest.TestCase):
    def setUp(self):
        self.list=VectorRepository()
    def test_addp(self):
        self.list.addp(MyVector("name1","r",1,[1,2,3]))
        self.assertEqual(str(self.list),str(MyVector("name1","r",1,[1,2,3]))+"\n")

    def test_str(self):
        self.list.addp(MyVector("name1", "r", 1, [1, 2, 3]))
        self.list.addp(MyVector("name2", "b", 2, [2, 4, 6]))
        self.assertEqual(str(self.list),str(MyVector("name1", "r", 1, [1, 2, 3]))+"\n"+str(MyVector("name2", "b", 2, [2, 4, 6]))+"\n")

    def test_getVectors(self):
        self.list.addp(MyVector("name1", "r", 1, [1, 2, 3]))
        self.list.addp(MyVector("name2", "b", 2, [2, 4, 6]))
        self.assertEqual(self.list.getVectors(),str(MyVector("name1", "r", 1, [1, 2, 3]))+"\n"+str(MyVector("name2", "b", 2, [2, 4, 6]))+"\n")

    def test_vectorAtIndex(self):
        self.list.addp(MyVector("name1", "r", 1, [1, 2, 3]))
        self.assertEqual(str(self.list.vectorAtIndex(0)),str(MyVector("name1", "r", 1, [1, 2, 3])))

    def test_updateVector(self):
        self.list.addp(MyVector("name1", "r", 1, [1, 2, 3]))
        self.list.updateVector(0,MyVector("name2", "b", 2, [2, 4, 6]))
        self.assertEqual(str(self.list),str(MyVector("name2", "b", 2, [2, 4, 6]))+"\n")

    def test_updateVectorByName(self):
        self.list.addp(MyVector("name1", "r", 1, [1, 2, 3]))
        self.list.updateVectorByName("name1",MyVector("name3","g",3,[5,6,7]))
        self.assertEqual(str(self.list),str(MyVector("name3","g",3,[5,6,7]))+"\n")

    def test_deleteByIndex(self):
        self.list.addp(MyVector("name1", "r", 1, [1, 2, 3]))
        self.list.addp(MyVector("name2", "b", 2, [2, 4, 6]))
        self.list.deleteByIndex(0)
        self.assertEqual(str(self.list),str(MyVector("name2", "b", 2, [2, 4, 6]))+"\n")

    def test_deleteByName(self):
        self.list.addp(MyVector("name1", "r", 1, [1, 2, 3]))
        self.list.addp(MyVector("name2", "b", 2, [2, 4, 6]))
        self.list.deleteByIndex("name1")
        self.assertEqual(str(self.list), str(MyVector("name2", "b", 2, [2, 4, 6])) + "\n")