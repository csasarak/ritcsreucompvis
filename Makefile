#CC=javac -cp $HOME/jai-1_1_3/lib/:$HOME/ImageJ/:.
CC=javac

all: ImageProcessor

ImageProcessor: ImageProcess GrayConvertProcess InvertProcess MonoscaleProcess MorphologicalCloseProcess SobelDetectionProcess
	$(CC) ImageProcessor.java

ProcessList: ImageProcess
	$(CC) ProcessList.java

ImageProcess: 
	$(CC) ImageProcess.java

GrayConvertProcess: 
	$(CC) GrayConvertProcess.java

InvertProcess: 
	$(CC) InvertProcess.java

MonoscaleProcess: 
	$(CC) MonoscaleProcess.java

MorphologicalCloseProcess: 
	$(CC) MorphologicalCloseProcess.java

SobelDetectionProcess: 
	$(CC) SobelDetectionProcess.java

clean:
	rm *.class
