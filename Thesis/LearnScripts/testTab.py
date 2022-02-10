#test
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import pickle
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC



bankdata = pd.read_csv("/Users/reza/Desktop/input_router.raw")
# bankdata = pd.read_csv("/Users/reza/Desktop/Thesis/ResultsForScikit/s35932-T200.nomral")
print(bankdata.shape)
# print(bankdata)

X = bankdata.drop('Class', axis=1)
y = bankdata['Class']

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.9)
loaded_model = pickle.load(open("/Users/reza/Desktop/Thesis/trainedModels/s38584-T300-17.sav", 'rb'))

y_pred = loaded_model.predict(X_test)
# print("###\n")
# print(y_pred)

from sklearn.metrics import classification_report, confusion_matrix
print(confusion_matrix(y_test,y_pred))
tn, fp, fn, tp = confusion_matrix(y_test, y_pred).ravel()
# print(confusion_matrix(y_test,y_pred))
tpr = tp/(tp+fn)
tnr = tn/(tn+fp)

print(tpr," , ",tnr)
# result = loaded_model.score(X_test,y_test)
# print(result)

# def getMassResults(model,files):
# 	for i in range(0,len(files)):

