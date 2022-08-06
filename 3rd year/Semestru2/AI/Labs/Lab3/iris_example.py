import matplotlib.pyplot as plt
import numpy as np
from sklearn import datasets
from sklearn.model_selection import train_test_split

from activation_layer import ActivationLayer
from activations import sigmoid, sigmoid_prime
from fc_layer import FCLayer
from losses import mse, mse_prime
from network import Network

digits = datasets.load_digits()
n_samples = len(digits.images)

x_data = []
y_data = []
with open('iris.txt', 'rt') as csv:
    for line in csv.readlines():
        tokens = line.rstrip('\n').split(',')
        x_data.append([float(tokens[0]),
                       float(tokens[1]),
                       float(tokens[2]),
                       float(tokens[3])])
        y_data.append(int(tokens[4]))
x_data = np.array(x_data).reshape((-1, 4))
for col in range(4):
    x_data_col = x_data[:, col]
    x_data[:, col] = (x_data_col - np.min(x_data_col)) / (np.max(x_data_col) - np.min(x_data_col))
y_data = np.array(y_data)

x_train, x_test, y_train, y_test = train_test_split(x_data, y_data, test_size=0.2, shuffle=True)

# x_train = x_train.astype(dtype=float) / 255
x_train = x_train.reshape((-1, 1, 4))
y_train_cat = []
for y in y_train:
    print(y_train)
    temp = np.zeros(3)
    print(temp)
    temp[y] = 1
    y_train_cat.append(temp)
y_train_cat = np.array(y_train_cat).reshape((-1, 3))

# x_test = x_test.astype(dtype=float) / 255
x_test = x_test.reshape((-1, 1, 4))
y_test_cat = []
for y in y_test:
    temp = np.zeros(3)
    temp[y] = 1
    y_test_cat.append(temp)
y_test_cat = np.array(y_test_cat).reshape((-1, 3))

net = Network()
net.add(FCLayer(4, 10))
net.add(ActivationLayer(sigmoid, sigmoid_prime))
net.add(FCLayer(10, 10))
net.add(ActivationLayer(sigmoid, sigmoid_prime))
net.add(FCLayer(10, 3))
net.add(ActivationLayer(sigmoid, sigmoid_prime))

net.use(mse, mse_prime)
errors = net.fit(x_train, y_train_cat, epochs=1000, learning_rate=0.1)
errors = np.array(errors)
plt.figure(figsize=(5,5))
plt.plot(errors)
plt.show()

total = len(y_test)
out = net.predict(x_test[0:total])
out = np.array(out, dtype=float).reshape((-1, 3))
print('Predicted values:')
print(out)
print(np.argmax(out, axis=1))
print('True values:')
print(y_test[0:total])

output_predicted = np.argmax(out, axis=1)
output_true = y_test[0:total]
contor = 0
for idx in range(total):
    if output_predicted[idx] == output_true[idx]:
        contor += 1
procentaj = contor / total * 100.0
print('Accuracy is ', procentaj)
