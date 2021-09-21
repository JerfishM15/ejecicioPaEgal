import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import utileria.accion as accion

TestData loginData = findTestData('pruebaInfo1');
int rows = loginData.getRowNumbers();
String products;
String cantidad;

WebUI.openBrowser('https://rahulshettyacademy.com/seleniumPractise/#/');

//if cart is present
if (accion.present("//div[@class='cart']")){
	
	int counter=1;
	int itemCount = 1;
	boolean present = true;
	boolean found = false;
	
	while(present) {
		products = loginData.getObjectValue('products', itemCount).toString();
		cantidad = loginData.getObjectValue('cantidad', itemCount).toString();
		//if products are present
		if (accion.present("//div[@class='product'][" + counter +"]",1 )){
			
			//if the product is the one im looking for
			if(accion.text('//div[@class="product"]['+ counter +']//h4[@class="product-name"]').contains(products)) {
				
				//increment requiered
				for(int i = 1; i <cantidad.toInteger(); i++) {
					accion.click('//div[@class="product"]['+ counter +']//a[@class="increment"]');
				}
			
				//add to cart
				accion.click('//div[@class="product"]['+ counter +']//button');
				counter=1;
				itemCount++;
				found=true;
				
			}else {
				counter++;
			}
		} else {
			present = false;
		}
		
		if (itemCount> rows) {
			present=false;
		}
		
	}
	//click on cart
	accion.click('//div[@class="cart"]//img[@alt="Cart"]');
	
	//procceed to chekout
	accion.click("//div[@class='cart']//button");
} 
