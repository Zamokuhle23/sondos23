import com.beust.jcommander.Parameter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class download {
    public static void main(String[] argv) {

        Runnable task1 = new Task();

        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);


     
   String thread = args.thread;
        ((Task) task1).dirName =args.dirName;
((Task) task1).url = args.url;
        ExecutorService executorService = Executors.newFixedThreadPool(Integer.parseInt(thread));
        long startTime = System.currentTimeMillis();


        executorService.submit(task1);

        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);  //Total execution time in milli seconds

        System.out.println(duration);







        executorService.shutdown();

    }


//    public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl)
//            throws MalformedURLException, IOException {
//        BufferedInputStream in = null;
//        FileOutputStream fout = null;
//        try { in = new BufferedInputStream(new URL(fileUrl).openStream());
//            fout = new FileOutputStream(fileName);
//            byte data[] = new byte[1024];
//            int count;
//            while ((count = in .read(data, 0, 1024)) != -1) {
//                fout.write(data, 0, count);
//            }
//        } finally {
//            if ( in != null)
//                in .close();
//            if (fout != null)
//                fout.close();
//        }
//
//    }


}
