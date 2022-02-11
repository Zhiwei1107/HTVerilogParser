#python code  for learning
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import pickle
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
import time

bankdata = pd.read_csv("/Users/reza/Desktop/Thesis/unifiedBalancedOfEveryBenchmark/17files/RS232-T1300.unk")
print(bankdata.shape)
print(bankdata.head())

X = bankdata.drop('Class', axis=1)
y = bankdata['Class']
X = X.drop('FFO', axis=1)

print(X.shape)
# exit()
start_time = time.time()
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.01)

print(X_test)

svclassifier = SVC(kernel='rbf',C=32, gamma=0.1)
svclassifier.fit(X_train, y_train)

filename = '/Users/reza/Desktop/withoutFFO.sav'
pickle.dump(svclassifier, open(filename, 'wb'))
print("--- %s seconds ---" % (time.time() - start_time))
