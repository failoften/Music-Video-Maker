import processing.core.*; 
import processing.xml.*; 

import processing.core.*; 
import processing.video.*; 
import java.io.*; 
import ddf.minim.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class savie_movie_6 extends PApplet {






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

public void draw() {       

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


    if((myMovie.time() >= randomScene1 && myMovie.time() < (randomScene1 + 0.05f)) || (myMovie.time() >= randomScene2 && myMovie.time() <= (randomScene2 + 0.05f)) || (myMovie.time() >= randomScene3 && myMovie.time() <= (randomScene3 + 0.05f)) || (myMovie.time() >= randomScene4 && myMovie.time() <= (randomScene4 + 0.05f))) {
      rectAlpha=150;
      myMovie.speed(2);
    } 

    else if((myMovie.time() >= (randomScene1 + 0.1f) && myMovie.time() < randomScene2) || (myMovie.time() >= (randomScene2 + 0.1f) && myMovie.time() < randomScene3) || (myMovie.time() >= (randomScene3 + 0.1f))) {
      myMovie.speed(0.3f);
    }


    if(myMovie.time() == 0.0f) {
      rectAlpha = 90;
      myMovie.speed(0.3f);
    }

    if(rectAlpha>=5) {
      fill(255,rectAlpha);
      rect(0,0,width,height);
      rectAlpha = rectAlpha-5;
      myMovie.speed(0.03f);
    }



  }
}
/**
 * FullScreen support for processing. 
 * 
 * For a detailed reference see http://www.superduper.org/processing/fullscreen_api/
 * 
 * - setFullScreen( true | false ) 
 *   goes to / leaves fullscreen mode
 *
 * - setResolution( x, y ) 
 *   sets the resolution 
 * 
 * - createFullScreenKeyBindings() 
 *   links ctrl+f (or apple+f for macintosh) to enter/leave fullscreen mode
 *
 * WARNING: This package conflicts with the processing "present" option. If you want
 * fullscreen from the start use like this: 
 * 
 * void setup(){
 *   setFullScreen( true );               // get fullscreen exclusive mode
 *   setResolution( 640, 480 );           // change resolution to 640, 480
 *   createFullScreenKeyBindings();       // let ctrl+f switch to window mode
 * }
 * 
 * LIMITATIONS: 
 * - The size of the sketch can not be changed, when your sketch is
 *   smaller than the screen it will be centered. 
 * - The ESC key exits the sketch, this is processing standard. 
 * - Requires min. Java 1.4 to be installed work
 * - Only works for applications (not for applets)
 * 
 * by hansi, http://www.superduper.org,  http://www.fabrica.it
 *
 */

// We use this frame to go to fullScreen mode...
Frame fsFrame = new Frame(); 
GraphicsDevice fsDevice = fsFrame.getGraphicsConfiguration().getDevice(); 
//AWTEventListener fsKeyListener;

// desired x/y resolution
int fsResolutionX, fsResolutionY; 

// the first time wait until the frame is displayed
boolean fsIsInitialized; 

// bind the keys? 
boolean fsBindKeys; 

// Setup everything...
{
  fsFrame.setTitle( "FullScreen" ); 
  fsFrame.setUndecorated( true ); 
  fsFrame.setBackground( Color.black ); 
  fsFrame.setLayout( null ); 
  fsFrame.addWindowListener( new FSWindowListener() );
  fsFrame.setSize( fsDevice.getDisplayMode().getWidth(), fsDevice.getDisplayMode().getHeight() ); 
}


/**
 * Are we in FullScreen mode? 
 *
 * @returns true if so, yes if not
 */
public boolean isFullScreen(){
  return fsDevice.getFullScreenWindow() == fsFrame; 
}


/**
 * FullScreen is only available is applications, not in applets! 
 *
 * @returns true if fullScreen mode is available
 */
public boolean isFullScreenAvailable(){
  return frame != null;
}

  
/**
 * Enters/Leaves fullScreen mode. 
 *
 * @param fullScreen true or false
 * @returns true on success
 */
