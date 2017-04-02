
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
import ddf.minim.*;

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
