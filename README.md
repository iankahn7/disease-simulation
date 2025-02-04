Project 4 - Disease Simulation
README
Developers: Wayne Rudnick and Ian Kahn
Here is our read me file. This program takes in various types of configuration
files which all have to follow the template / structure of the configuration files
as described in the project description. The program then iterates through the
configuration text file and finds the various value(s) associated with each keyword
and then changes the parameters respectively. Our program also has a lot of bonus +
extra credit features such as person sickness degradation movement and different keys to activate
to change positioning and window color.
Bonus Key Button Specifications:
r - randomises positions
c - changes background to a random color
k - kills a random dot if they are not already dead
s -( speed )increases person // dot movement distance
w - slows down the movement distance of all the persons
z - cures // resurrects all persons, and removes immunity factors
i - infects a random person
b - makes all persons bigger
n - makes all persons smaller
m - makes default computer beep sound
h - Reduce the height of the possible area for people to roam
y - Reduce the width of the possible area for people to roam
' ' - spacebar puts all of them in a line starting position
u - updates the scene title to display how many people are alive, continue to press u to update number
o - (WARNING: PROBABLY DON'T TRY THIS ONE)makes sick face image pop up along with other windows to represent ads
e - exit the program
t - Increase the height of the possible area for people to roam
g - Increase the width of the possible area for people to roam
Other Notable Bonus Features:
Each person has random Movement abilities that constantly updates
Default computer alert sound plays when a person dies
Color states key / guide:
green - healthy (no afflictions)
yellow - immune
red - sickness level 1
any color red through purple represents a spectrum of sickness from 1 - 10.
The more purple they are, the more sick they are. The more sick they are, the slower they are, and the more
likely they are to spread disease.
black - dead
Project Credits:
Infrastructure / Design of Program - Wayne Rudnick
Input File Argument Handler Logic - Ian Kahn
GUI Design and Structure - Wayne Rudnick
README  - Ian Kahn
Multi Threading - Wayne Rudnick
Program Public Method Documentation / Commenting - Ian Kahn
General Program Commenting - Wayne Rudnick / Ian Kahn