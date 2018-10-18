#------------------------------------------------------------------------------
# csearch module
#   -- contains data and information for controlling the search window
#------------------------------------------------------------------------------
from Tkinter import *

from model import team

#------------------------------------------------------------------------------
# SearchController class
#   -- contains functions, lists, data, etc, for handling the search window
#------------------------------------------------------------------------------
class SearchController():
    """Class that handles commands from the search window."""

    entryItemGenAuto = [("avgOff","Offensive Score >= "),
                        ("avgDef","Defencive Score >= "),
                        ("avgTotal","Total Score >= "),
                        ("avgAuto","Auto Score >= "),
                        ("avgAutoCubesInSw1","Average Cubes Placed In Opposing Switch In Auto >= "),
                        ("avgAutoCubesInSw2","Average Cubes Placed In Alliance Switch In Auto >= "),
                        ("avgAutoCubesInScl","Average Cubes Placed In Scale In Auto >= "),
                        ("avgAutoCubesInEx","Average Cubes Placed In Exchange In Auto >= "),
                        ("avgAutoOwnGainSw1","Average Time It Takes To Gain Opposing Switch Ownership In Auto <= "),
                        ("avgAutoOwnedSw1","Average Time Owning Opposing Switch In Auto >= "),
                        ("avgAutoOwnGainSw2","Average Time It Takes To Gain Alliance Switch Ownership In Auto <= "),
                        ("avgAutoOwnedSw2","Average Time Owning Alliances Switch In Auto >= "),
                        ("avgAutoOwnGainScl","Average Time It Takes To Gain Scale Ownership In Auto <= "),
                        ("avgAutoOwnedScl","Average Time Owning Scale In Auto >= "),
                        ("avgAutoNumOwnChangesSw1","Average Number Of Opposing Switch Ownership Changes In Auto >= "),
                        ("avgAutoNumOwnChangesSw2","Average Number Of Alliance Switch Ownership Changes In Auto >= "),
                        ("avgAutoNumOwnChangesScl","Average Number Of Scale Ownership Changes In Auto >= "),
                        ("avgHadAuto","Auto Score >= ")]
                        

    entryItemTelePost = [("avgTele","Tele Score >= "),
                        ("avgTeleCubesInSw1","Average Cubes Placed In Opposing Switch In Tele >= "),
                        ("avgTeleCubesInSw2","Average Cubes Placed In Alliance Switch In Tele >= "),
                        ("avgTeleCubesInScl","Average Cubes Placed In Scale In Tele >= "),
                        ("avgTeleCubesInEx","Average Cubes Placed In Exchange In Tele >= "),
                        ("avgTeleOwnGainSw1","Average Time It Takes To Gain Opposing Switch Ownership In Tele <= "),
                        ("avgTeleOwnedSw1","Average Time Owning Opposing Switch In Tele >= "),
                        ("avgTeleOwnGainSw2","Average Time It Takes To Gain Alliance Switch Ownership In Tele <= "),
                        ("avgTeleOwnedSw2","Average Time Owning Alliances Switch In Tele >= "),
                        ("avgTeleOwnGainScl","Average Time It Takes To Gain Scale Ownership In Tele <= "),
                        ("avgTeleOwnedScl","Average Time Owning Scale In Tele >= "),
                        ("avgTeleNumOwnChangesSw1","Average Number Of Opposing Switch Ownership Changes In Tele >= "),
                        ("avgTeleNumOwnChangesSw2","Average Number Of Alliance Switch Ownership Changes In Tele >= "),
                        ("avgTeleNumOwnChangesScl","Average Number Of Scale Ownership Changes In Tele >= "),

                        ("avgNumCubesInVault","Average Number Of Cubes In The Vault >= "),
                        ("avgActiveFceTime","Average Time Force Is Activated <= "),
                        ("avgActiveLevTime","Average Time Levitate Is Activated <="),
                        ("avgActiveBstTime","Average Time Boost Is Activated <= "),
                        ("avgCubesAtActiveFce","Average Cubes When Force Is Activated >= "),
                        ("avgCubesAtActiveLev","Average Cubes When Levitate Is Activated >= "),
                        ("avgCubesAtActiveBst","Average Cubes When Boost Is Activated >= ")]
    
    checkItemTypes = [("hadAuto","Had Autonomous"),
                      ("crossAutoLine","Crossed Baseline"),
                      ("scoredInTele","Scored in Tele"),
                      ("climb","Climbed"),
                      ("climbAssist","Assisted Climb"),
                      ("parking","Parked"),
                      ("noShow","Always Showed Up"),
                      ("disabled","Never Disabled"),
                      ("hasFoul","No Fouls"),
                      ("yellowCard","No Yellow Cards"),
                      ("redCard","No Red Cards")]
                    
    def searchGreater(self, value=None, index=None):
        try:
            self.matchedList = filter(lambda team:team.getAttr(index)>=float(value.get()), self.matchedList)
        except:
             print "Invalid Search Parameter " + str(value.get()) + " for " + str(index)
             value.set(0)

    def searchLess(self, value=None, index=None):
        try:
            self.matchedList = filter(lambda team:team.getAttr(index)<=int(value.get()), self.matchedList)
        except:
             print "Invalid Search Parameter " + str(value.get()) + " for " + str(index)
             value.set(999)

    def searchHas(self, value=None, index=None):
        try:
            if value.get() == 1:
                self.matchedList = filter(lambda team:team.Info.getAttr(index) >= 1, self.matchedList)
        except:
            value.set(0)

    def searchNever(self, value=None, index=None):
        try:
            if value.get() == 1:
                self.matchedList = filter(lambda team:team.Info.getAttr(index) == 0, self.matchedList)
        except:
            value.set(0)

    
    Searches = {"avgOff":searchGreater,
                "avgTotal":searchGreater,
                "avgAuto":searchGreater,
                "avgHadAuto":searchGreater,
                "hadAuto":searchHas,
                "climb":searchHas,
                "climbAssist":searchHas,
                "parking":searchHas,
                "crossAutoLine":searchHas,
                "scoredInTele":searchHas,
                "disabled":searchNever,
                "noShow":searchNever,
                "hasFoul":searchNever,
                "yellowCard":searchNever,
                "redCard":searchNever,
                "avgDef":searchGreater,
                "avgAutoCubesInSw1":searchGreater,
                "avgAutoCubesInSw2":searchGreater,
                "avgAutoCubesInScl":searchGreater,
                "avgAutoCubesInEx":searchGreater,
                "avgAutoOwnGainSw1":searchLess,
                "avgAutoOwnedSw1":searchGreater,
                "avgAutoOwnGainSw2":searchLess,
                "avgAutoOwnedSw2":searchGreater,
                "avgAutoOwnGainScl":searchLess,
                "avgAutoNumOwnChangesSw1":searchGreater,
                "avgAutoNumOwnChangesSw2":searchGreater,
                "avgAutoNumOwnChangesScl":searchGreater,
                "avgHadAuto":searchGreater,
                "avgTele":searchGreater,
                "avgTeleCubesInSw1":searchGreater,
                "avgTeleCubesInSw2":searchGreater,
                "avgTeleCubesInScl":searchGreater,
                "avgTeleCubesInEx":searchGreater,
                "avgTeleOwnGainSw1":searchLess,
                "avgTeleOwnedSw1":searchGreater,
                "avgTeleOwnGainSw2":searchLess,
                "avgTeleOwnedSw2":searchGreater,
                "avgTeleOwnGainScl":searchLess,
                "avgTeleOwnedScl":searchGreater,
                "avgTeleNumOwnChangesSw1":searchGreater,
                "avgTeleNumOwnChangesSw2":searchGreater,
                "avgTeleNumOwnChangesScl":searchGreater,
                "avgNumCubesInVault":searchGreater,
                "avgActiveFceTime":searchLess,
                "avgActiveLevTime":searchLess,
                "avgActiveBstTime":searchLess,
                "avgCubesAtActiveFce":searchGreater,
                "avgCubesAtActiveLev":searchGreater,
                "avgCubesAtActiveBst":searchGreater}

    def search(self):
        self.matchedList = team.Team.team_list

        for index, value in self.searchVariables:
            if index in self.Searches:
                self.Searches[index](self,value,index)

    def addWanted(self,number=None):
        for t in team.Team.team_list:
            if t.number == int(number) and t not in team.Team.wanted:
                team.Team.wanted.append(t)
                break

        self.wantedList = team.Team.wanted

    def subWanted(self,number=None):
        for t in team.Team.wanted:
            if t.number == int(number):
                team.Team.wanted.remove(t)
                break

        self.wantedList = team.Team.wanted

    def sortWanted(self,rList=None):
        newList = []
        for item in rList:
            for t in team.Team.team_list:
                if t.number == int(item):
                    newList.append(t)
                    break
                    
        team.Team.wanted = newList
        self.wantedList = team.Team.wanted
        
    def __init__(self):
        self.matchedList = team.Team.team_list
        self.searchVariables = []
        self.wantedList = team.Team.wanted
        
