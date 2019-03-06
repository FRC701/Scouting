#------------------------------------------------------------------------------
# csearch module
#   -- contains data and information for controlling the search window
#------------------------------------------------------------------------------
from tkinter import *

from model import team

#------------------------------------------------------------------------------
# SearchController class
#   -- contains functions, lists, data, etc, for handling the search window
#------------------------------------------------------------------------------
class SearchController():
    """Class that handles commands from the search window."""

    entryItem = [("avgOff","Offensive Score >= "),
                        ("avgTotal","Total Score >= "),
                        ("avgRocketTopC","Average Cargo In Top Rocket >= "),
                        ("avgRocketTopH","Average Hatch In Top Rocket >= "),
                        ("avgRocketMiddleC","Average Cargo In Middle Rocket >= "),
                        ("avgRocketMiddleH","Average Hatch In Middle Rocket >= "),
                        ("avgRocketBottomC","Average Cargo In Bottom Rocket <= "),
                        ("avgRocketBottomH","Average Hatch In Bottom Rocket >= "),
                        ("avgCargoShipC","Average Cargo In Cargo Ship <= "),
                        ("avgCargoShipH","Average Hatch In Cargo Ship >= "),
    
    checkItemTypes = [("startLevel1","Started on Level 1"),
                      ("startLevel2","Started on Level 2"),
                      ("preloadCargo","Preload Cargo"),
                      ("preloadHatch","Preload Hatch"),
                      ("crossHubline","Crossed Hubline"),
                      ("endLevel1","Ended on Level 1"),
                      ("endLevel2","Ended on Level 2"),
                      ("endLevel3","Ended on Level 3"),
                      ("endNone","Didn't End on a Level"),
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
                "avgRocketTopC":searchGreater,
                "avgRocketTopH":searchGreater,
                "avgRocketMiddleC":searchGreater,
                "avgRocketMiddleH":searchGreater,
                "avgRocketBottomC":searchGreater,
                "avgRocketBottomH":searchGreater,
                "avgCargoShipC":searchGreater,
                "avgCargoShipH":searchGreater,
                "disabled":searchNever,
                "noShow":searchNever,
                "hasFoul":searchNever,
                "yellowCard":searchNever,
                "redCard":searchNever,
                "startLevel1":searchHas,
                "startLevel2":searchHas,
                "preloadCargo":searchHas,
                "preloadHatch":searchHas,
                "crossHubline":searchHas,
                "endLevel1":searchHas,
                "endLevel2":searchHas,
                "endLevel3":searchHas,
                "endNone":searchNever}

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
        
