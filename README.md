# Clinic Reminder System
I am learning full-stack development to understand how back-end works together with the front-end. In this project, I used Angular 2, Ionic 3, Spring MVC and MySQL to build the systems.

## Problem
I am going to implement a clinic reminder system which helps doctor and patient manage their clinical reminders. A clinical reminder is an instruction doctor post to patient. The instruction could be "eat medicine A in 3 hours" or "take exercise for 1 hour today". There will be a dashboard (**Doctor Dashboard**) used by doctor to post these instructions to patients and the patient will get notifications on their mobile app (**Patient App**). Once the patient finish the reminder, they can mark them as 'done' in their app and doctor could see these update in realtime on their dashboard. For details, see it in the next section
## Sub Systems 
* Patient App
* Doctor Dashboard
* RESTful Backend

## Patient App
* I used ionic 3 to develop this mobile app. Users can get notification from doctor dashboard by Websocket.
* I also built a native app has the same functions by using Android SDK.
* There is a page let user login by a username and password (no register function now, we can create username and password from database)
* There is a page (**Reminder List Page**) which list all reminders you receive order by the time it was created. 
   * The list will update in real-time (e.g. when doctor post a reminder on Doctor Dashboard, the list on patient should be updated with the newest reminder)
   * When patient's mobile phone is inactive (e.g. when the screen is lock), try to push a notification to patient's mobile phone. When they click the notification, your app will be opened and go to the **Reminder List Page** 
   * you can mark the reminder as "done" from this list and **Doctor Dashboard** will also update after you do this

## Doctor Dashboard
* I used angular 7 to finish this part.
* A page to let doctor login
* A page which can let doctor post reminder to patient. A reminder will have:
  * a description text for what doctor wants the patient to do 
  * a duration time which indicates in how many hours should the patient finish this instruction from now.
  * a priority level which has three value: "HIGH", "MIDDLE","LOW"
* A page (**Reminder List Page**) to show a table which lists status of reminders for all the patients for a doctor.
  * the table has 4 columns: "Patient Name", "High Priority Reminder (Unfinished Count)","Middle Priority Reminder (Unfinished Count)", "Low Priority Reminder (Unfinished Count)", for the last 3 columns, they will show the unfinished count for each reminder priority level.
  * the table is ordered by the unfinished count of reminder for different priority level in descend order. The record firstly is sort by "High Priority Reminder (Unfinished Count)" in descend order, then by "Middle Priority Reminder (Unfinished Count)" in descend order, then by "Low Priority Reminder (Unfinished Count)" in descend order
* A page (**History Reminder Page**) to show a barchart for a given patient, for the last 7 days, in each day, how many reminders he didn't finish on-time. For a given reminder, we use its creation time to decide which day it belongs to. (e.g. if a reminder create at 11pm at 05/05/2018 and should be done in 24h, it should belong to 05/05/2018). 
  * you can click the row in **Reminder List Page** to navigate to this page

## RESTful Backend
For the backend, it's just implement all the functions my frontend and mobile app needs. 
* I used Spring MVC to implement the RESTful API
* I used MySQL to store the data
* I built real-time service to connect the client-side by creating Socket.IO server.
