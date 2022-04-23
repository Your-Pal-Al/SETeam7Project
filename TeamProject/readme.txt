###############################################################################################################################################
#  _____ ______   ________  ________   ________  ________  ___       ________          ________  ___       ________  ________   _______       #
# |\   _ \  _   \|\   __  \|\   ___  \|\   ____\|\   __  \|\  \     |\   __  \        |\   ____\|\  \     |\   __  \|\   ___  \|\  ___ \      #
# \ \  \\\__\ \  \ \  \|\  \ \  \\ \  \ \  \___|\ \  \|\  \ \  \    \ \  \|\  \       \ \  \___|\ \  \    \ \  \|\  \ \  \\ \  \ \   __/|     #
#  \ \  \\|__| \  \ \   __  \ \  \\ \  \ \  \    \ \   __  \ \  \    \ \   __  \       \ \  \    \ \  \    \ \  \\\  \ \  \\ \  \ \  \_|/__   #
#   \ \  \    \ \  \ \  \ \  \ \  \\ \  \ \  \____\ \  \ \  \ \  \____\ \  \ \  \       \ \  \____\ \  \____\ \  \\\  \ \  \\ \  \ \  \_|\ \  #
#    \ \__\    \ \__\ \__\ \__\ \__\\ \__\ \_______\ \__\ \__\ \_______\ \__\ \__\       \ \_______\ \_______\ \_______\ \__\\ \__\ \_______\ #
#     \|__|     \|__|\|__|\|__|\|__| \|__|\|_______|\|__|\|__|\|_______|\|__|\|__|        \|_______|\|_______|\|_______|\|__| \|__|\|_______| #
###############################################################################################################################################                                                                                                                                      

Authors: Alex Salas, Jacob Jeffery, Santos Valdez, Luke Vander Lugt
GitHub: https://github.com/Your-Pal-Al/SETeam7Project

Thanks for downloading our Mancala Clone! This game was designed to fulfill the requirements of our software engineering semester project
as outlined by Dr. Mark Smith at the University of Central Arkansas. This readme file will detail how to install this program as well as 
specific instructions on how to run it. 

#############################
# INSTALLATION INSTRUCTIONS #
#############################
1. If not already, download project from; https://github.com/Your-Pal-Al/SETeam7Project

2. Extract the files from the archive

3. Move folder to desired install directory



###################################
# EXECUTION INSTRUCTIONS - SERVER #
###################################

1. Navigate to the TeamProject install directory

2. Before running the Server, a database be active and populated with data
   -For ease-of-use, the runXAMPP.bat file may be used to launch Xampp with the default option
   -Do note that the bat file will not work properly if installed in a non-standard location (C:\xampp)
   -If Xampp is installed in another location, set the location properly in runXAMPP.bat
   -Otherwise, Xampp must be started manually

3. Assuming Xampp is running, and the MySQL server is running, the user database must be initialized
   
   -If used the runXAMPP.bat, proceed to step 4.
   
   -If Xampp is started manually, you will need to create a command prompt and navigate to "C:\xampp\mysql\bin" (or relative install path)
   -Then enter the following commands in the terminal;
			
			start mysqld.exe
			mysql -hlocalhost -ustudent -phello student_space < "C:\..PATH_TO_INSTALL\TeamProjectdatabaseTEST.sql"
			
4. Now that the database is active and initialized, the Mancala Server can be create.

	- To do this, navigate back to the program install directory and execute "runProject_server.bat"

5. The Mancala Server GUI should appear with the status "Not Connected". To allow clients to join, select the "Listen" button

6. The Server is now active and ready to accept clients. 



###################################
# EXECUTION INSTRUCTIONS - CLIENT #
###################################

1. Navigate to the TeamProject install directory

2. Execute "runProject_client.bat"

3. The Mancala Client GUI should appear 

4. Choose either "Login" to log in to an existing account or "Create" to create a new account
	
5. Continue following the GUI instructions to play Mancala
	-Note that 2 Clients must be active in order for a game to start



##############################################################################
#  _________  ___  ___  ________  ________   ___  __    ________  ___        #
# |\___   ___\\  \|\  \|\   __  \|\   ___  \|\  \|\  \ |\   ____\|\  \       #
# \|___ \  \_\ \  \\\  \ \  \|\  \ \  \\ \  \ \  \/  /|\ \  \___|\ \  \      #
#      \ \  \ \ \   __  \ \   __  \ \  \\ \  \ \   ___  \ \_____  \ \  \     #
#       \ \  \ \ \  \ \  \ \  \ \  \ \  \\ \  \ \  \\ \  \|____|\  \ \__\    #
#        \ \__\ \ \__\ \__\ \__\ \__\ \__\\ \__\ \__\\ \__\____\_\  \|__|    #
#        \|__|  \|__|\|__|\|__|\|__|\|__| \|__|\|__| \|__|\_________\  ___   #
#                                                        \|_________| |\__\  #
#                                                                     \|__|  #
##############################################################################






























Header  and thanks generated using;
https://textkool.com/en/ascii-art-generator?hl=default&vl=controlled%20smushing&font=3D-ASCII&text=Mancala%20Clone