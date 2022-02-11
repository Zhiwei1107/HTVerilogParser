#test and create table for infected new files!
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import pickle
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC

Benchmarks = ["input","fifo","output","arbiter"]
tableTPR=""
tableTNR=""
filename=""
tableTPR="input\tTP\tFN\tfifo\tTP\tFN\toutput\tTP\tFN\tarbiter\tTP\tFN\n"
tableTNR="input"+"\tfifo"+"\toutput"+"\tarbiter\n"
addressOfFile=""
addressOfFile1=""
for i in range(0,8):

	# print(bench)
	for bench in Benchmarks:
		if bench=="input":
			filename="input_router"
		elif bench =="fifo":
			filename="vc_buffer"
		elif bench =="output":
			filename="output_module"
		elif bench =="arbiter":
			filename="rr_arbiter"
		if i==0:
			addressOfFile="/Users/reza/Desktop/Thesis/RaveNOCVerilog/Infected/"+bench+"/"+filename+".raw"
			addressOfFile1="/Users/reza/Desktop/Thesis/RaveNOCVerilog/Infected/"+bench+"/"+filename+".v"
		else:
			addressOfFile="/Users/reza/Desktop/Thesis/RaveNOCVerilog/Infected/"+bench+"/"+filename+str(i)+".raw"
			addressOfFile1="/Users/reza/Desktop/Thesis/RaveNOCVerilog/Infected/"+bench+"/"+filename+str(i)+".v"

		bankdata = pd.read_csv(addressOfFile)
		# bankdata = pd.read_csv("/Users/reza/Desktop/Thesis/ResultsForScikit/s35932-T200.nomral")
		# print(bankdata.shape)
		# print(bankdata)

		X = bankdata.drop('Class', axis=1)
		y = bankdata['Class']
		X = X.drop('FFO', axis=1)
		X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.9)
		loaded_model = pickle.load(open("/Users/reza/Desktop/withoutFFO.sav", 'rb'))

		y_pred = loaded_model.predict(X_test)
		# print("###\n")
		# print(y_pred)

		from sklearn.metrics import classification_report, confusion_matrix
		# print(confusion_matrix(y_test,y_pred))
		tn, fp, fn, tp = confusion_matrix(y_test, y_pred).ravel()
		# print(confusion_matrix(y_test,y_pred))
		tpr = tp/(tp+fn)
		tnr = tn/(tn+fp)

		file = open(addressOfFile1)
		data = file.read()

		tableTPR=tableTPR+str("{:.2f}".format(tpr))+","+str(data.count("DFFX2")+data.count("DFFLES2"))+"\t"+str(tp)+"\t"+str(fn)+"\t"
		tableTNR=tableTNR+str("{:.2f}".format(tnr))+"\t"

	tableTPR=tableTPR+"\n"
	tableTNR=tableTNR+"\n"
print("TPR Table:\n")
print(tableTPR)
print("\n\n")
print("TNR Table:\n")
print(tableTNR)
		# print(tpr," , ",tnr)
	# print("-----------------------------")
# result = loaded_model.score(X_test,y_test)
# print(result)

# def getMassResults(model,files):
# 	for i in range(0,len(files)):

