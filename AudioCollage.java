public class AudioCollage {
    // Returns a new array that rescales a[] by a factor of alpha.
    public static double[] amplify(double[] a, double alpha) {
        // create array amp that rescales a with alpha
        int n = a.length;
        double[] amp = new double[n];
        for (int i = 0; i < n; i++) {
            amp[i] = a[i] * alpha;
        }
        // return the rescaled
        return amp;
    }

    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {

        // create a new array(copy) rev that reverses contents of a to rev
        int n = a.length;
        double[] rev = new double[n];
        for (int i = 0; i < n; i++) {
            rev[n - 1 - i] = a[i];
        }
        // return the reverse of the original
        return rev;
    }

    // Returns a new array that is the concatenation of a[] and b[].
    public static double[] merge(double[] a, double[] b) {
        // create array mer that is the concatenation of a[] and b[].
        int n = a.length + b.length;
        double[] mer = new double[n];
        // fill contents of a to mer
        for (int i = 0; i < a.length; i++) {
            mer[i] = a[i];
        }
        // fill contents of b to mer
        for (int i = a.length; i < n; i++) {
            mer[i] = b[i - a.length];
        }
        // return the concatenation
        return mer;
    }

    // Returns a new array that is the sum of a[] and b[],
    // padding the shorter array with trailing 0s if necessary.
    public static double[] mix(double[] a, double[] b) {
        // create a new array mics that mixes a and b
        int length = Math.max(a.length, b.length);
        int min = Math.min(a.length, b.length);
        int n = length;
        double[] mics = new double[n];
        // add a and b
        for (int i = 0; i < min; i++) {
            mics[i] = a[i] + b[i];
        }

        if (a.length > b.length) {
            for (int i = min; i < a.length; i++) {
                mics[i] = a[i];
            }
        }
        else if (a.length < b.length) {
            for (int i = min; i < b.length; i++) {
                mics[i] = b[i];
            }
        }
        // return the padding
        return mics;
    }

    // Returns a new array that changes the speed of a[] by a factor of alpha.
    public static double[] changeSpeed(double[] a, double alpha) {
        // create array amp that changes speed of a with alpha
        int n = a.length;
        double m = n / alpha;
        int k = (int) Math.floor(m);
        double[] speed = new double[k];
        // fill a into speed
        for (int i = 0; i < k; i++) {
            int p = (int) Math.floor(i * alpha);
            speed[i] = a[p];
        }
        // returns the new array speed
        return speed;
    }

    // clamp that sets sounds >+1.0 to 1.0 and those<-1.0 to -1.0
    private static double[] clamp(double[] samples) {
        int n = samples.length;
        double[] s = new double[n];
        for (int i = 0; i < n; i++) {
            s[i] = samples[i];
        }
        for (int i = 0; i < n; i++) {
            if (s[i] > 1.0) {
                s[i] = 1.0;
            }
            else if (s[i] < -1.0) {
                s[i] = -1.0;
            }
        }

        return s;
    }

    // Creates an audio collage and plays it on standard audio.
    // See below for the requirements.
    public static void main(String[] args) {
        // read a wave file and extract its samples
        double[] samples1 = StdAudio.read("cow.wav");
        double[] play1 = amplify(samples1, 0.5);
        double[] samples2 = StdAudio.read("afrobeat.wav");
        double[] play2 = reverse(samples2);
        double[] samples3 = StdAudio.read("glass.wav");
        double[] play3 = changeSpeed(samples3, 0.5);
        double[] samples4 = StdAudio.read("scratch.wav");
        double[] play4 = merge(samples1, samples4);
        double[] samples5 = StdAudio.read("piano.wav");
        double[] play5 = mix(samples5, samples2);
        double[] play6 = merge(play5, samples5);
        double[] samples7 = StdAudio.read("doh.wav");
        double[] play7 = merge(play6, play5);
        double[] play8 = clamp(play7);
        // play the audio
        StdAudio.play(play8);
    }
}
