GoalGet familiar with Selenium WebDriver

Type

QA AutomationTask

Implement auto tests, based on provided testcases

Assertions should be made using special testing libraries (Java – TestNG, C# - NUnit, Python – PyTest, JS - mocha+chai)  

To work with browser/elements of pages you should use Selenium WebDriver library

Tests should be working with Chrome browser

Requirements

You'll need to create **at least two** auto tests. **Test case 1 is obligatory**. After you're done with test case 1 **you'll need to create at least one more auto test** based on remaining scenarios (if you have time - you could write two auto tests, one for each test case). 

We expect that for each created auto test you'll implement whole set of obligatory requirements. Only in case if you've met all the basic requirements you can start working on additional ones.

**\***Basic (obligatory):

1. You should use WebDriverManager in your solution.
2. Page Object pattern should be used is your solution (i.e. all of the actions with elements from page should be described in that page's class).
3. Locators should meet our requirements (were discussed at workshop before, 4 rules).
4. Singleton pattern should be used in your solution.
5. Some parts of auto test should be separated into pre- and postconditions.
6. Test data and config data should be stored separately, to work with them you'll need to create a utility class. For storing this data you should use either Xml or Json.
7. Browser itself should start up in Incognito mode.

**\***Additional:

1. Implement Browser Factory (Factory Method) pattern.
2. Add models to your solution and build them into auto tests - Test case #2.
3. Implement composition principle to work with forms on pages into your solution - Test case #3.

_  
\*Requirements are arranged in descending order by their priority_

Test casesURL: [https://store.steampowered.com](https://store.steampowered.com/) 

**Test case 1 (obligatory)**

| 
#

 | 

Step

 | 

Expected result

 |
| --- | --- | --- |
| 1 | Navigate to main page | Main page is open |
| 2 | Scroll and open Privacy Policy | 

Privacy Policy page is opened in new tab

Switch language elements list displayed.

Supported languages: English, Spanish, French, German, Italian, Russian, Japanese, Portuguese, Brazilian.

  


 |
|   
 |   
 | Policy revision signed in the current year |

**Test case 2**

| 
#

 | 

Step

 | 

Expected result

 |
| --- | --- | --- |
| 1 | 

Navigate to main page

 | 

Main page is open

 |
| 2 | 

Move pointer to '**New & Noteworthy**' at page's menu. Using explicit waits wait until popup menu shows up.  
Click '**Top Sellers**' option in that menu

 | 

Page with Top Sellers products is open

 |
| 3 | 

In menu on the right choose '**Action**', '**LAN Co-op**' and '**SteamOS + Linux**' checkboxes

 | 

All three checkboxes are checked;

Number of results matching your search equals to number of games in games list

 |
| 6 | 

From list get first game's name, release date and price

 |   
 |
| 7 | 

Click on first game in the list

 | 

Page with game's description is open

Game's data (name, release date and price) are equal to the ones from step #6

 |

**Test case 3**

| 
#

 | 

Step

 | 

Expected result

 |
| --- | --- | --- |
| 1 | Navigate to main page | Main page is open |
| 2 | Open Community Market page (move pointer to **COMMUNITY** and then in dropdown click **MARKET**)  | Community Market page is open  |
| 3 | 

Click '**Show advanced options**' in the right menu

 | '**SEARCH COMMUNITY MARKET**' window is open |
| 4 | 

Enter next parameters in the search form: 

1. **Games** field - choose '**Dota 2**'
2. **Hero** dropdown - choose '**Lifestealer**'
3. **Rarity** column - choose checkbox '**Immortal**'
4. In **Search** field type '**golden**'

 | 

  


 |
| 5 | Click on '**Search**' button | 

Filters '**Dota 2**', '**Lifestealer**', '**Immortal**', '**golden**' have appeared on the page.

**Top 5** results contain word '**Golden**' in their names.

 |
| 6 |  Delete filters '**golden**' and '**Dota 2**' by clicking on cross sign | Both filters are removed |
| 7 | 

Click on first item in the list

 | 

Item's page is open

Item's data (game, hero, rarity, search keyword) are equal to the ones set in step #4

Item's name equals to its name from previous page

 |
