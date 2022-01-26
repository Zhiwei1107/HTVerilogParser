1.Home directory includes:

1.1 src
	---s38584_scan.v	for both Trojan inserted and free designs	

2.Trojan
Trojan Description
	The Trojan trigger is a high active region detector. Whenever the number of transitions 
	over some internal signals are above a certain value in a short time interval (50 clock cycles), 
	the  Trojan gets activated. After Trojan activation, the Trojan payload leaks the value of one internal signal through a primary output.  

Trojan Taxonomy
	Insertion phase: Design
	Abstraction level: gate level 
	Activation mechanism: Internally conditionally triggered
	Effects: Change Functionality, Leak Information
	Location: Processor
	Physical characteristics: Functional