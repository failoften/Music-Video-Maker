void draw() {       

  if(recordCam == 1) {                                            // IF WE ARE RECORDING


    // read the image from the camera 
    webCam1.read();
    //webCam2.read();

    //add the image as a frame to the movie
    cam1.addFrame(webCam1.pixels,500,375);
    //cam2.addFrame(webCam2.pixels,500,375);

    nowTime = millis();
    if(nowTime >= timePlayed + 15000) {
      theCamera();
      recordCam = 0;
      vidMode = 0;
    }

    image(webCam1,0,0);
    

    if(splashscreen == 1) {
      //make a bar at the bottom - like MTV or something
      fill(0);

      rect(0, 300, width, 100);

      // write the font
      fill(255);
      textFont(font, 28); 
      text("ghetto record.", width/2, height/2+150);
    } 


  } 
  else {                                                            // IF WE ARE NOT RECORDING       AND       WE ARE PLAYING THE VIDEO

    delay(10);

    if(millis() >= (theTime + 30000)) {
      delay(10);
      theCamera();
    }


    // show the recorded movie

    // this does some weird opacity thing
    tint(255, 60);
    image(myMovie,0,0);
    filter(GRAY);
    //filter(INVERT);  //you can use GRAY, ERODE, DILATE, POSTERIZE, INVERT, THRESHOLD, OPAQUE or BLUR

    //fade in 
    // println(myMovie.time());


    if((myMovie.time() >= randomScene1 && myMovie.time() < (randomScene1 + 0.05)) || (myMovie.time() >= randomScene2 && myMovie.time() <= (randomScene2 + 0.05)) || (myMovie.time() >= randomScene3 && myMovie.time() <= (randomScene3 + 0.05)) || (myMovie.time() >= randomScene4 && myMovie.time() <= (randomScene4 + 0.05))) {
      rectAlpha=150;
      myMovie.speed(2);
    } 

    else if((myMovie.time() >= (randomScene1 + 0.1) && myMovie.time() < randomScene2) || (myMovie.time() >= (randomScene2 + 0.1) && myMovie.time() < randomScene3) || (myMovie.time() >= (randomScene3 + 0.1))) {
      myMovie.speed(0.3);
    }


    if(myMovie.time() == 0.0) {
      rectAlpha = 90;
      myMovie.speed(0.3);
    }

    if(rectAlpha>=5) {
      fill(255,rectAlpha);
      rect(0,0,width,height);
      rectAlpha = rectAlpha-5;
      myMovie.speed(0.03);
    }



  }
}
