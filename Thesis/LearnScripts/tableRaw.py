#combination of learn and test files for creating table
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import pickle
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import classification_report, confusion_matrix


Benchmarks = ["RS232-T1000","RS232-T1100","RS232-T1200","RS232-T1300","RS232-T1400","RS232-T1500","RS232-T1600","s15850-T100","s35932-T100","s35932-T200","s35932-T300","s38417-T100","s38417-T200","s38417-T300","s38584-T100","s38584-T200","s38584-T300"]

for benchmark in Benchmarks:
	bankdata = pd.read_csv("/Users/reza/Desktop/Thesis/Raw/"+benchmark+".raw")
	X = bankdata.drop('Class', axis=1)
	y = bankdata['Class']
	X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.9)
	loaded_model = pickle.load(open("/Users/reza/Desktop/Thesis/trainedModels/"+benchmark+"-17.sav", 'rb'))
	y_pred = loaded_model.predict(X_test)
	# print(confusion_matrix(y_test,y_pred))s
	tn, fp, fn, tp = confusion_matrix(y_test, y_pred).ravel()
	# print(confusion_matrix(y_test,y_pred))
	tpr = tp/(tp+fn)
	tnr = tn/(tn+fp)
	print(benchmark,"\t",fn,"\t",tp,"\t",int(tpr*100),"%\t",int(tnr*100),"%")
	# print(benchmark,"\t",int(tpr*100),"%\t",int(tnr*100),"%")

