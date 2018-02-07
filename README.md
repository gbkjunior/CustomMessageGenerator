# CustomMessageGenerator

Read Me file :

Instructions to run the program:

1. Download the zip file into your system and extract the folder. 
2. Open Eclipse IDE, and import this folder by clicking Add projects from
File System option. Import it as a Java Project.
3. Navigate to src > com.kipsu.msggen.tests > TestOutput.Java
4. Run this file as a Java Application

Overview of Design Decisions:

1. The first thing I have to mention regarding the design decision for the
given problem was to decide on the kind of application I am going to develop
for satisfying the conditions in the problem statement. I decided to build a
web application with a nice UI and handling the requests appropriately using 
Node.js and React.js.

Unfortunately, I felt it was taking more time that I anticipated and I reverted 
to a Java console application since the core of this challenge is to solve the 
problem using good object oriented principles. 

2. Since it was decided to be a console application, I decided to have classes for
Guests, Reservation details, Company and MessageTemplate. 

3. As data has to be read from JSON files, I used the Simple JSON jar for handling
those requests. I used an ArrayList of JSONObjects and decided to pass them to
the constuctors of the respective classes to get the data along with the index.
(The index refers to the ID that is being searched/queried for.)

4. Now, I had to formulate a way to store the templates using placeholders or variables.
I created a JSON file which will hold a templateID and the templateName which
will be a string with the appropriate placeholders for firstName, room Number, company etc.

The placeholders that I have used are as follows:

%GREETING% : This place holder will replace the appropriate greeting of the day based on the time.
%NAME% : This placeholder will replace the firstName of the appropriate user.
%COMPANY_NAME% : This placeholder will replace the hotel/company name of the appropriate company/hotel.
%ROOM_NO% : This placeholder will replace the room number of the selected user.

These are the main placeholders that I thought of and this can be extended as per convenience.

5. Next, I had to handle the epoch timestamps values that were provided in the Guests
JSON file. I used the SimpleDateFormat Library to find the time in the given
timeZone. The timeZone value is obtained from the companies.json file and matched with the 
timestamp of the user.

6. One thing to note is that, for generating the greeting from the time, I have taken only
the start timestamp value into consideration. As I am not aware of the templates 
that can be generated using the endtimestamp or current timestamp value when the user is being sent a message , I have skipped that part this time.
However, the same method can be extended for the endtimestamp or current system timestamp value to find the time of the day.

7. Since it's a console application, I decided to have a workflow as follows:

-> Display the list of users to select from.
-> Display the list of company names to select from.
-> Give an option for the user to use existing template or create a custom template following the template metrics.
-> Choosing 1 will let them use existing template.
-> Choosing 2 will let them create custom template using the metrics.

-> Take the corresponding user id and company id as input to map to the user details and company details.
   (The greeting name will be computed internally and replaced in the template message accordingly.)
-> Based on all these values, the message with all the given details will be generated and displayed on the console.

 Language used and why:

 I have implemented this as a simple Java console application. This was far more
 time saving and by using classes and objects it made all the more easy and efficient. 
 On top of that, I am most comfortable with Java as it is object oriented and has its own garbage collection.

Process for verifying the correctness of the program:

I did both unit test and integration test for the individual classes. Also, functional test
was done after the entire module was built. I verified the correctness of the program by checking
if all the values are being returned appropriately and most importantly, I checked whether the timestamp values are returning 
the correct date format for it to be mapped to the greeting name.

Also, when a custom template is used, it will add it to the template.json file and return the generated message on the go.

What didn't I get to and improvements:

To start off with improvements, I would definitely have liked to build this as a neat 
web application since it would remove the aspect of the user to enter the id as input instead
would let him select the user he wants to send a message. A visually pleasing application is always 
a great fit.

Moving onto the things I didn't get to, I guess I should have handled the error input cases in a better manner. I have developed this application based on user input but 
I haven't effectively handled the situations where people can enter bad input. I have handled basic cases, but if I had more time, I could have completed it elegantly.

Adding on to the error handling, I should have methods to check whether the custom template given by the user is in the correct format following the template metrics. 
I should have completed all the user input validations to make it look as a fully developed application.

Sample Input and Output :

List of users to select from: 
ID  firstName   lastName
1   Candy       Pace
2   Morgan       Porter
3   Bridgett       Richard
4   Melisa       Preston
5   Latoya       Herrera
6   Hewitt       Hopper
List of companies to select from:
ID  companyName 
1   Hotel California
2   The Grand Budapest Hotel
3   The Heartbreak Hotel
4   The Prancing Pony
5   The Fawlty Towers
Enter the userID:
4
Enter the companyID:
5
Do you want to use existing template or do you want to create a custom template? 
 Press 1 for existing template. 
 Press 2 for creating custom template.
1
List of templates to choose from:
1 %GREETING% %NAME%, and Welcome to %COMPANY_NAME% . Room %ROOM_NO% is ready for you. Enjoy your stay and let us know if you need anything.
2 %GREETING% %NAME%, and Welcome to %COMPANY_NAME% . Hope you are having a good day. There are quite a few amazing restaurants that you have to try out. Please let us know if you need more information regarding this.
3 %GREETING% %NAME%, We hope you enjoyed your stay in room %ROOM_NO% here at %COMPANY_NAME%. We are pleased to help you anytime and we would like to improve our standards based on your feedback. Could you spend a minute on it?.
4 %GREETING% %NAME%, how has the day been at room %ROOM_NO%.
5 %GREETING% %NAME%, How is your stay at room %ROOM_NO% ?
6 %GREETING% %NAME% have a fun day!
Enter the templateID:
4
Custom Generated Message: 
Good Evening Melisa, how has the day been at room 417.



