# Custom Inventory Management Application

## Part C
**Prompt**: Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.
- **File Name**: `mainscreen.html'
- **Change**: title, and list of products and parts

## Part D
**Prompt**: Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.
- **File Name**: `About.html' and mainscreen.html
- **Change**: created page about Fatality Hair products that is linked to mainscreen 

## Part E
**Prompt**: Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.
- **File Name**: `ProductServiceImpl.java'
- **Change**: added sample inventory 

## Part F
**Prompt**: Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
•  Display a message that indicates the success or failure of a purchase.
- **File Name**: mainscreen.html & MainScreenControllerr.java
- **Change**: Added Buy Now button functionality to product list.

## Part G
**Prompt**: Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
•  Modify the sample inventory to include the maximum and minimum fields.
•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
•  Rename the file the persistent storage is saved to.
•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.
- **File Name**: Part.java, ProductServiceImpl.java, InhousePartForm.html, OutsourcedPartForm.html, application.properties, 
- **Change**: -Implemented `min` and `max` fields in the `Part` entity to track minimum and maximum inventory levels. Updated `InhousePartForm.html` and `OutsourcedPartForm.html` to include input fields for `min` and `max` inventory values.
- Modified sample inventory in `ProductServiceImpl` to set `min` and `max` values.
- Renamed the persistent storage file to `fatality-hair-products`.
- Added validation in `ProductServiceImpl` to enforce that inventory levels are between the `min` and `max` values before saving.

## Part H
**Prompt**: H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
•  Display error messages when adding and updating parts if the inventory is greater than the maximum.
- **File Name**: PartService.java, PartServiceImpl.java, InhousePartForm.html, OutsourcedPartForm.html, ProductServiceImpl.java
- **Change**: Added inventory validation for parts and products:
- Implemented validation to ensure part inventory is between min and max values.
- Updated PartService and ProductService to include inventory validation before saving.
- Added error messages to forms to handle invalid inventory inputs.
- Ensured products cannot be saved if associated parts have inventory levels below the minimum or above the maximum.

## Part I
**Prompt**:Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.
- **File Name**: PartTest.java, AddProductController.java, InhousePartTest.java, OutsourcedPartTest.java, ProductTest.java, 
- **Change**: Successfully added inventory validation for min/max values and refactored controller logic. All unit tests passed without errors.

## Part J - the scariest step of all! 
**Prompt**:Remove the class files for any unused validators in order to clean your code.
- **File Name**: ValidDeletePart, ValidEnufParts, DeletePartValidator, EnufPartsValidator, and ValidDeletePart.java, PriceProductValidator.java
- **Change**: 