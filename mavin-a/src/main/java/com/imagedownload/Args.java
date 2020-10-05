package com.imagedownload;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {

    @Parameter(names = "--mode")
    public String mode;

    @Parameter(names = "--files",
    		required = true)
    public String url;

    @Parameter(names = "--count")
    public String count;

    @Parameter(names = "--folder",
    		required = true)
    public String dirName;


}
