2ndChallenge
============

This Android application responds to the following requirements:

• Retrieve historical earthquake data from the U.S. Geological Survey (USGS) and produce a table displaying information about earthquakes that have occurred in the past 30 days.  The recommended approach section has a URL for the data.
•	The view of earthquake events must be sorted in descending date order.
•	Each item must display the following information in a pleasing and easy to read format:
o	The USGS title for the quake
o	The latitude and longitude of the quake
o	The time the quake occurred in local time
•	The user must be able to force a reload of the view.
•	When the user reloads the view new quake data should be added without the deletion of older quake data (e.g. if a quake is recorded while using the app and the user reloads they should see the new quake plus all of the old quake data).
•	The user must be able to selectively delete table items using a gesture familiar to uses of that platform.
•	If the user selects a row the app should open an internal web browser control with a link to the full quake information at the USGS site.
•	The items with an earthquake of magnitude 5 or higher must be shaded in light red.
•	The items with a quake of magnitude 7 or higher must be shaded in red.
•	The application must cleanly and correctly handle network failures or the absence of a network.
•	The application must display some indicator that the application is busy during data loads.
•	The application UI must remain responsive to user input during the data load process.
•	The application must not crash or leak memory.
•	The structure of the application must be well structured and understandable.
•	If you’re completing this task using the mobile web technology, there may be no server side logic other than delivery of HTML/JS/CSS content to the phone.  All other logic, storage, etc. must occur on the phone itself.
