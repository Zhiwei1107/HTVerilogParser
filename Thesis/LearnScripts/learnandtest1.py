#learn  and test
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import pickle
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC

bestTPR =""
bestTNR=""
bestC =0.000001
bestG =0.000001
bestAverge=0.0001
Benchmarks = ["RS232-T1000","RS232-T1100","RS232-T1200","RS232-T1300","RS232-T1400","RS232-T1500","RS232-T1600","s15850-T100","s35932-T100","s35932-T200","s35932-T300","s38417-T100","s38417-T200","s38417-T300","s38584-T100","s38584-T200","s38584-T300"]
outfile="/Users/reza/Desktop/range1.txt"
# bench="s35932-T300"
Crange  = [0.001,0.002,0.005,0.01,0.02,0.05,0.1,0.2,0.5,1,2,4,8,16,32,40]
gammaRange = [0.001,0.002,0.005,0.01,0.02,0.05,0.1,0.2,0.5,1,2,4,8,16,32,40]
for bench in Benchmarks:
	average=0.00000000001
	for Cparam in Crange:
		for Gammaparam in gammaRange:

			tableTPR="BenchmarkName\tinput\tTP\tFN\t\n"
			tableTNR="name\tinput\n"
			
			bankdata = pd.read_csv("/Users/reza/Desktop/Thesis/unifiedBalancedOfEveryBenchmark/17files/"+bench+".unk")
					# print(bankdata.shape)
					# print(bankdata.head())

			X = bankdata.drop('Class', axis=1)
			y = bankdata['Class']
					

					# print(X.shape)
					# exit()
					# start_time = time.time()
			X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.01)

					# print(X_test)
			svclassifier = SVC(kernel='rbf',C=Cparam, gamma=Gammaparam)
			svclassifier.fit(X_train, y_train)

			filename = '/Users/reza/Desktop/prev-t1300.sav'
			pickle.dump(svclassifier, open(filename, 'wb'))
					# print("--- %s seconds ---" % (time.time() - start_time))


					# print(bench)
					# print(addressOfFile)
				
			bankdata = pd.read_csv("/Users/reza/Desktop/Thesis/ResultsForScikit/"+bench+".full")
							# bankdata = pd.read_csv("/Users/reza/Desktop/Thesis/ResultsForScikit/s35932-T200.nomral")
							# print(bankdata.shape)
							# print(bankdata)

			X = bankdata.drop('Class', axis=1)
			y = bankdata['Class']
			X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.9)
			loaded_model = pickle.load(open("/Users/reza/Desktop/prev-t1300.sav", 'rb'))

			y_pred = loaded_model.predict(X_test)					# print("###\n")
								# print(y_pred)

			from sklearn.metrics import classification_report, confusion_matrix
						# print(confusion_matrix(y_test,y_pred))
			tn, fp, fn, tp = confusion_matrix(y_test, y_pred).ravel()
							# print(confusion_matrix(y_test,y_pred))
			tpr = tp/(tp+fn)
			tnr = tn/(tn+fp)

			tableTPR=tableTPR+bench+"\t"+str("{:.2f}".format(tpr))+"\t"+str(tp)+"\t"+str(fn)+"\t\n"
			tableTNR=tableTNR+str("{:.2f}".format(tnr))+"\t\n"
			if(tpr>average):
								bestAverge=average;
								bestC=Cparam
								bestG=Gammaparam
								bestTPR=tableTPR
								bestTNR=tableTNR
	print("Best Average:"+str(bestAverge)+"\n")			
	print("Best C:"+str(bestC)+"\n")
	print("Best Gamma:"+str(bestG)+"\n")
	print("Best TPR:\n"+bestTPR+"\n")
	print("Best TNR:\n"+bestTNR+"\n")



			# with open(outfile, 'a') as newfile:
			# 	newfile.write("C="+str(Cparam)+", Gamma="+str(Gammaparam)+"\n")
			# 	newfile.write("TPR Table:\n")
			# 	newfile.write(tableTPR)
			# 	newfile.write("\n\n")
			# 	newfile.write("TNR Table:\n")
			# 	newfile.write(tableTNR)
			# 	newfile.write("-----------------------------")
			# result = loaded_model.score(X_test,y_test)
			# print(result)

			# def getMassResults(model,files):
			# 	for i in range(0,len(files)):

