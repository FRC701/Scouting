#------------------------------------------------------------------------------
# cteamdata module
#   -- contains the functions and classes for controlling the teamdata window
#------------------------------------------------------------------------------
from Tkinter import *
from model.team import *

#------------------------------------------------------------------------------
# TeamDataController Class
#   -- contains information for setting and getting data and display values
#------------------------------------------------------------------------------
class TeamDataController():
    """Class that handles commands from the teamdata window."""

    # use these to index values to display, use the system: ("key", "term")
    # where key corresponds to a value in team and term labels that value
    dataLabelVals = [("numMatch","Number of Matches: "),
                     ("pNoShow","No Show: "),
                     ("offensiveWS","Offensive Weighted Score: "),
                     ("defensiveWS","Defensive Weighted Score: "),
                     ("totalWS","Total Weighted Score: "),
                     ("negativeWS","Negative Weighted Score: "),
                     
                     ("autoWS","Auto Weighted Score: "),
                     ("pHadAuto","Had Auto Mode: "),
                     ("pCrossAutoLine","Matches Crossed AutoLine: "),
                     ("pAutoPickedUpCube","Picked Up Cube In Auto Percentage: "),
                     ("avgAutoNumOwnChangesSw1","Average Number Of Opposing Switch Ownership Changes In Auto: "),
                     ("avgAutoNumOwnChangesSw2","Average Number Of Alliance Switch Ownership Changes In Auto: "),
                     ("avgAutoNumOwnChangesScl","Average Number Of Scale Ownership Changes In Auto: "),
                     ("avgAutoCubesInSw1","Average Cubes Placed In Opposing Switch In Auto: "),
                     ("avgAutoCubesInSw2","Average Cubes Placed In Alliance Switch In Auto: "),
                     ("avgAutoCubesInScl","Average Cubes Placed In Scale In Auto: "),
                     ("avgAutoCubesInEx","Average Cubes Placed In Exchange In Auto: "),
                     ("avgAutoOwnGainSw1","Average Time It Takes To Gain Opposing Switch Ownership In Auto: "),
                     ("avgAutoOwnedSw1","Average Time Owning Opposing Switch In Auto: "),
                     ("avgAutoOwnGainSw2","Average Time It Takes To Gain Alliance Switch Ownership In Auto: "),
                     ("avgAutoOwnedSw2","Average Time Owning Alliance Switch In Auto: "),
                     ("avgAutoOwnGainScl","Average Time It Takes To Gain Scale Ownership In Auto: "),
                     ("avgAutoOwnedScl","Average Time Owning Opposing Switch In Auto: "),
                                         
                     ("teleWS","Tele Weighted Score: "),
                     ("pTelePickedUpCube","Picked Up Cube In Tele Percentage: "),
                     ("avgTeleCubesInSw1","Average Cubes Placed In Opposing Switch In Tele: "),
                     ("avgTeleCubesInSw2","Average Cubes Placed In Alliance Switch In Tele: "),
                     ("avgTeleCubesInScl","Average Cubes Placed In Scale In Tele: "),
                     ("avgTeleCubesInEx","Average Cubes Placed In Exchange In Tele: "),
                     ("avgTeleOwnGainSw1","Average Time It Takes To Gain Opposing Switch Ownership In Tele: "),
                     ("avgTeleOwnedSw1","Average Time Owning Opposing Switch In Tele: "),
                     ("avgTeleOwnGainSw2","Average Time It Takes To Gain Alliance Switch Ownership In Tele: "),
                     ("avgTeleOwnedSw2","Average Time Owning Alliance Switch In Tele: "),
                     ("avgTeleOwnGainScl","Average Time It Takes To Gain Scale Ownership In Tele: "),
                     ("avgTeleOwnedScl","Average Time Owning Scale In Tele: "),
                     ("avgTeleNumOwnChangesSw1","Average Number Of Opposing Switch Ownership Changes In Tele: "),
                     ("avgTeleNumOwnChangesSw2","Average Number Of Alliance Switch Ownership Changes In Tele: "),
                     ("avgTeleNumOwnChangesScl","Average Number Of Scale Ownership Changes In Tele: "),


                     ("pClimb","Matches Climbed: "),
                     ("pClimbAssist","Matches Assisted A Climb: "),
                     ("pParking","Matches parked: "),

                     
                     #("totTechFoul","Total # of Tech Foul: "),
                     #("totFoul","Total # of Foul: "),
                     ("pHasFoul","Matches Had Any Foul: "),
                     ("pFoul","Matches Had Foul: "),
                     ("pTechFoul","Matches Had Tech Foul: "),
                     ("pYellowCard","Matches Had Yellow Card: "),
                     ("pRedCard","Matches Had Red Card: "),
                     ("pDisabled", "Matches Where The Robot Was Disabled: "),
                     ("pHumanPlayer", "Matches Human Player In Charge Of Vault: "),
                     

                     ("avgNumCubesInVault","Average Number Of Cubes In The Vault: "),
                     ("avgActiveFceTime","Average Time Force Is Activated: "),
                     ("avgActiveLevTime","Average Time Levitate Is Activated: "),
                     ("avgActiveBstTime","Average Time Boost Is Activated: "),
                     ("avgCubesAtActiveFce","Average Cubes When Force Is Activated: "),
                     ("avgCubesAtActiveLev","Average Cubes When Levitate Is Activated: "),
                     ("avgCubesAtActiveBst","Average Cubes When Boost Is Activated: ")]

    maxminLabelVals = [("maxAutoNumOwnChangesSw1","Maximum Number Of Opposing Switch Ownership Changes In Auto: "),("minAutoNumOwnChangesSw1","Minimum Number Of Opposing Switch Ownership Changes In Auto: "),
                       ("maxAutoNumOwnChangesSw2","Maximum Number Of Alliance Switch Ownership Changes In Auto: "),("minAutoNumOwnChangesSw2","Minimum Number Of Alliance Switch Ownership Changes In Auto: "),
                       ("maxAutoNumOwnChangesScl","Maximum Number Of Scale Ownership Changes In Auto: "),("minAutoNumOwnChangesScl","Minimum Number Of Scale Ownership Changes In Auto: "),
                       ("maxAutoCubesInSw1","Maximum Cubes Placed In Opposing Switch In Auto: "),("minAutoCubesInSw1","Minimum Cubes Placed In Opposing Switch In Auto: "),
                       ("maxAutoCubesInSw2","Maximum Cubes Placed In Alliance Switch In Auto: "),("minAutoCubesInSw2","Minimum Cubes Placed In Alliance Switch In Auto: "),
                       ("maxAutoCubesInScl","Maximum Cubes Placed In Scale In Auto: "),("minAutoCubesInScl","Minimum Cubes Placed In Scale In Auto: "),
                       ("maxAutoCubesInEx","Maximum Cubes Placed In Exchange In Auto: "),("minAutoCubesInEx","Minimum Cubes Placed In Exchange In Auto: "),
                       ("maxAutoOwnGainSw1","Maximum Time It Takes To Gain Opposing Switch Ownership In Auto: "),("minAutoOwnGainSw1","Minimum Time It Takes To Gain Opposing Switch Ownership In Auto: "),                       
                       ("maxAutoOwnedSw1","Maximum Time Owning Opposing Switch In Auto: "),("minAutoOwnedSw1","Minimum Time Owning Opposing Switch In Auto: "),
                       ("maxAutoOwnGainSw2","Maximum Time It Takes To Gain Alliance Switch Ownership In Auto: "),("minAutoOwnGainSw2","Minimum Time It Takes To Gain Alliance Switch Ownership In Auto: "),                       
                       ("maxAutoOwnedSw2","Maximum Time Owning Alliance Switch In Auto: "),("minAutoOwnedSw2","Minimum Time Owning Alliance Switch In Auto: "),
                       ("maxAutoOwnGainScl","Maximum Time It Takes To Gain Scale Ownership In Auto: "),("minAutoOwnGainScl","Minimum Time It Takes To Gain Scale Ownership In Auto: "),                       
                       ("maxAutoOwnedScl","Maximum Time Owning Scale In Auto: "),("minAutoOwnedScl","Minimum Time Owning Scale In Auto: "),

                       ("maxTeleNumOwnChangesSw1","Maximum Number Of Opposing Switch Ownership Changes In Tele: "),("minTeleNumOwnChangesSw1","Minimum Number Of Opposing Switch Ownership Changes In Tele: "),
                       ("maxTeleNumOwnChangesSw2","Maximum Number Of Alliance Switch Ownership Changes In Tele: "),("minTeleNumOwnChangesSw2","Minimum Number Of Alliance Switch Ownership Changes In Tele: "),
                       ("maxTeleNumOwnChangesScl","Maximum Number Of Scale Ownership Changes In Tele: "),("minTeleNumOwnChangesScl","Minimum Number Of Scale Ownership Changes In Tele: "),
                       ("maxTeleCubesInSw1","Maximum Cubes Placed In Opposing Switch In Tele: "),("minTeleCubesInSw1","Minimum Cubes Placed In Opposing Switch In Tele: "),
                       ("maxTeleCubesInSw2","Maximum Cubes Placed In Alliance Switch In Tele: "),("minTeleCubesInSw2","Minimum Cubes Placed In Alliance Switch In Tele: "),
                       ("maxTeleCubesInScl","Maximum Cubes Placed In Scale In Tele: "),("minTeleCubesInScl","Minimum Cubes Placed In Scale In Tele: "),
                       ("maxTeleCubesInEx","Maximum Cubes Placed In Exchange In Tele: "),("minTeleCubesInEx","Minimum Cubes Placed In Exchange In Tele: "),
                       ("maxTeleOwnGainSw1","Maximum Time It Takes To Gain Opposing Switch Ownership In Tele: "),("minTeleOwnGainSw1","Minimum Time It Takes To Gain Opposing Switch Ownership In Tele: "),                       
                       ("maxTeleOwnedSw1","Maximum Time Owning Opposing Switch In Tele: "),("minTeleOwnedSw1","Minimum Time Owning Opposing Switch In Tele: "),
                       ("maxTeleOwnGainSw2","Maximum Time It Takes To Gain Alliance Switch Ownership In Tele: "),("minTeleOwnGainSw2","Minimum Time It Takes To Gain Alliance Switch Ownership In Tele: "),                       
                       ("maxTeleOwnedSw2","Maximum Time Owning Alliance Switch In Tele: "),("minTeleOwnedSw2","Minimum Time Owning Alliance Switch In Tele: "),
                       ("maxTeleOwnGainScl","Maximum Time It Takes To Gain Scale Ownership In Tele: "),("minTeleOwnGainScl","Minimum Time It Takes To Gain Scale Ownership In Tele: "),                       
                       ("maxTeleOwnedScl","Maximum Time Owning Scale In Tele: "),("minTeleOwnedScl","Minimum Time Owning Scale In Tele: "),
                       
                       ("maxNumCubesInVault","Maximum Number Of Cubes In The Vault: "),("minNumCubesInVault","Minimum Number Of Cubes In The Vault: "),
                       ("maxActiveFceTime","Maximum Time Force Is Activated: "),("minActiveFceTime","Minimum Time Force Is Activated: "),
                       ("maxActiveLevTime","Maximum Time Levitate Is Activated: "),("minActiveLevTime","Minimum Time Levitate Is Activated: "),
                       ("maxActiveBstTime","Maximum Time Boost Is Activated: "),("minActiveBstTime","Minimum Time Boost Is Activated: "),
                       ("maxCubesAtActiveFce","Maximum Cubes When Force Is Activated: "),("minCubesAtActiveFce","Minimum Cubes When Force Is Activated: "),
                       ("maxCubesAtActiveLev","Maximum Cubes When Levitate Is Activated: "),("minCubesAtActiveLev","Minimum Cubes When Levitate Is Activated: "),
                       ("maxCubesAtActiveBst","Maximum Cubes When Boost Is Activated: "),("minCubesAtActiveBst","Minimum Cubes When Boost Is Activated: ")]
    
    pitDataStrings = []
                       

    graphVals = [("avgAutoCubesInSw1", "Scores","autoCubesInSw1"),
                 ("avgAutoCubesInScl", "Scores","autoCubesInScl"),
                 ("avgAutoCubesInSw2", "Scores","autoCubesInSw2"),
                 ("avgAutoCubesInEx", "Scores","autoCubesInEx"),

                 ("avgAutoOwnGainSw1","Scores","autoGainOwnTimesSw1"),
                 ("avgAutoOwnGainScl","Scores","autoGainOwnTimesScl"),
                 ("avgAutoOwnGainSw2","Scores","autoGainOwnTimesSw2"),

                 ("avgAutoOwnedSw1","Scores","autoOwnedSw1"),
                 ("avgAutoOwnedScl","Scores","autoOwnedScl"),
                 ("avgAutoOwnedSw2","Scores","autoOwnedSw2"),

                 ("avgAutoNumOwnChangesSw1", "Scores", "autoNumOwnChangesSw1"),
                 ("avgAutoNumOwnChangesScl", "Scores", "autoNumOwnChangesScl"),
                 ("avgAutoNumOwnChangesSw2", "Scores", "autoNumOwnChangesSw2"),
                  
                 ("avgTeleCubesInSw1", "Scores","teleCubesInSw1"),
                 ("avgTeleCubesInScl", "Scores","teleCubesInScl"),
                 ("avgTeleCubesInSw2", "Scores","teleCubesInSw2"),
                 ("avgTeleCubesInEx", "Scores","teleCubesInEx"),

                 ("avgTeleOwnGainSw1","Scores","teleGainOwnTimesSw1"),
                 ("avgTeleOwnGainScl","Scores","teleGainOwnTimesScl"),
                 ("avgTeleOwnGainSw2","Scores","teleGainOwnTimesSw2"),

                 ("avgTeleOwnedSw1","Scores","teleOwnedSw1"),
                 ("avgTeleOwnedScl","Scores","teleOwnedScl"),
                 ("avgTeleOwnedSw2","Scores","teleOwnedSw2"),

                 ("avgTeleNumOwnChangesSw1", "Scores", "teleNumOwnChangesSw1"),
                 ("avgTeleNumOwnChangesSw2", "Scores", "teleNumOwnChangesScl"),
                 ("avgTeleNumOwnChangesScl", "Scores", "teleNumOwnChangesSw2"),
                 
                 ("avgNumCubesInVault", "Scores","numCubesInVault"),
                 
                 ("avgActiveFceTime", "Scores","activeFceTime"),
                 ("avgActiveLevTime", "Scores","activeLevTime"),
                 ("avgActiveBstTime", "Scores","activeBstTime"),
                 
                 ("avgCubesAtActiveFce", "Scores","cubesAtActiveFce"),
                 ("avgCubesAtActiveLev", "Scores","cubesAtActiveLev"),
                 ("avgCubesAtActiveBst", "Scores","cubesAtActiveBst")]
    
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

            






