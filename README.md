# Framework. Selenium WebDriver. Tools QA

## Task description
You'll need to implement basic framework, which should include next parts:

1. BaseForm class  
    
2. BaseElement class and child classes for elements
3. Singleton & BrowserFactory (Factory method) to organize process of getting driver instance
4. Utility class to work with driver
5. ConfigManager class

All of the assertions should be implemented by using special testing libraries (Java – TestNG, C# - NUnit, Python – PyTest, JS - mocha+chai)

Selenium WebDriver should be used for working with browser, pages' elements, etc.

Tests should be working on both Chrome and Firefox browsers.

### Test cases
**URL**: [https://demoqa.com/](https://demoqa.com/)

**Test case 1. Alerts**

| #   | Step                                                                                 | Expected result                                                             |
| --- | ------------------------------------------------------------------------------------ | --------------------------------------------------------------------------- |
| 1   | Navigate to main page                                                                | Main page is open                                                           |
| 2   | Click on **Alerts, Frame & Windows** button.  <br>In a menu click **Alerts** button. | Alerts form has appeared on page                                            |
| 3   | Click on **Click Button to see alert** button                                        | Alert with text "You clicked a button" is open                              |
| 4   | Click **OK** button                                                                  | Alert has closed                                                            |
| 5   | Click on **On button click, confirm box will appear** button                         | Alert with text "Do you confirm action?" is open                            |
| 6   | Click on **OK** button                                                               | Alert has closed  <br>Text "You selected Ok" has appeared on page           |
| 7   | Click on **On button click, prompt box will appear** button                          | Alert with text "Please enter your name" is open                            |
| 8   | Enter **randomly generated** text, click **OK** button                               | Alert has closed  <br>Appeared text equals to the one you've entered before |

**Test case 2. Iframe**

|#|Step|Expected result|
|---|---|---|
|1|Navigate to main page|Main page is open|
|2|Click on **Alerts, Frame & Windows** button  <br>In a menu click **Nested Frames** button|Page with Nested Frames form is open.<br><br>There are messages "Parent frame" & "Child Iframe" present on page|
|3|Select **Frames** option in a left menu|Page with Frames form is open.<br><br>Message from upper frame is equal to the message from lower frame|

**Test case 3. Tables**

|User №|First Name|Last Name|Email|Age|Salary|Department|
|---|---|---|---|---|---|---|
|1|Jon|Snow|knownothing@gmail.com|30|3000|alpha|
|2|Buttercup|Cumbersnatch|BudapestCandygram@mail.io|41|2000|beta|

|#|Step|Expected result|
|---|---|---|
|1|Navigate to main page|Main page is open|
|2|Click on **Elements** button  <br>In the menu click a **Web Tables** button|Page with **Web Tables** form is open|
|3|Click on **Add** button|**Registration Form** has appeared on page|
|4|Enter data for **User №** from the table and then click **Submit** button|Registration form has closed.<br><br>Data of **User №** has appeared in a table|
|5|Click **Delete** button in a row which contains data of **User №**|Number of records in table has changed<br><br>Data of **User №** has been deleted from table|

**Test case 4. Handles**

|#|Step|Expected result|
|---|---|---|
|1|Navigate to main page|Main page is open|
|2|Click on **Alerts, Frame & Windows** button  <br>In the menu click a **Browser Windows** button|Page with Browser Windows form is open|
|3|Click on **New Tab** button|New tab with sample page is open|
|4|Close current tab|Page with Browser Windows form is open|
|5|In the menu on the left click **Elements → Links** button|Page with Links form is open|
|6|Click on **Home** link|New tab with main page is open|
|7|Resume to previous tab|Page with Links form is open|

**Test case 5. Slider, Progress bar**

|#|Step|Expected result|
|---|---|---|
|1|Navigate to main page|Main page is open|
|2|Click on **Widgets** button. In the menu on the left click **Slider** button|Page with Slider form is open|
|3|Set slider to a valid randomly generated value|Value on the page near the slider is equals to the one set before|
|4|In the left menu click **Progress Bar** button|Page with Progress Bar form is open|
|5|Click on **Start** button||
|6|Click on **Stop** button, when value displayed on progress bar becomes equals to your age|Value on progress bar is equal to your age (error is not higher than 2 %)|

**Test case 6. Date picker**

|#|Step|Expected result|
|---|---|---|
|1|Navigate to main page|Main page is open|
|2|Click on **Widgets** button  <br>In the left menu click **Date Picker** button|Page with Date Picker form is open.  <br>Values of Select Date and Date And Time fields are equal to current date and time|
|3|Using date selector, pick **nearest 29th of February** in a **Date Picker**|Value is equal to the one set previously|

**Test case 7. Files. Uploading and downloading**

|#|Step|Expected result|
|---|---|---|
|1|Navigate to main page|Main page is open|
|2|Click on **Elements** button  <br>In the left menu click **Upload and Download** button|Page with Upload and Download form is open|
|3|Click **Download** button, wait until file is downloaded|File was downloaded successfully|
|4|Upload file which was downloaded on step #3|File's name is displayed in upload form|
