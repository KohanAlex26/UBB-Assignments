import numpy as np
from sklearn import datasets
from sklearn.model_selection import train_test_split

from activation_layer import ActivationLayer
from activations import tanh, tanh_prime
from activations import sigmoid,sigmoid_prime
from fc_layer import FCLayer
from losses import mse, mse_prime
from network import Network
import matplotlib.pyplot as plt

digits = datasets.load_digits()
n_samples = len(digits.images)

x_train, x_test, y_train, y_test = train_test_split(digits.images, digits.target, test_size=0.2, shuffle=True)

x_train = x_train.astype(dtype=float) / 255
x_train = x_train.reshape((-1, 1, 8 * 8))
y_train_cat = []
for y in y_train:
    temp = np.zeros(10)
    temp[y] = 1
    y_train_cat.append(temp)
y_train_cat = np.array(y_train_cat).reshape((-1, 10))

x_test = x_test.astype(dtype=float) / 255
x_test = x_test.reshape((-1, 1, 8 * 8))
y_test_cat = []
for y in y_test:
    temp = np.zeros(10)
    temp[y] = 1
    y_test_cat.append(temp)
y_test_cat = np.array(y_test_cat).reshape((-1, 10))

net = Network()
net.add(FCLayer(8 * 8, 25))
net.add(ActivationLayer(tanh, tanh_prime))
net.add(FCLayer(25, 25))
net.add(ActivationLayer(tanh, tanh_prime))
net.add(FCLayer(25, 10))
net.add(ActivationLayer(tanh, tanh_prime))

net.use(mse, mse_prime)
errors=net.fit(x_train, y_train_cat, epochs=100, learning_rate=0.1)
plt.plot(errors)
plt.show()

total = 10
out = net.predict(x_test[0:total])
out = np.array(out, dtype=float).reshape((-1, 10))
print('Predicted values:')
print(out)
computed=np.argmax(out, axis=1)
print(computed)
print('True values:')
actual=y_test[0:total]
print(actual)



corect=0
for i in range(total):
    if actual[i]==computed[i]:
        corect+=1
print(str((corect*10))+"%")