public boolean setFullScreen( boolean fullScreen ){
  
  // If it's called from setup we wait until the applet initialized properly
  if( frameCount == 0 && fullScreen == true ){
    new FSWaitForInitThread().start(); 
    
    return true; 
  }
  
  
  
  if( fullScreen == isFullScreen() ){
    // no change required! 
    return true; 
  }
  else if( fullScreen ){
    // go to fullScreen mode...
    
    if( isFullScreenAvailable() ){
      fsDevice.setFullScreenWindow( fsFrame ); 
      
      // remove applet from processing frame and attach to fsFrame
      frame.removeAll(); 
      frame.setVisible( false ); 
      frame.setSize( width + frame.insets().left + frame.insets().right, 
                     height + frame.insets().top + frame.insets().bottom ); 
      fsFrame.add( this ); 
      this.requestFocus(); 
      
      // set nice resolution...
      setResolution( 0, 0 ); 
      
      // update texture space
      processing.core.fullscreen_texturehelper.update( this ); 
      
      
      return true; 
    }
    else{
      System.err.println( "Fullscreen mode not available" ); 
      return false; 
    }
  }
  else{
    // remove applet from fsFrame and attach to processing frame
    fsFrame.removeAll(); 
    frame.add( this ); 
    this.setLocation( frame.insets().left, frame.insets().top ); 
    this.requestFocus(); 
    
    fsDevice.setFullScreenWindow( null ); 
    fsFrame.setVisible( false ); 

    frame.setVisible( true ); 

    processing.core.fullscreen_texturehelper.update( this ); 
    return true; 
  }
}


/**
 * Change display resolution. Only sets the resolution, use 
 * setFullScreen( true ) to go to fullscreen mode! 
 *
 * If you're not in fullscreen mode it memorizes the resolution and sets
 * it the next time you go in fullscreen mode
 *
 * @returns true if resolution change succeeded, false if not
 */
public boolean setResolution( int xRes, int yRes ){
  if( xRes > 0 && yRes > 0 ){
    fsResolutionX = xRes; 
    fsResolutionY = yRes; 
  }
  
  
  // only change in fullscreen mode
  if( !isFullScreen() ){
    return false; 
  }
  
  
  // Change resolution only if values are somehow meaningfull
  if( fsResolutionX <= 0 || fsResolutionY <= 0 ){
    setLocation( ( fsDevice.getDisplayMode().getWidth() - width ) / 2, ( fsDevice.getDisplayMode().getHeight() - height ) / 2 ); 
    return false; 
  }
  
  DisplayMode modes[ ] = fsDevice.getDisplayModes(); 
  DisplayMode theMode = null; 

  for( int i = 0; i < modes.length; i++ ){
    
    if( modes[ i ].getWidth() == fsResolutionX && modes[ i ].getHeight() == fsResolutionY ){
      theMode = modes[ i ]; 
    }
  }


  // Resolution not supported? 
  if( theMode == null ){
    System.err.println( "Display mode not supported: " + fsResolutionX + "x" + fsResolutionY ); 
    setLocation( ( fsDevice.getDisplayMode().getWidth() - width ) / 2, ( fsDevice.getDisplayMode().getHeight() - height ) / 2 ); 
    return false; 
  }


  // Wait until we are in fullScreen exclusive mode..
  try{
    fsDevice.setDisplayMode( theMode ); 
    fsFrame.setSize( fsResolutionX, fsResolutionY ); 
  }
  catch( Exception e ){
    System.err.println( "Failed to go to fullScreen mode" ); 
    e.printStackTrace(); 
    return false; 
  }

  setLocation( ( fsDevice.getDisplayMode().getWidth() - width ) / 2, ( fsDevice.getDisplayMode().getHeight() - height ) / 2 ); 
  return true; 
}


/**
 * Add keyboard short cuts (CTRL + F to toggle fullscreen mode)
 * Watch out - if you call this function twice weird things might happen! 
 */
public void createFullScreenKeyBindings(){
  fsBindKeys = true; 
}




final static int fsControlKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

