# dart-dine

For our final project for CS89, Human Computer Interaction, we tried to revamp the Dartmouth meal site, which is located at http://nutrition.dartmouth.edu:8088.  Our goal was to make a mobile app that would solve all of the UX/UI problems that the current site had, especially when accessing it on mobile, and also combine all dining information into a useful format.  

One of the major contributions I made to the project was fully building out this app.  Note that this app was constructed as a proof of concept, and is not fully functional.  For example, it currently doesn't pull the food data from the working site, despite having a hidden API that I could have leveraged.  Additionally, ideally I would've created a function that would guess at how full the different dining locations were based on measured data for a few weeks.  However, in the PoC, that data is randomly generated on application open.

The .apk is also compiled and can be downloaded, along with screenshots of the app.

### Screenshots

<img src="/screenshots/Home_Page.png?raw=true" width="200" alt="Home Page"/>

Clicking on the "FOCO" button takes you to the "Foco Main Page".

<img src="/screenshots/Foco_Main_Page.png?raw=true" width="200" alt="Foco Main Page"/>

Clicking "Search Menu" will show all of the menu items in a searchable, scrollable list, and displays nutritional information by tapping on any of the food items.

<img src="/screenshots/Menu_Item_Nutrition.png?raw=true" width="200" alt="Nutritional Information for Green Peas"/>

Going back to the "Foco Main Page", and clicking on "Make a New Meal" will take you to this page, where you can choose items to add to a meal, and it will keep track of all the health statistics of your constructed meal, including calories, total fat, and protein.  You can also save it for later use.

<img src="/screenshots/Make_Meal.png?raw=true" width="200" alt="Meal Construction Page"/>

The "Load a Meal" button will allow you to load a meal from previously saved options.

<img src="/screenshots/Load_Meal.png?raw=true" width="200" alt="Loading a Meal"/>

---

<ul>
<li>Java
<ul><li>Android Development</li></ul>
</li>
</ul>

**Created by Alex Beals © 2015**
