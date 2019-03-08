#------------------------------------------------------------------------------
# team Module
#   -- Keeps track of valuable team information and scorings
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
# TeamInfo Class
#   -- Stores performance information
#------------------------------------------------------------------------------
class _TeamInfo(object):
    """Used to handle information for different teams."""

    def __init__(self):
        self.matches = []
        # Things that go into TeamInfo....... numbers that you want as percentages and totals
        # The numbers are set up so the numbers set as 0 are check boxes that incriment when set to true                     
        self.noShow = 0                    
        self.startLevel1 = 0
        self.startLevel2 = 0
        self.preloadCargo = 0
        self.preloadHatch = 0
        self.crossHubline = 0
        self.endLevel1 = 0
        self.endLevel2 = 0
        self.endLevel3 = 0
        self.endNone = 0
        self.robotDisabled = 0
        self.redCard = 0
        self.yellowCard = 0
        self.fouls = 0
        self.techFouls = 0
        self.hasFoul = 0

    def get_info(self):
        self.totalFoul =  self.fouls
        # you don't need to the the redCard, yellowCard, and disabled because they add up on their own
        self.totalTechFoul = self.techFouls
        # fouls are in arrays so they need to be added manually

        self.pCrossHubLine = float(100 * self.crossHubline) / float(len(self.matches)) if len(self.matches) else 0
        self.pNoShow = float(100*self.noShow)/float(len(self.matches)) if len(self.matches) else 0

        self.pStartLevel1 = float(100*self.startLevel1)/float(len(self.matches)) if len(self.matches) else 0
        self.pStartLevel2 = float(100*self.startLevel2)/float(len(self.matches)) if len(self.matches) else 0
                                                      
        self.pPreloadCargo = float(100*self.preloadCargo)/float(len(self.matches)) if len(self.matches) else 0
        self.pPreloadHatch = float(100*self.preloadHatch)/float(len(self.matches)) if len(self.matches) else 0
                                                                        
        self.pEndLevel1 = float(100*self.endLevel1)/float(len(self.matches)) if len(self.matches) else 0
        self.pEndLevel2 = float(100*self.endLevel2)/float(len(self.matches)) if len(self.matches) else 0
        self.pEndLevel3 = float(100*self.endLevel3)/float(len(self.matches)) if len(self.matches) else 0
        self.pEndNone = float(100*self.endNone)/float(len(self.matches)) if len(self.matches) else 0

        self.pDisabled = float(100*self.robotDisabled)/float(len(self.matches)) if len(self.matches) else 0                             
        self.pRedCard = float(100*self.redCard)/float(len(self.matches)) if len(self.matches) else 0
        self.pYellowCard = float(100*self.yellowCard)/float(len(self.matches)) if len(self.matches) else 0
        self.pFoul = float(100*self.totalFoul)/float(len(self.matches)) if len(self.matches) else 0
        self.pTechFoul = float(100*self.totalTechFoul)/float(len(self.matches)) if len(self.matches) else 0
                
    def getAttr(self, source):
        #return getattr(self, source)
        return None

