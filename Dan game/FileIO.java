import java.io.*;
import java.util.*;

public class FileIO
{
    public static void main()
    {
        ArrayList bob = loadFile();
        for (int i=0; i<bob.size(); i++)
            System.out.println(i+1 + ":\t" + bob.get(i));
    }

    public static ArrayList loadFile()
    {
       
        
        String line;
        ArrayList file = new ArrayList();

        try
        {    
            BufferedReader in = new BufferedReader(new FileReader("mydata.txt"));

            if (!in.ready())
                throw new IOException();

            while ((line = in.readLine()) != null)
                file.add(line);

            in.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
            return null;
        }

        return file;
    }
}


