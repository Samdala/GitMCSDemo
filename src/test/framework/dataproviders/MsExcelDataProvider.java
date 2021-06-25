package test.framework.dataproviders;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import test.configuration.Configuration;

/**
 * Implements the work with MS Excel old style (97-2003) in order to be used by descendants
 *
 */
public abstract class MsExcelDataProvider extends AbstractDataProvider {
	
	public static Iterator<Object[]> getTestData(String filePath) throws Exception {
		
		// Initialize the POI class from appropriate classpath resource
		InputStream stream = ClassLoader.getSystemResourceAsStream(
				"test/" 
				+ filePath 
				+ Configuration.getConfiguration(null).getAppLanguage() 
				+ ".xls"
				);
		POIFSFileSystem xmlReaderStream = new POIFSFileSystem(stream);
		
		// Get the first sheet
		HSSFWorkbook workBook = new HSSFWorkbook(xmlReaderStream);
		HSSFSheet sheet = workBook.getSheetAt(0);
		
		// Obtain the rows collection		
		Iterator<Row> rowIterator = sheet.rowIterator();		
		
		// Parse the first row and get the list of object attributes
		ArrayList<String> objectAttributes = new ArrayList<>();			
		HSSFRow row = (HSSFRow) rowIterator.next();
		
		Iterator<Cell> cellIterator = row.cellIterator();		
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			objectAttributes.add(cell.getStringCellValue());
		}			
		
		// Parse the second row and get the list of object attribute types
		ArrayList<String> objectAttributesTypes = new ArrayList<>();			
		row = (HSSFRow) rowIterator.next();
		
		cellIterator = row.cellIterator();		
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			objectAttributesTypes.add(cell.getStringCellValue());
		}		
		
		// The result will be returned through this list
		List<Object[]> result = new ArrayList<>();
		
		// Parse all the other remainig rows and build the list,
		// where every entry is represented by a HashMap with key 
		// equal to the header of the cell and the value, which 
		// belongs to appropriate row at appropriate position					 		
		while (rowIterator.hasNext())
		{
			// Retrieve next row
			row = (HSSFRow) rowIterator.next();
					
			// Create hash map instance
			HashMap<String, Object> entry = new HashMap<>();
			
			// Go through the cells and put every cell value into a hash map
			for (int i = 0; i < objectAttributes.size(); i ++) {
				
				if (row.getCell(i) != null) {
					switch (objectAttributesTypes.get(i).toString().toUpperCase()) {				
						case "BOOLEAN":
							entry.put(objectAttributes.get(i), row.getCell(i).getBooleanCellValue());							
							break;
						case "INTEGER":
							entry.put(objectAttributes.get(i), (new Double(row.getCell(i).getNumericCellValue()).intValue()));
							break;
						case "STRING":
							entry.put(objectAttributes.get(i), getCellValueAsString(row.getCell(i)));
							break;							
						default:
							entry.put(objectAttributes.get(i), row.getCell(i).getStringCellValue());
							break;
					}
				} else {
					entry.put(objectAttributes.get(i), null);
				}
			
			}					
			
			// Add hash map into the result list
			result.add(new Object[] {entry});			
		}
		
		return result.iterator();
	}
		
	private static String getCellValueAsString(HSSFCell cell) {
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				return String.valueOf(((Double) cell.getNumericCellValue()).intValue());
			default:
				return cell.getStringCellValue();			
		}
	}
}
