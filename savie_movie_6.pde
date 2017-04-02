import processing.core.*;
import processing.video.*;
import java.io.*;


//make a font variable
PFont font;
int i = 0;
float theTime = 0;

// make a little splash screen 
int splashscreen = 1;

boolean init;
int rectAlpha = 150;
float nowTime = 0;
int vidMode = 0;

float randomScene1 = 0;
float randomScene2 = 0;
float randomScene3 = 0;
float randomScene4 = 0;

//declare MovieMaker obj
MovieMaker cam1;
MovieMaker cam2;

//declare camera
Capture webCam1;
Capture webCam2;

//declare movie to save
Movie myMovie; 
Movie myMovie2; 

// var to tell if camera should be recording or not
int recordCam = 1;
float timePlayed = 0;

