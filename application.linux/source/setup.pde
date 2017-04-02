void setup() {

  // use P3D because it works better
  size(500, 375, P3D);

  // higher framerate helps get more.
  frameRate(200);
  


  // get fullscreen exclusive mode
  setFullScreen( true );

  // change resolution to 640, 480
  setResolution( 640, 480 );

  // let ctrl+f switch to window mode
  createFullScreenKeyBindings();

  //see what cams are available
  //println(Capture.list()); 

  // Create MovieMaker object with size, filename,
  // compression codec and quality, framerate

  cam1 = new MovieMaker(this, width, height, "cam1.mov", 200, MovieMaker.MOTION_JPEG_B, MovieMaker.LOW, 200);
  //cam2 = new MovieMaker(this, width, height, "cam2.mov", 200, MovieMaker.MOTION_JPEG_B, MovieMaker.LOW, 200);
  init = true;


  // If no device is specified, will just use the default.
  String Vid1 = "IIDC FireWire Video";
  //String Vid2 = "USB Video Class Video";  
 
  webCam1 = new Capture(this,width, height,Vid1);
  //webCam2 = new Capture(this,width, height,Vid2);

  // cam.settings();

  background(0);

  //load the font to use
  
  font = loadFont("CenturyGothic-12.vlw"); 
}