#------------------------------------------------------------------------------
# TeamScores Class
#   -- stores data about a team's scores
#------------------------------------------------------------------------------
class _TeamScores(object):
    """Used to handle scoring data for different teams."""

    def __init__(self):
        self.rocketTopC = []
        self.rocketTopH = []
        self.rocketMiddleC = []
        self.rocketMiddleH = []
        self.rocketBottomC = []
        self.rocketBottomH = []
        self.cargoShipC = []
        self.cargoShipH = []

    def get_maxmin_scores(self):
        self.maxRocketTopC = max(self.rocketTopC) if len(self.rocketTopC) else 0
        self.minRocketTopC = min(self.rocketTopC) if len(self.rocketTopC) else 0
        self.maxRocketTopH = max(self.rocketTopH) if len(self.rocketTopH) else 0
        self.minRocketTopH = min(self.rocketTopH) if len(self.rocketTopH) else 0

        self.maxRocketMiddleC = max(self.rocketMiddleC) if len(self.rocketMiddleC) else 0
        self.minRocketMiddleC = min(self.rocketMiddleC) if len(self.rocketMiddleC) else 0
        self.maxRocketMiddleH = max(self.rocketMiddleH) if len(self.rocketMiddleH) else 0
        self.minRocketMiddleH = min(self.rocketMiddleH) if len(self.rocketMiddleH) else 0

        self.maxRocketBottomC = max(self.rocketBottomC) if len(self.rocketBottomC) else 0
        self.minRocketBottomC = min(self.rocketBottomC) if len(self.rocketBottomC) else 0
        self.maxRocketBottomH = max(self.rocketBottomH) if len(self.rocketBottomH) else 0
        self.minRocketBottomH = min(self.rocketBottomH) if len(self.rocketBottomH) else 0

        self.maxCargoShipC = max(self.cargoShipC) if len(self.cargoShipC) else 0
        self.minCargoShipC = min(self.cargoShipC) if len(self.cargoShipC) else 0
        self.maxCargoShipH = max(self.cargoShipH) if len(self.cargoShipH) else 0
        self.minCargoShipH = min(self.cargoShipH) if len(self.cargoShipH) else 0

    def get_avgOff_scores(self, matches=1):

        self.avgRocketTopC = float(sum(self.rocketTopC))/float(matches) if matches else 0
        self.avgRocketTopH = float(sum(self.rocketTopH))/float(matches) if matches else 0
        self.avgRocketMiddleC = float(sum(self.rocketMiddleC))/float(matches) if matches else 0
        self.avgRocketMiddleH = float(sum(self.rocketMiddleH))/float(matches) if matches else 0
        self.avgRocketBottomC = float(sum(self.rocketBottomC))/float(matches) if matches else 0
        self.avgRocketBottomH = float(sum(self.rocketBottomH))/float(matches) if matches else 0
        self.avgCargoShipC = float(sum(self.cargoShipC))/float(matches) if matches else 0
        self.avgCargoShipH = float(sum(self.cargoShipH))/float(matches) if matches else 0

    def getAttr(self, source):
        return getattr(self, source)
#------------------------------------------------------------------------------
# TeamPitInfo Class
#   -- stores data unrelated to performance on the field
#------------------------------------------------------------------------------
class _TeamPitInfo(object):
    """Used to handle information about a teams chassis and other
       non-performance related information."""

    def __init__(self):
      self.answer1 = ""
      self.answer2 = ""
      self.answer3 = ""
      self.answer4 = ""
      self.answer5 = ""
      self.answer6 = ""

    def getAttr(self, source):
        return getattr(self, source)    

#------------------------------------------------------------------------------
# TeamRankings class
#   -- place to store ranking lists, for viewing team ranks
#------------------------------------------------------------------------------
class TeamRankings(object):
    """Used to keep track of rankings for each team."""

    rocketTopC_rank = []
    rocketTopH_rank = []
    rocketMiddleC_rank = []
    rocketMiddleH_rank = []
    rocketBottomC_rank = []
    rocketBottomH_rank = []
    cargoShipC_rank = []
    cargoShipH_rank = []
    fouls_rank = []
    techFouls_rank = []

    def __init__(self):
        print
        # no non-static class variables
        # team cannot track its own ranking:
            # rankings are defined by the user
            # rankings are dynamic, constantly changing to user request

    def getAttr(self, source):
        return getattr(self, source)

