package com.imagedownload;
import com.libsondolib.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.beust.jcommander.JCommander;

public class download {
    public static void main(String[] argv) throws Exception {
    	
        Args args = new Args();
        
        String thread = "1";

        JCommander.newBuilder()
                .addObject(args)
         
                .build()
                .parse(argv);

    Runnable task1 = new Task();
    
   if(args.mode.equalsIgnoreCase("one-thread") | args.mode.isEmpty()){
thread = "1";
   } else
   if(args.mode.equalsIgnoreCase("multi-thread")){
       thread = args.count;
   } else
	   throw new Exception("Input Mode");
   

        ((Task) task1).dirName =args.dirName;
((Task) task1).url = args.url;
        ExecutorService executorService = Executors.newFixedThreadPool(Integer.parseInt(thread));
        long startTime = System.currentTimeMillis();
        executorService.submit(task1);

        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);  //Total execution time in milli seconds

        System.out.println("It took " + duration + "Miliseconds to download image");

        executorService.shutdown();

    }

}