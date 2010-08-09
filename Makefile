#CC=javac -cp $HOME/jai-1_1_3/lib/:$HOME/ImageJ/:.
CC=javac

all: ImageProcessor.class

ImageProcessor.class: ImageProcess.class GrayConvertProcess.class MonoscaleProcess.class MorphologicalCloseProcess.class SobelDetectionProcess.class
	$(CC) ImageProcessor.java

ProcessList.class: ImageProces.class
	$(CC) ProcessList.java

ImageProcess.class: 
	$(CC) ImageProcess.java

GrayConvertProcess.class: 
	$(CC) GrayConvertProcess.java

MonoscaleProcess.class: 
	$(CC) MonoscaleProcess.java

MorphologicalCloseProcess.class: 
	$(CC) MorphologicalCloseProcess.java

SobelDetectionProcess.class: 
	$(CC) SobelDetectionProcess.java

clean:
	rm *.class
