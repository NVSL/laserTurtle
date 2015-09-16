package turtleClasses;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by sciencectn on 9/14/15.
 */
public class DXFfile {

    private PrintWriter file;
    private int line_handle;

    /**
     * Create the DXF file and write the header
     * @param filename
     */
    public DXFfile(String filename) throws FileNotFoundException{
        line_handle = 256;
        file = new PrintWriter(filename);
        file.print(DXFconstants.r14_layers_header);
        insertCode("0", "TABLE");
        insertCode("2", "LAYER");
        insertCode("5", "2");
        insertCode("330", "0");
        insertCode("100", "AcDbSymbolTable");
        insertCode("70","2");
        insertCode("0", "LAYER");
        insertCode("5", "10");
        insertCode("330", "2");
        insertCode("100", "AcDbSymbolTableRecord");
        insertCode("100", "AcDbLayerTableRecord");
        insertCode("2", "LayerOne");  //First layer. I'm just modifying the 2 layer example
        insertCode("70", "0");
        insertCode("62", "7");
        insertCode("6", "CONTINUOUS");
        insertCode("0", "LAYER");
        insertCode("5", "10");
        insertCode("330", "2");
        insertCode("100", "AcDbSymbolTableRecord");
        insertCode("100", "AcDbLayerTableRecord");
        insertCode("2", "LayerTwo");  //The other layer (unused)
        insertCode("70", "0");
        insertCode("62", "7");
        insertCode("6", "CONTINUOUS");
        insertCode("0","ENDTAB");
        insertCode("0","ENDSEC");
        file.print(DXFconstants.r14_layers_blocks);
    }

    public void addLineSegment(PathSegment segment){
        addLineSegment(segment.getLine().getX1(),
                        segment.getLine().getY1(),
                        segment.getLine().getX2(),
                        segment.getLine().getY2());

    }

    public void addLineSegment(double x1, double y1, double x2, double y2){
        insertCode(  "0", "LINE" );
        insertCode(  "8", "LayerOne" );
        insertCode( "62", "4" );
        insertCode(  "5", Integer.toHexString(line_handle) );
        insertCode("100", "AcDbEntity" );
        insertCode("100", "AcDbLine" );
        insertCode( "10", x1 );
        insertCode( "20", y1 );
        insertCode( "30", "0.0" );
        insertCode( "11", x2 );
        insertCode( "21", y2 );
        insertCode( "31", "0.0" );
        line_handle += 1;
    }


    public void close(){
        file.print(DXFconstants.r14_layers_footer);
        file.close();
    }

    /******* PRIVATE SECTION ********/
    private void insertCode(String code, String value){
        file.println(code);
        file.println(value);
    }

    private void insertCode(String code, double value){
        file.println(code);
        file.println(value);
    }

}

