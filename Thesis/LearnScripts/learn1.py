#python code  for learning
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import time
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
import pickle

bankdata = pd.read_csv("/Users/reza/Desktop/final1.txt")
print(bankdata.shape)
print(bankdata.head())

X = bankdata.drop('Class', axis=1)
y = bankdata['Class']





X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.01)

print(X_test)

start_time = time.time()
svclassifier = SVC(kernel='linear')
svclassifier.fit(X_train, y_train)

y_pred = svclassifier.predict(X_test)
# print("###\n")
# print(y_pred)

from sklearn.metrics import classification_report, confusion_matrix
print(confusion_matrix(y_test,y_pred))
print("--- %s seconds ---" % (time.time() - start_time))
# print(classification_report(y_test,y_pred))