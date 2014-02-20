import java.util.LinkedList;

public class GuitarHeroVisualiser {
      public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
      
      public static void main(String[] args) {

          // create 37 guitar strings, like a keyboard
          double CONCERT_A = 440.0;

          GuitarString[] strings = new GuitarString[37];
          for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2,((double)(i - 24.0) / 12.0)));
          }
          LinkedList<Double> wave = new LinkedList<Double>();
          for (int i = 0; i < 500; i++) {
            wave.push((double)0);
          }
          int first = 0;
          int last = 0;
          StdDraw.setXscale(0, 500);
          StdDraw.setYscale(-250, 250);
          int looper = 0;
          boolean mute = false;
          while (true) {
              if (looper > 10000000)
                looper = 0;
              else 
                looper++;
              // check if the user has typed a key; if so, process it   
              if (StdDraw.hasNextKeyTyped()) {
                  char key = StdDraw.nextKeyTyped();
                  if (key == '1')
                    mute = true;
                  if (keyboard.indexOf(key) > -1)
                    strings[keyboard.indexOf(key)].pluck();
              }

              // compute the superposition of samples
              double sample = 0;
              for (int i = 0; i < strings.length; i++) {
                if (mute) 
                  strings[i].mute();
                else 
                  sample += strings[i].sample();
              }
              mute = false;
              // play the sample on standard audio
              StdAudio.play(sample);

              // shift the previous wave and draw new sample
              wave.push(sample);
              wave.remove(wave.size()-1);
              if (looper % 1000 == 0) {
              StdDraw.show(0);
              StdDraw.setPenColor(255, 255, 255);
              StdDraw.filledSquare(250, 0, 251);
              StdDraw.setPenColor();
                for (int i = 0; i < wave.size() - 1; i++) {
                  StdDraw.line(i, wave.get(i)*150, (i + 1), wave.get(i + 1)*150);
                }
              StdDraw.show(0);
              }
              // advance the simulation of each guitar string by one step   
              for (int i = 0; i < strings.length; i++)
                strings[i].tic();
              }
       }
  }
