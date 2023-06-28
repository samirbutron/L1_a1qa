# Selenium WebDiver. Steam

## Task description
Implement auto tests, based on provided testcases
Assertions should be made using special testing libraries (Java – TestNG, C# - NUnit, Python – PyTest, JS - mocha+chai)  
To work with browser/elements of pages you should use Selenium WebDriver library
Tests should be working with Chrome browser


### Test cases
**Test case 1**

| #   | Step                           | Expected result                                                                                                                                                                                           |
| --- | ------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1   | Navigate to main page          | Main page is open                                                                                                                                                                                         |
| 2   | Scroll and open Privacy Policy | Privacy Policy page is opened in new tab<br><br>Switch language elements list displayed.<br><br>Supported languages: English, Spanish, French, German, Italian, Russian, Japanese, Portuguese, Brazilian. |
|     |                                | Policy revision signed in the current year                                                                                                                                                                |


**Test case 2**

| #   | Step                                                                                                                                                         | Expected result                                                                                                           |
| --- | ------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------- |
| 1   | Navigate to main page                                                                                                                                        | Main page is open                                                                                                         |
| 2   | Move pointer to '**New & Noteworthy**' at page's menu. Using explicit waits wait until popup menu shows up.  <br>Click '**Top Sellers**' option in that menu | Page with Top Sellers products is open                                                                                    |
| 3   | In menu on the right choose '**Action**', '**LAN Co-op**' and '**SteamOS + Linux**' checkboxes                                                               | All three checkboxes are checked;<br><br>Number of results matching your search equals to number of games in games list   |
| 6   | From list get first game's name, release date and price                                                                                                      |                                                                                                                           |
| 7   | Click on first game in the list                                                                                                                              | Page with game's description is open<br><br>Game's data (name, release date and price) are equal to the ones from step #6 |

**Test case 3**

| #   | Step                                                                                                                                                                                                                                               | Expected result                                                                                                                                                           |
| --- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1   | Navigate to main page                                                                                                                                                                                                                              | Main page is open                                                                                                                                                         |
| 2   | Open Community Market page (move pointer to **COMMUNITY** and then in dropdown click **MARKET**)                                                                                                                                                   | Community Market page is open                                                                                                                                             |
| 3   | Click '**Show advanced options**' in the right menu                                                                                                                                                                                                | '**SEARCH COMMUNITY MARKET**' window is open                                                                                                                              |
| 4   | Enter next parameters in the search form: <br><br>1. **Games** field - choose '**Dota 2**'<br>2. **Hero** dropdown - choose '**Lifestealer**'<br>3. **Rarity** column - choose checkbox '**Immortal**'<br>4. In **Search** field type '**golden**' |                                                                                                                                                                           |
| 5   | Click on '**Search**' button                                                                                                                                                                                                                       | Filters '**Dota 2**', '**Lifestealer**', '**Immortal**', '**golden**' have appeared on the page.<br><br>**Top 5** results contain word '**Golden**' in their names.       |
| 6   | Delete filters '**golden**' and '**Dota 2**' by clicking on cross sign                                                                                                                                                                             | Both filters are removed                                                                                                                                                  |
| 7   | Click on first item in the list                                                                                                                                                                                                                    | Item's page is open<br><br>Item's data (game, hero, rarity, search keyword) are equal to the ones set in step #4<br><br>Item's name equals to its name from previous page |