public void processKeyEvent( KeyEvent e ){
  
  // Are we allowed to look for key events? 
  if( fsBindKeys == false ){
    super.processKeyEvent( e ); 
  }
  
  // Catch the ESC key if in fullscreen mode
  else if( e.getKeyCode() == e.VK_ESCAPE ){
    if( isFullScreen() ){
      if( e.getID() == KeyEvent.KEY_RELEASED ){
        setFullScreen( false ); 
      }
    }
    else{
      super.processKeyEvent( e ); 
    }
  }
  
  // catch the cmd+f combination (alt+enter or ctrl+f for windows)
  else if( e.getID() == KeyEvent.KEY_PRESSED ){
    if( ( e.getKeyCode() == e.VK_F && e.getModifiers() == fsControlKey ) ||
        ( platform == WINDOWS && e.getKeyCode() == e.VK_ENTER && e.getModifiers() == e.VK_ALT ) ){
      // toggle fullscreen! 
      setFullScreen( !isFullScreen() ); 
    }
    else{
      super.processKeyEvent( e ); 
    }
  }
  else{
    super.processKeyEvent( e ); 
  }
}


/**
 * A window listener for the fullscreen window, that 
 * calls the exit() function of processing when the window 
 * is closed (using alt+f4, apple+w, or whatever)
 */
class FSWindowListener extends WindowAdapter{
  public void windowClosing( WindowEvent e ){
    // let processing exit! 
    exit(); 
  }
}



/**
 * A thread that invokes the setFullScreen() functionality delayed, 
 * in case it's called from setup()
 */
class FSWaitForInitThread extends Thread{
  
  public void run(){
    while( frameCount < 5 ){
      try{
        Thread.sleep( 1000 ); 
      }
      catch( Exception e ){
        System.err.println( "failed to go to fullscreen mode" ); 
        return; 
      }
    }
    
    if( !setFullScreen( true ) ){
      System.err.println( "failed to go to fullscreen mode" );
    }
  }
}

public void movieEvent(Movie myMovie) {
  myMovie.read();
}


public void keyPressed() {
  if (key == ' ' && recordCam == 0) {
    theCamera();
  }
}



public void theCamera() {

  if(recordCam == 1) {                                                    // STOP WATCHING AND START RECORDING

    // Finish the movie
    cam1.finish();
    //cam2.finish();  



    // stop recording and show the movie       
    recordCam = 0;

    myMovie = new Movie(this, "cam1.mov");  
    myMovie.loop();
    myMovie.speed(0.03f);

    randomScene1 = random(0.1f,0.2f);
    randomScene2 = random(0.5f,0.6f);
    randomScene3 = random(0.7f,0.8f);

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

/**
  * This sketch demonstrates how to play an internet radio stream with Minim. You load the stream like any other 
  * file and then just use <code>play()</code> to start playing it. You can still use the other methods of a 
  * <code>Playable</code> object and it shouldn't break anything, but it also won't accomplish much. For example, 
  * rewinding streaming audio simply reloads the stream, there's no TIVO-like buffer that is kept. Not all internet 
  * radio streams will work in an applet. I've used this crappy techno station for this example because my favorite 
  * internet radio, Cliq-hop on Soma.fm doesn't work in a browser. Another limitation of applets is that they must 
  * be signed to be able to access remote hosts. If your applet is running from www.foo.com and you want to access 
  * the mp3 stream at www.bar.com, you must sign the applet. If you are just trying to load a file from www.foo.com/file, 
  * you shouldn't need to sign it.
  */
/*


AudioPlayer radio;

void setup()
{
  size(1024, 200);
  // always start Minim first
  Minim.start(this);
  // load a URL, default sample buffer size is 1024
  radio = Minim.loadFile("http://64.236.34.97:80/stream/1003");
  // play the file
  radio.play();
}

void draw()
{
  background(0);
  stroke(255);
  // draw the waveforms
  // the values returned by left.get() and right.get() will be between -1 and 1,
  // so we need to scale them up to see the waveform
  // note that if the file is MONO, left.get() and right.get() will return the same value
  for(int i = 0; i < radio.left.size()-1; i++)
  {
    line(i, 50 + radio.left.get(i)*50, i+1, 50 + radio.left.get(i+1)*50);
    line(i, 150 + radio.right.get(i)*50, i+1, 150 + radio.right.get(i+1)*50);
  }
}

void stop()
{
  radio.close();
  super.stop();
}
*/
public void setup() {

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


  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "savie_movie_6" });
  }
}
