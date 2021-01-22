
import com.beust.jcommander.Parameter;

    @Parameter(seperators = "=")
public class Args {

    @Parameter(name = "--mode")
    required = true,
    public String mode;
    

    @Parameter(name = "--files")
    required = true,
    public String url;

    @Parameter(name = "--count")
    public String thread;

    @Parameter(name = "--folder")
    required = true,
    public String dirName;


}
