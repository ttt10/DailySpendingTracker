# DailySpendingTracker
##Description:
DailyExpense is a simple Android app keeps track of manually entered expenses. The idea is to use the app for cash transactions which are hard to keep up with if you do not log them somewhere. Data is stored in a SQLiteDatabase and abstracted from the UI via a repository. The UI currently is a single activity that manages 3 fragments which allow the user to view expenses and modify them.

Below are some of the things I learned about while creating this app:
###Design patterns
- Repository pattern
- Single Responsibility pattern
- Singleton pattern
	
###UI patterns
- Object Pool pattern (RecyclerView)
- Android Fragments and LifeCycle management

###Data Storage
- SQLiteDatabases

###Testing
- JUnit
- Android Testing Support Library

This app also uses a licensed version of GrapeCity's Xuni Calendar control. GrapeCity offers a variety of UI controls for Mobile developers in Android, iOS, Windows & Xamarin. Find out more about the product at [GoXuni.com](http://www.goxuni.com).

