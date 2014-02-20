public class GuitarHero {
      public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
      
      public static void main(String[] args) {

          // create 37 guitar strings, like a keyboard
          double CONCERT_A = 440.0;

          GuitarString[] strings = new GuitarString[37];
          for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2,((double)(i - 24.0) / 12.0)));
          }

          while (true) {

              // check if the user has typed a key; if so, process it   
              if (StdDraw.hasNextKeyTyped()) {
                  char key = StdDraw.nextKeyTyped();
                  if (keyboard.indexOf(key) > -1)
                    strings[keyboard.indexOf(key)].pluck();
              }

              // compute the superposition of samples
              double sample = 0;
              for (int i = 0; i < strings.length; i++) 
                sample += strings[i].sample();
  
              // play the sample on standard audio
              StdAudio.play(sample);
  
              // advance the simulation of each guitar string by one step   
              for (int i = 0; i < strings.length; i++)
                strings[i].tic();
          }
       }
  }
