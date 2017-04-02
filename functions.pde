
void movieEvent(Movie myMovie) {
  myMovie.read();
}


void keyPressed() {
  if (key == ' ' && recordCam == 0) {
    theCamera();
  }
}



void theCamera() {

  if(recordCam == 1) {                                                    // STOP WATCHING AND START RECORDING

    // Finish the movie
    cam1.finish();
    //cam2.finish();  



    // stop recording and show the movie       
    recordCam = 0;

    myMovie = new Movie(this, "cam1.mov");  
    myMovie.loop();
    myMovie.speed(0.03);

    randomScene1 = random(0.1,0.2);
    randomScene2 = random(0.5,0.6);
    randomScene3 = random(0.7,0.8);

    theTime = millis();  

  } 
  else if (recordCam == 0) {                                              // STOP RECORDING AND START WATCHING

    // if not recording and spacebar pressed, start recording again and stop playing
    myMovie.stop();

    if (init) {
      //Process p; 
      String cmd = "rm /Users/david/Documents/Processing/ITP/savie_movie_6/cam1.mov";
      //String cmd2 = "rm /Users/David/Documents/Processing/savie_movie_6/cam2.mov";
      Process p;
      //Process d;
      try{
        p = Runtime.getRuntime().exec(cmd);
        //d = Runtime.getRuntime().exec(cmd2);
        //System.out.println(p);
      }
      catch(IOException e){ 
        e.printStackTrace();
      }
    }
    println("yes, deleted!");
    delay(2000);

    cam1 = new MovieMaker(this, width, height, "cam1.mov", 200, MovieMaker.MOTION_JPEG_B, MovieMaker.MEDIUM, 100);
    //cam2 = new MovieMaker(this, width, height, "cam2.mov", 200, MovieMaker.MOTION_JPEG_B, MovieMaker.MEDIUM, 100);

    init=true;

    splashscreen = 1;

    recordCam = 1;
    timePlayed = millis();
    delay(20);
    background(0);
  }
}
