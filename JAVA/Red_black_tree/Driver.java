
import java.io.*;
import java.util.*;

public class Driver
{
    // Static test methods, easily called via bluej right-click.
    public static void testBST() throws Exception
    { testAndCheck("PBST"); }
    public static void testPRedBlackBST() throws Exception
    { testAndCheck("PRedBlackBST"); }

    // In case we run it from the command line:
    public static void main(String[] args) throws Exception
    { testPRedBlackBST(); }

    // The constructor and remaining methods are private,
    // so they don't clutter up the bluej interface.
    private Driver() {}

    // So that we may capture our output, we print to this stream.
    static PrintStream out = System.out;

    private static void testAndCheck(String className) throws Exception
    {
        // Prepare to capture output as a string.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            out = new PrintStream(new TwoOutputStream(System.out, baos));
            testButNoCompare(className);
            out.flush();
        } finally {
            out = System.out;
        }
        String output = baos.toString();
        // Hack to read text file contents (with no ^Z) to a string:
        String fName = "expect" + className + ".txt";
        out.println();
        out.println("Comparing your output to " + fName + " ...");
        String expect = new Scanner(new File(fName)).useDelimiter("\\Z").next();
        // Compare them line by line, avoiding line-ending issues.
        Scanner elines = new Scanner(expect.trim());
        Scanner olines = new Scanner(output.trim());
        int line = 0;
        while (elines.hasNextLine() || olines.hasNextLine())
        {
            ++line;
            String eline = elines.hasNextLine() ? elines.nextLine() : "END";
            String oline = olines.hasNextLine() ? olines.nextLine() : "END";
            if (!eline.equals(oline))
            {
                out.println("Your output differs from expected on line " + line);
                out.println("   output: " + oline);
                out.println(" expected: " + eline);
                return;
            }
        }
        out.println("Good, you had the expected test output.");
    }

    private static void testButNoCompare(String className)
    {
        PBST<String,Integer> first, last;
        if (className.equals("PBST"))
            first = new PBST<String,Integer>();
        else if (className.equals("PRedBlackBST"))
            first = new PRedBlackBST<String,Integer>();
        else
            throw new RuntimeException("Unknown className " + className);
        out.println("Testing " + className);
        // Create a history of PBST objects, and check each.
        String input = "SEARCHEXAMPLE";
        int N = input.length();
        int nodes0 = PBST.nodes();

        // We keep the history in an ArrayList rather than a raw
        // array here, to avoid some compiler warnings.
        ArrayList<PBST<String, Integer>> h =
            new ArrayList<PBST<String, Integer>>();
        out.println("Phase 1: book 'SEARCHEXAMPLE' input");
        out.println();
        out.println("h[0] = new " + className + "();");
        first.check();
        h.add(first);
        last = first;
        for (int val=0; val<N; ++val) {
            String key = input.substring(val,val+1);
            out.printf("h[%d] = h[%d].put(\"%s\", %d);%n",
                       val+1, val, key, val);
            h.add(last = last.put(key, val));
            // check class is same, and tree is internally consistent
            if (last.getClass() != first.getClass())
                last.bug("got tree of class " + last.getClass().getName());
            last.check();
        }
        out.printf("%nAll these trees are still usable:%n%n");
        for (int val=0; val <= N; ++val) {
            // Note that printing tree h.get(val) implicitly uses its
            // constructor.
            out.printf("h[%d] is %s%n", val, h.get(val));
            out.printf("h[%d].rank(\"S\") == %d, ", val,
                              h.get(val).rank("S"));
            out.printf("h[%d].get(\"E\") == %s%n", val,
                              h.get(val).get("E"));
        }
        int nodes1 = PBST.nodes();
        out.printf("%nCreated %d nodes in Phase 1.%n%n", nodes1-nodes0);

        // Note we start over at 'first' again.
        out.println("Phase 2: starting with h[0], inserting " +
                           "100 keys in increasing order");
        PBST<String,Integer> t = first;
        for (int i=100; i<200; ++i)
            t = t.put((""+i).substring(1), i-100);
        int nodes2 = PBST.nodes();
        out.printf("Created %d nodes in Phase 2.%n%n", nodes2-nodes1);
        t.check();
        out.println("final check() succeeded");
    }

    // This class is used above, it lets us print to two destinations at once.
    static class TwoOutputStream extends OutputStream
    {
        OutputStream os1, os2;
        TwoOutputStream(OutputStream o1, OutputStream o2) throws IOException
        { os1=o1; os2=o2; }
        public void close() throws IOException
        { os1.close(); os2.close(); }
        public void flush() throws IOException
        { os1.flush(); os2.flush(); }
        public void write(int b) throws IOException
        { os1.write(b); os2.write(b); }
        public void write(byte[] b, int o, int l) throws IOException
        { os1.write(b,o,l); os2.write(b,o,l); }
    }


}