#------------------------------------------------------------------------------
# Team Class
#   -- stores and recalls team specific data
#------------------------------------------------------------------------------
class Team(object):
    """Store and recall data on a team from here."""

    team_list = []  # list holding all the teams currently loaded in the database
    available = []  # list holding all the teams not currently selected
    wanted = []     # list holding all the teams in our wanted list
    
    def __init__(self, num):
        self.number = num
        self.Info = _TeamInfo()
        self.Scores = _TeamScores()
        self.PitInfo = _TeamPitInfo()
        self.team_list.append(self)
        self.available.append(self)

        # a few of the final details predefined so as to satisfy predictions with null teams
        self.avgOff = 0
        self.avgDef = 0
        self.pOff = 0
        self.pDef = 0     

    def get_primary_details(self): # gets the offensive values of Team
        self.Info.get_info()
        self.Scores.get_avgOff_scores(len(self.Info.matches))
        self.Scores.get_maxmin_scores()

        #weighted scores calculations
            
        self.offensiveWS = str(round(((self.Info.pCrossHubLine/100.0)*4.0)
                                     + ((self.Info.pStartLevel2/100.0)*3.0)+((self.Info.pEndLevel1/100.0)*3.0)
                                     + ((self.Info.pEndLevel2/100.0)*6.0)+((self.Info.pEndLevel3/100.0)*12.0)
                                     + ((self.Scores.avgRocketTopC/4)*3.0)+((self.Scores.avgRocketTopH/4)*2.0)
                                     + ((self.Scores.avgRocketMiddleC/4)*3.0) + ((self.Scores.avgRocketMiddleH/4)*2.0)
                                     + ((self.Scores.avgRocketBottomC/4)*3.0)+((self.Scores.avgRocketBottomH/4)*2.0)
                                     + ((self.Scores.avgCargoShipC/4)*3.0)+((self.Scores.avgCargoShipH/4)*2.0),2))

        self.negativeWS = str(round(((self.Info.pFoul/100.0)*-2.0)+((self.Info.pYellowCard/100.0)*-3.0)+((self.Info.pDisabled/100.0)*-10.0)
                                +((self.Info.pRedCard/100.0)*-10)+((self.Info.pTechFoul/100.0)*-11.0),2))

        self.totalWS = str(round(float(self.offensiveWS)+float(self.negativeWS), 2))

        matches = self.Info.matches
        self.numMatch = len(matches)
        self.pNoShow  = str(round(self.Info.pNoShow,2))  + "%"

        self.pCrossHubLine = str(round(self.Info.pCrossHubLine,2))  + "%"
        self.pStartLevel1 = str(round(self.Info.pStartLevel1,2))  + "%"
        self.pStartLevel2 = str(round(self.Info.pStartLevel2,2))  + "%"
        self.pPreloadCargo = str(round(self.Info.pPreloadCargo,2))  + "%"
        self.pPreloadHatch = str(round(self.Info.pPreloadHatch,2))  + "%"
        self.pEndLevel1 = str(round(self.Info.pEndLevel1,2))  + "%"
        self.pEndLevel2 = str(round(self.Info.pEndLevel2,2))  + "%"
        self.pEndLevel3 = str(round(self.Info.pEndLevel3,2))  + "%"
        self.pEndNone = str(round(self.Info.pEndNone,2))  + "%"
        self.pRedCard = str(round(self.Info.pRedCard,2))  + "%"
        self.pYellowCard = str(round(self.Info.pYellowCard,2))  + "%"

        self.pHasFoul = str(int(100*self.Info.hasFoul)/len(matches)) + "%" if len(matches) else "0%"
        self.pFoul = str(round(self.Info.pFoul, 2)) + "%"
        self.pTechFoul = str(round(self.Info.pTechFoul, 2)) + "%"
        self.pDisabled = str(round(self.Info.pDisabled)) + "%"

        self.avgRocketTopC = str(round(self.Scores.avgRocketTopC))
        self.avgRocketTopH = str(round(self.Scores.avgRocketTopH))
        self.avgRocketMiddleC = str(round(self.Scores.avgRocketMiddleC))
        self.avgRocketMiddleH = str(round(self.Scores.avgRocketMiddleH))
        self.avgRocketBottomC = str(round(self.Scores.avgRocketBottomC))
        self.avgRocketBottomH = str(round(self.Scores.avgRocketBottomH))
        self.avgCargoShipC = str(round(self.Scores.avgCargoShipC))
        self.avgCargoShipH = str(round(self.Scores.avgCargoShipH))


    def getAttr(self, source):
        return getattr(self, source)
