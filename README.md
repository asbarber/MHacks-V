# MHacks-V
##MYOwnTransaction: 
An application for the MHacks V competition at University of Michigan, Winter 2015. The purpose of the application is to connect with to a human with a physical handshake, and then be able to share data.

### Use cases
* Meeting a new person, you would be able to share social information (phone number, connect on Facebook or Twitter) and make the "first impression" have more uninterrupted human action.
* While shopping, you would be able to make your purchase through a simple handshake (using Venmo).
* At a career fair, you would be able to share professional information (name, university, major, year, resume, LinkedIn profile, GitHub account) and make it fully available to a recruiter with a simple handshake.

### Data sharing supported
* Capital One 
* Concur
* LinkedIn
* Venmo

### Hardware used
* MYO bands were used for the motion detection associated with the handshake.
* Android phones were used to authenticate and connect with the other hardware.
* Google glasses were used as the user-interface with voice commands to exchange data.

### Implementation details
MYO bands detect the handshake and send a bluetooth message to notify the Android phone connected to the MYO band.  The two Android phones then connect over bluetooth to share basic connection/identification information. Prior to the connection, the user has authenticated for the relevant data. At this point, the Android phones update a Firebase database, notifying the Google Glasses that a connection has been made. The Google Glasses respond with a list of voice commands for data sharing.

### Awards Won
* Best use of Concur or TripIt APIs
* Most Technically Challenging, sponsered by a16z

### Additional Information
* http://challengepost.com/software/myowntransaction

### Developers
* Sean Acker, sjacker@umich.edu
* Aaron Barber, asbarber@umich.edu
* Katelyn Dunaski,dunaskik@msu.edu
* Michael Ray, mjray@umich.edu
