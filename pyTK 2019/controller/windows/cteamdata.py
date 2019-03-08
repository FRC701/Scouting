#------------------------------------------------------------------------------
# cteamdata module
#   -- contains the functions and classes for controlling the teamdata window
#------------------------------------------------------------------------------
from tkinter import *
from model.team import *

#------------------------------------------------------------------------------
# TeamDataController Class
#   -- contains information for setting and getting data and display values
#------------------------------------------------------------------------------
class TeamDataController():
    """Class that handles commands from the teamdata window."""

    # use these to index values to display, use the system: ("key", "term")
    # where key corresponds to a value in team and term labels that value
    dataLabelVals = [("numMatch", "Number of Matches: "),
                     ("offensiveWS", "Offensive Weighted Score: "),
                     ("totalWS", "Total Weighted Score: "),
                     ("negativeWS", "Negative Weighted Score: "),
                     
                     ("pNoShow", "No Show Percentage: "),
                     ("pStartLevel1", "Start on Level 1 Percentage: "),
                     ("pStartLevel2", "Start on Level 2 Percentage: "),
                     ("pPreloadCargo", "Preload Cargo Percentage: "),
                     ("pPreloadHatch", "Preload Hatch Percentage: "),
                     ("pCrossHubLine", "Cross Hubline Percentage: "),
                     ("avgRocketTopC", "Average Number Of Cargo in Top Rocket: "),
                     ("avgRocketTopH", "Average Number Of Hatch in Top Rocket: "),
                     ("avgRocketMiddleC", "Average Number Of Cargo in Middle Rocket: "),
                     ("avgRocketMiddleH", "Average Number Of Hatch in Middle Rocket: "),
                     ("avgRocketBottomC", "Average Number Of Cargo in Bottom Rocket: "),
                     ("avgRocketBottomH", "Average Number Of Hatch in Bottom Rocket: "),
                     ("avgCargoShipC", "Average Number Of Cargo in Cargo Ship: "),
                     ("avgCargoShipH", "Average Number Of Hatch in Cargo Ship: "),
                     
                     ("pEndLevel1", "End Level 1 Percentage: "),
                     ("pEndLevel2", "End Level 2 Percentage: "),
                     ("pEndLevel3", "End Level 3 Percentage: "),
                     ("pEndNone", "No End Level Percentage: "),

                     ("pFoul", "Matches Had Foul: "),
                     ("pTechFoul", "Matches Had Tech Foul: "),
                     ("pYellowCard", "Matches Had Yellow Card: "),
                     ("pRedCard", "Matches Had Red Card: "),
                     ("pDisabled", "Matches Where The Robot Was Disabled: ")]

    maxminLabelVals = [("maxRocketTopC","Maximum Number Of Cargo in Top Rocket: "),("minRocketTopC","Minimum Number Of Cargo in Top Rocket: "),
                       ("maxRocketTopH","Maximum Number Of Hatch in Top Rocket: "),("minRocketTopH","Minimum Number Of Hatch in Top Rocket: "),
                       ("maxRocketMiddleC","Maximum Number Of Cargo in Middle Rocket: "),("minRocketMiddleC","Minimum Number Of Cargo in Middle Rocket: "),
                       ("maxRocketMiddleH","Maximum Number Of Hatch in Middle Rocket: "),("minRocketMiddleH","Minimum Number Of Hatch in Middle Rocket: "),
                       ("maxRocketBottomC","Maximum Number Of Cargo in Bottom Rocket: "),("minRocketBottomC","Minimum Number Of Cargo in Bottom Rocket: "),
                       ("maxRocketBottomH","Maximum Number Of Hatch in Bottom Rocket: "),("minRocketBottomH","Minimum Number Of Hatch in Bottom Rocket: "),
                       ("maxCargoShipC","Maximum Number Of Cargo in Cargo Ship: "),("minCargoShipC","Minimum Number Of Cargo in Cargo Ship: "),
                       ("maxCargoShipH","Maximum Number Of Hatch in Cargo Ship: "),("minCargoShipH","Minimum Number Of Hatch in Cargo Ship: ")]
    
    pitDataStrings = []
                       

    graphVals = [("avgRocketTopC", "Scores","rocketTopC"),
                 ("avgRocketTopH", "Scores","rocketTopH"),
                 ("avgRocketMiddleC", "Scores","rocketMiddleC"),
                 ("avgRocketMiddleH", "Scores","rocketMiddleH"),
                 ("avgRocketBottomC", "Scores","rocketBottomC"),
                 ("avgRocketBottomH", "Scores","rocketBottomH"),
                 ("avgCargoShipC", "Scores","cargoShipC"),
                 ("avgCargoShipH", "Scores","cargoShipH")]
    
    def __init__(self):
        self.teamNum = 0
        self.entry = None
        self.data = None
        self.image = None

    # gets the team # from self.entry and finds the corresponding team
    # returns true if the team was found and false if not
    def loadData(self):
        try:
            self.teamNum = int(self.entry.get())
        except:
            print("Team value not valid.")
            self.teamNum = 0
            
        for team in Team.team_list:
            if team.number == self.teamNum:
                self.data = team
                print ("Loading team...")
                return True
            
        print ("Team not found.")
        return False

    # gets the image file corresponding to self.teamNum and returns it
    # if team is not found: returns nopic.gif
    def get_PhotoImage(self):
        image_name = "Images/" + str(self.teamNum) + ".gif"
        try:
            open(image_name)
        except:
            self.image = PhotoImage(file="Images/nopic.gif")
            return self.image
        
        self.image = PhotoImage(file=image_name)
        return self.image

    def get_GraphData(self, graphType=None):
        index = None
        data = None
        try:
            graphName = self.dataLabelVals[int(graphType[0])][0]  
        except:
            graphName = None

        #find the index and attr name
        for x, y, z in self.graphVals:
            if x == graphName:
                index = y
                data = z
                break # do not continue to iterate through the list
        try:
            currentIndex = self.data.getAttr(index)
            return currentIndex.getAttr(data)
        except:
            print ("Cannot find data for that graph.")
            return None

    def get_GraphName(self, graphType=None):
        try:
            graphName = self.dataLabelVals[int(graphType[0])][0]  
        except:
            graphName = None

        return graphName

            






