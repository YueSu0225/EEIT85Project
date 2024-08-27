package tw.RC.tutor;

import java.awt.image.ImagingOpException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.sql.SQLException;
import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;

import javax.management.OperationsException;

public class RC39 {

	public static void main(String[] args) {

	}

}
class RC391 {
	void m1() throws Exception{}
}
class RC392 extends RC391 {//override的時候,子類別拋出例外範圍要比父類別小&少
	void m1() throws IllegalFormatException, ObjectStreamException, SQLException{}
}